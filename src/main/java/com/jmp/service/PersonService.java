package com.jmp.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.jmp.dto.PersonDto;
import com.jmp.entity.Person;
import com.jmp.repository.PersonRepository;

//@Service
@Component
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonDto find(int id) {
        Person person = personRepository.findOne(id);
        PersonDto personDto = new PersonDto();
        BeanUtils.copyProperties(person, personDto);
        return personDto;
    }

    public void create(PersonDto personDto) {
        Person person = new Person();
        BeanUtils.copyProperties(personDto, person);
        personRepository.save(person);
    }

    @Transactional
    public void update(@RequestBody PersonDto personDto) {
        Person person = personRepository.findOne(personDto.getId());
        BeanUtils.copyProperties(personDto, person);
    }

    public void delete(int id) {
        personRepository.delete(id);
    }
}
