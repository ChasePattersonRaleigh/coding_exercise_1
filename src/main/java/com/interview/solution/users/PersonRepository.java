package com.interview.solution.users;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends MongoRepository<Person, String> {
    
    public List<Person> findByLastName(@Param("name") String lastName, Pageable pageable);
    public List<Person> findByAge(int age, Pageable pageable);
    public List<Person> findByLastNameAndAge(@Param("name") String lastName, int age, Pageable pageable);

    // Not required.
    // public List<Person> findByFirstNameAndLastName(Optional<String> firstName, Optional<String> lastName, Sort sort);
    // public List<Person> findByFirstName(@Param("name") String firstName);
}
