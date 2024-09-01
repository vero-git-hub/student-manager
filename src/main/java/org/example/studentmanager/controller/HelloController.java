package org.example.studentmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vero-git-hub
 **/
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Swagger!";
    }
}
