package com.example.demo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/api")
    public Response api() {
        return new Response("success",
                new Quote(counter.incrementAndGet(),
                        "Really loving Spring Boot, makes stand alone Spring apps easy."));
    }
}
