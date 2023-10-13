package com.anhvu.springcoredemo.common;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class TrackCoach implements Coach {
    public TrackCoach() {
        System.out.println("In contructor " + getClass().getSimpleName());
    }

    // define our init method
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("In doMyStartupStuff " + getClass().getSimpleName());
    }

    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("In doMyCleanupStuff " + getClass().getSimpleName());
    }
    // define our destroy method

    @Override
    public String getDailyWorkout() {
        return "Get Daily Track Coach!";
    }
}
