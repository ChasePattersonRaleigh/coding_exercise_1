package com.interview.solution.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Provides business logic for handling people objects. 
 * In this case, it's only for generating test data.
 */
@Slf4j
@Service
public class PersonService {
    private static final int MAX_PEOPLE_PER_SAVE = 1000;
    private static final int MAX_TOTAL_PEOPLE_TO_GENERATE = 1000000;
    

    @Autowired
	private PersonRepository repository;
    
    /**
     * Populates the database with the given number of users. 
     * First it empties the database.
     * 
     * 
     * @param size Total number of users to create.
     * @return
     * @throws IllegalArgumentException if the size is larger than 1 million.
     */
    public int generateData(int size) {

        if (size > MAX_TOTAL_PEOPLE_TO_GENERATE) { 
            throw new IllegalArgumentException(String.format("generateData API requested with size of %s. Max allowed is %s.", size, MAX_TOTAL_PEOPLE_TO_GENERATE));
        }
        
        int numberOfPeopleSaved = 0;
        List<Person> currentPeople;
        
        try { 
            log.info("Request to generate test data started");
            log.info("Deleting test data");
            repository.deleteAll();
            log.info("Creating new data");

            try (PersonGenerator personGenerator = new PersonGenerator()) {
                boolean finished = false;
                while (!finished) {
                    int maxForCurrentLoop =  Math.min(MAX_PEOPLE_PER_SAVE,  size - numberOfPeopleSaved);
                    if (maxForCurrentLoop == 0) {
                        break;
                    }
                    currentPeople = new ArrayList<>(MAX_PEOPLE_PER_SAVE);
                    for (int i = 0; i < maxForCurrentLoop; i++) {
                        Optional<Person> nextPerson = personGenerator.getNextPerson();
                        if (nextPerson.isEmpty()) { 
                            // finished getting all of the data, break and save.
                            finished = true;
                            break;
                        }
                        currentPeople.add(nextPerson.get());
                    }
                    // save in chunks
                    if (currentPeople.size() > 0) { 
                        repository.saveAll(currentPeople);
                    }
                    numberOfPeopleSaved += currentPeople.size();
                }
            }
            log.info("Request to generate test data finished");
        } catch(Exception e) { 
            log.error("Failure while creating test data", e);
            throw new RuntimeException(e);
        } finally { 
            log.info(String.format("A total of %s users were saved in the repository", numberOfPeopleSaved));
        }
        
        return numberOfPeopleSaved;
    }
}
