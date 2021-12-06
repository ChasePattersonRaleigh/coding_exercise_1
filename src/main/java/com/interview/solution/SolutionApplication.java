package com.interview.solution;

import java.util.List;

import com.interview.solution.users.Person;
import com.interview.solution.users.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolutionApplication implements CommandLineRunner {
	@Autowired
	private PersonRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(SolutionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception { 
		// repository.deleteAll();

		// // add data
		// repository.save(new Person("John", "Doe"));

		// // fetch all
		// List<Person> allCustomers = repository.findAll();
		// System.out.println("All Data: ");
		// allCustomers.forEach((x) -> System.out.println(x));
	}
}
