package com.anhvu.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class TenisCoach implements Coach {
    public TenisCoach() {
        System.out.println("In contructor " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Get Daily Tenis Coach!";
    }
}
