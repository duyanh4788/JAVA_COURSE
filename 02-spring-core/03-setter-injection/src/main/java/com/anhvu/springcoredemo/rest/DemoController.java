package com.anhvu.springcoredemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhvu.springcoredemo.common.Coach;

@RestController
public class DemoController {
    // define a private field for the dependency
    private Coach myCoach;

    // define a contructor for dependency injection
    @Autowired
    public void setCoach(Coach theCoach){
        myCoach = theCoach;
    }

    @GetMapping("/daily-workout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
