package com.jpop.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheckController {
    @Value("${health.check.message: default value here...}")
    private String message;

    @Autowired
    private Environment environment;


    @GetMapping("")
    public String checkHealth(){
        return message;
    }

    @GetMapping("/envDetails")
    public String getEnvDetails(){
        return environment.toString();
    }
}