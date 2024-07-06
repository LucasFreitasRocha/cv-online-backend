package io.github.lucasfreitasrocha.cv_online_backend.entrypoint.person.controller;

import io.github.lucasfreitasrocha.cv_online_backend.entrypoint.person.dto.in.CreatePerson;
import io.github.lucasfreitasrocha.cv_online_backend.entrypoint.person.dto.out.PersonCreated;
import io.github.lucasfreitasrocha.cv_online_backend.entrypoint.person.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {
    private final PersonService service;

    @PostMapping
    public ResponseEntity<PersonCreated> createPerson(@RequestBody CreatePerson createPerson){
        return ResponseEntity.ok(service.createPerson(createPerson));
    }
}
