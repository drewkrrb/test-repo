package com.globe.restservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IsOnlineController {

    Logger logger = LoggerFactory.getLogger(IsOnlineController.class);

    @GetMapping("/isonline")
    public String IsOnline() {
        return "{\"status\":\"Is Online\"}";
    }
}
