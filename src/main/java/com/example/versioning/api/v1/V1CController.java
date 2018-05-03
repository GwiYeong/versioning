package com.example.versioning.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class V1CController {
    @RequestMapping("c")
    public String method() {
        return "c of v1";
    }
}
