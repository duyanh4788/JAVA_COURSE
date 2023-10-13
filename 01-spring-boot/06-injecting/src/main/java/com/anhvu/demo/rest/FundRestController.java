package com.anhvu.demo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FundRestController {
    // inject properties for: coach team
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;

    @GetMapping("/")
    public String sayHello() {
        return "Hello Anh Vu";
    }

    @GetMapping("/checkout")
    public Number checkout() {
        return 12345;
    }

    @GetMapping("/coach")
    public String getCoach() {
        return "Coach: " + coachName + " Team: " + teamName;
    }

    @GetMapping("/team")
    public String getTeam() {
        return "Team: " + teamName + " Coach: " + coachName;
    }
}
