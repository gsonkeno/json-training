package com.gsonkeno.jsontraining.controller;

import com.gsonkeno.jsontraining.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

    @GetMapping(value = "/person")
    public Person getPerson() {
        return new Person.PersonBuilder().withBirthAddress("上海-浦东")
                .withEmailAddress("hugdiad@163.com").withName("刘畅").build();
    }
}
