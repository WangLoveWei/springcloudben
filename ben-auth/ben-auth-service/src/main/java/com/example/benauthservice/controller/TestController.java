package com.example.benauthservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangben
 * @Description:
 * @Date: 2020/8/7 11:19
 * @Version: 1.0
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public String test(){
        return "test";
    }
}
