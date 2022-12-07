package com.edu.fit.datapersistencemongo.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public Person get(@PathVariable UUID id){
        var p = repository.findById(id);

        if(p.isPresent()) return p.get();

        return null;
    }

    @PostMapping("/")
    public Person create(@RequestBody Person person) {
        person.setId(UUID.randomUUID());
        repository.save(person);

        return person;
    }

    @PutMapping("/{id}")
    public Person update(@PathVariable UUID id, @RequestBody Person person){
        var p = repository.findById(id);

        if(p.isPresent()){

            var newPerson = p.get();

            newPerson.setName(person.getName());

            repository.save(newPerson);

            return newPerson;

        }else{
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public Person delete(@PathVariable UUID id){
        var p = repository.findById(id);
        if(p.isPresent()){

            var person = p.get();

        repository.delete(person);

        return person;

        }else{
            return null;
        }
    }


}