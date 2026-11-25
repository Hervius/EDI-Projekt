package com.example.marketing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/marketing/hello")
    public String sayHello() {
        return "Hello from Marketing Service!";
    }

}
