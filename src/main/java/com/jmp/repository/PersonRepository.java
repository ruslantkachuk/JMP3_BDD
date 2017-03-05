package com.jmp.repository;

import org.springframework.data.repository.CrudRepository;

import com.jmp.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}
