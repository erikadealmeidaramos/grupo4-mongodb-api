package com.edu.fit.datapersistencemongo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.fit.datapersistencemongo.repositories.PersonRepository;
import com.edu.fit.datapersistencemongo.models.Person;



@RestController
@RequestMapping("/people")
public class PersonController {
    @Autowired
    private PersonRepository repository;

    @GetMapping("/")
    public List<Person> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> getById(@PathVariable("id") UUID id){
        return repository.findById(id);
    }

    @PostMapping("/")
    public Person create(@RequestBody Person person) {
        person.setId(UUID.randomUUID());
        repository.save(person);

        return person;
    }

    @PutMapping("/")
    public Person update(@RequestBody Person p){
        repository.save(p);

        return p;
    }

    @DeleteMapping("/")
    public List<Person> delete(@RequestBody UUID uid){
        repository.deleteById(uid);

        return repository.findAll();
    }


}