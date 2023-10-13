package com.anhvu.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach{
    @Override
    public String getDailyWorkout(){
        return "Get Daily Cricket Coach!!!!!";
    }
}
