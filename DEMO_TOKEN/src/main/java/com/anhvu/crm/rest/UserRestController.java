package com.anhvu.crm.rest;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhvu.crm.DTO.UserDTO;
import com.anhvu.crm.JWT.JwtToken;
import com.anhvu.crm.config.PreAuthorize;
import com.anhvu.crm.entity.AuthToken;
import com.anhvu.crm.entity.User;
import com.anhvu.crm.exception.ExceptionError;
import com.anhvu.crm.exception.ResponseSuccess;
import com.anhvu.crm.interfaces.AuthService;
import com.anhvu.crm.interfaces.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private UserService userService;
    private AuthService authService;
    private JwtToken jwtToken;

    public UserRestController(UserService theUserService, JwtToken theJwtToken, AuthService theAuthService) {
        userService = theUserService;
        jwtToken = theJwtToken;
        authService = theAuthService;
    }

    @PreAuthorize("hasRole('1')")
    @GetMapping("/infor")
    public ResponseEntity<ResponseSuccess> getById(HttpServletRequest request) {
        Long userIdLong = (Long) request.getAttribute("userId");
        int userId = userIdLong.intValue();
        if (userId < 0) {
            throw new ExceptionError("Invalid User Id: " + userId);
        }

        User user = userService.findById(userId);
        if (user == null) {
            throw new ExceptionError("User Id not available " + userId);
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return ResponseSuccess.build("", userDTO);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ResponseSuccess> signIn(@RequestBody User theUser, HttpServletResponse response)
            throws ParseException {
        User user = userService.findByUserName(theUser.getUserName());
        if (user == null) {
            throw new ExceptionError("user not found");
        }
        String passWord = user.getPassWord();
        String thePassWord = theUser.getPassWord();
        if (BCrypt.checkpw(thePassWord, passWord)) {
            List<AuthToken> authTokens = authService.findByUserId(user.getId());
            if (authTokens.size() >= 5) {
                authService.deleteByUserId(user.getId());
                throw new ExceptionError(
                        "You has login upto 5 device, we are remove history login to all device,  please login again");
            }
            String publicKey = jwtToken.generateSecretKey();
            String privateKey = jwtToken.generateSecretKey();
            String token = jwtToken.generateToken(user, publicKey);
            String refreshToken = jwtToken.generateToken(user, privateKey);
            AuthToken authRes = new AuthToken();
            authRes.setPublicKey(publicKey);
            authRes.setPrivateKey(privateKey);
            authRes.setUserId(user.getId());
            authRes.setKeyToken(token);
            authRes.setRefreshToken(refreshToken);
            authService.save(authRes);
            Cookie cookie = new Cookie("jwtToken", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            Map<String, Object> tokenRes = new HashMap<>();
            tokenRes.put("userId", user.getId());
            tokenRes.put("token", token);
            return ResponseSuccess.build("Sign in successfully", tokenRes);
        } else {
            throw new ExceptionError("Invalid password");
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseSuccess> signUp(@RequestBody User theUser) {
        User user = userService.findByUserName(theUser.getUserName());
        if (user != null) {
            throw new ExceptionError("user name has exits");
        }
        String plainPassword = theUser.getPassWord();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(plainPassword);
        theUser.setPassWord(hashedPassword);
        int userId = userService.save(theUser);
        Map<String, Object> userRes = new HashMap<>();
        userRes.put("userId", userId);
        return ResponseSuccess.build("Sign up successfully", userRes);

    }
}
