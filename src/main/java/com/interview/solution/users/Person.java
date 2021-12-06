package com.interview.solution.users;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


/**
 * Instances of this class model a person object and are used to store/generate 
 * the data in the database.
 */
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
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                age == person.age && Objects.equals(firstName, person.firstName)
                && Objects.equals(lastName, person.lastName) && Objects.equals(streetAddress, person.streetAddress)
                && Objects.equals(city, person.city) && Objects.equals(state, person.state)
                && Objects.equals(zip, person.zip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, firstName, lastName, streetAddress, city, state, zip);
    }

    @Override
    public String toString() {
      return String.format(
          "Person[id=%s, firstName='%s', lastName='%s']",
          id, firstName, lastName);
    }
}
