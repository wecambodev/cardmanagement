package com.mbanq.cardmanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GetStartController {

    @GetMapping("/start")
    public String index() {
        return  "Starting";
    }
}