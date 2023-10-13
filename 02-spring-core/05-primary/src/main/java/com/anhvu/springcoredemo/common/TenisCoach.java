package com.anhvu.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class TenisCoach implements Coach{
    @Override
    public String getDailyWorkout(){
        return "Get Daily Tenis Coach!";
    }
}
