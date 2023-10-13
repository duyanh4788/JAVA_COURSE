package com.anhvu.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FundRestController {
    @GetMapping("/")
    public String sayHello() {
        return "Hello Anh Vu";
    }
    @GetMapping("/checkout")
    public String checkout() {
        return "DATA 01";
    }
}
