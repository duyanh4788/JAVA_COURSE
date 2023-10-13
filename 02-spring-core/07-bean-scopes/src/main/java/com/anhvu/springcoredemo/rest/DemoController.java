package com.anhvu.springcoredemo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhvu.springcoredemo.common.Coach;

@RestController
public class DemoController {
    // define a private field for the dependency
    private Coach myCoach;
    private Coach anotherCoach;

    @Autowired
    public DemoController(@Qualifier("trackCoach") Coach theCoach, @Qualifier("trackCoach") Coach theAnotherCoach) {
        System.out.println("In contructor " + getClass().getSimpleName());
        myCoach = theCoach;
        anotherCoach = theAnotherCoach;
    }

    @GetMapping("/daily-workout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/anotherCoach")
    public String checkAnotherCoach() {
        return myCoach.getDailyWorkout() + " || " + anotherCoach.getDailyWorkout() + " || another coach: "
                + (myCoach == anotherCoach);
    }
}
