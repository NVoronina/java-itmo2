package com.example.javaitmo2.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pwd")
public class MyController {

    @GetMapping("/secret")
    public String secretCode() {
        return "test success";
    }
}
