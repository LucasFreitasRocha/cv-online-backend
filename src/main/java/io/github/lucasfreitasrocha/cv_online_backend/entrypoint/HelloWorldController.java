package io.github.lucasfreitasrocha.cv_online_backend.entrypoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {
    @GetMapping
    public String helloWorld(){
        return "Hello world!";
    }
}
