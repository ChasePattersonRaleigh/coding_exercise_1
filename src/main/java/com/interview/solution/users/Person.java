package com.interview.solution.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Person {
    @Id 
    public String id;

    @Indexed(name = "ageIndex")
    private int age;
    @Indexed
    private String firstName;
    @Indexed
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;


    public Person() { 

    }

    public Person(String firstName, String lastName) { 
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
      return String.format(
          "Person[id=%s, firstName='%s', lastName='%s']",
          id, firstName, lastName);
    }
}
