package com.trung.pharmacyservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RefreshScope
public class WelcomeController {
    @Value("${app.branch-name}")
    private String branchName;

    @Value("${app.hotline}")
    private String hotline;

    @RequestMapping
    public String welcome() {
        return "Welcome to " + branchName + " branch, call " + hotline;
    }
}
