package com.anhvu.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class TrackCoach implements Coach{
    @Override
    public String getDailyWorkout(){
        return "Get Daily Track Coach!";
    }
}
