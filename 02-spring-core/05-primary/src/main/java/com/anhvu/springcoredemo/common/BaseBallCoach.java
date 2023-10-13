package com.anhvu.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseBallCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Get Daily Base Ball Coach!";
    }
}
