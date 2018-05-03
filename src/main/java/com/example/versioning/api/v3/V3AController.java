package com.example.versioning.api.v3;

import com.example.versioning.api.v1.V1AController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v3/")
public class V3AController extends V1AController {
    @Override
    @RequestMapping("a")
    public String method() {
        return super.method() + " was overridden by v3";
    }
}