package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String sayHello() {
        return "Hello";
    }

    @GetMapping("easteregg")
    public String sayEasteregg() {
        return "You are such a smartass...";
    }
}
