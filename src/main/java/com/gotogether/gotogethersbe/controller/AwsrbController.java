package com.gotogether.gotogethersbe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AwsrbController {

    @GetMapping("/awsrb")
    public String test() {
        return "asdasdasd";
    }

}
