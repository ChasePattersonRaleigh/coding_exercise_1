package com.interview.solution.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/people")
public class PersonController {
    private static final int MAX_TEST_DATA_SIZE = 1000000;
    
    @Autowired
	private PersonService personService;

    
    @PostMapping("/generate")
    public ResponseEntity<String> generateData(@RequestParam(name="size", defaultValue="1000") int size) {
        if (size > MAX_TEST_DATA_SIZE) {
            String errorMsg = String.format("size parameter set to %s. This exceeds the limit of %s.", size, MAX_TEST_DATA_SIZE);
            log.error(errorMsg);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
        }

        try {
            personService.generateData(size);
        } catch(Exception e) {
            String errorMsg = "Error generating data";
            log.error(errorMsg, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMsg);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Data successfully created");
    }
}
