package com.jmp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Person {
    @Id()
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
