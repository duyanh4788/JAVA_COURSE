package com.anhvu.springcoredemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.anhvu.springcoredemo.common.Coach;
import com.anhvu.springcoredemo.common.SwimCoach;

@Configuration
public class SportConfig {
    @Bean("aquatic")
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
