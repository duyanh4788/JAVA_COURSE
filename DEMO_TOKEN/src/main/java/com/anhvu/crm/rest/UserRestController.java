package com.anhvu.crm.rest;

import java.text.ParseException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhvu.crm.JWT.JwtToken;
import com.anhvu.crm.config.PreAuthorize;
import com.anhvu.crm.entity.User;
import com.anhvu.crm.exception.ExceptionError;
import com.anhvu.crm.exception.ResponseSuccess;
import com.anhvu.crm.interfaces.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private UserService userService;
    private JwtToken jwtToken;

    public UserRestController(UserService theUserService, JwtToken theJwtToken) {
        userService = theUserService;
        jwtToken = theJwtToken;
    }

    @PreAuthorize("hasRole('1')")
    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        if (id == null || id < 0) {
            throw new ExceptionError("User Id not available " + id);
        }

        User user = userService.findById(id);
        if (user == null) {
            throw new ExceptionError("User Id not available " + id);
        }

        return user;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ResponseSuccess> signIn(@RequestBody User theUser, HttpServletResponse response)
            throws ParseException {
        User user = userService.findByUserName(theUser.getUserName());
        if (user == null) {
            throw new ExceptionError("user not found");
        }
        System.out.println(user);
        String passWord = user.getPassWord();
        String thePassWord = theUser.getPassWord();
        if (BCrypt.checkpw(thePassWord, passWord)) {
            String token = jwtToken.generateToken(user);
            Cookie cookie = new Cookie("jwtToken", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseSuccess.build("Sign in successfully", token);
        } else {
            throw new ExceptionError("Invalid password");
        }
    }

    @PostMapping("/sign-up")
    public String signUp(@RequestBody User theUser) {
        User user = userService.findByUserName(theUser.getUserName());
        if (user != null) {
            throw new ExceptionError("user name has exits");
        }
        String plainPassword = theUser.getPassWord();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(plainPassword);
        theUser.setPassWord(hashedPassword);
        int userId = userService.save(theUser);
        return "Sign up successfully, the new id: " + userId;
    }
}
