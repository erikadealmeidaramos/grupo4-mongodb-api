package com.edu.fit.datapersistencemongo.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edu.fit.datapersistencemongo.models.Person;

public interface PersonRepository extends MongoRepository<Person, UUID> {
}