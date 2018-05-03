package com.example.versioning.api.v2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/")
public class V2BController {
    @RequestMapping("b")
    public String method() {
        return "b of v2";
    }
}