package com.example.versioning.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class V1AController {
    @RequestMapping("a")
    public String method() {
        return "a of v1";
    }
}