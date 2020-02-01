package com.java;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "ping")
public class PingController {
    @RequestMapping
    public String ping(){
        return "pong";
    }
}
