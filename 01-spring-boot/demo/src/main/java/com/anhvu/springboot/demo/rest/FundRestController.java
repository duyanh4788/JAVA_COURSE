package com.anhvu.springboot.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FundRestController {
    @GetMapping("/")
    public String sayHello() {
        return "Hello Anh Vu!";
    }

    @GetMapping("/workout")
    public String getDaillyWorkOut() {
        return "Demo 01!";
    }

    @GetMapping("/workout2")
    public String getDaillyWorkOut2() {
        return "Demo 02!";
    }
}
