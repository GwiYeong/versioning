package com.example.versioning.api.v3;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/")
public class V3BController {
    @RequestMapping("b")
    public String method() {
        return "b of v3";
    }
}
