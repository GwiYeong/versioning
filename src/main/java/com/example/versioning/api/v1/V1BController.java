package com.example.versioning.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class V1BController {
    @RequestMapping("b")
    public String method() {
        return "b of v1";
    }
}
