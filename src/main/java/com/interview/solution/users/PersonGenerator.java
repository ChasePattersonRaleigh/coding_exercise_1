package com.interview.solution.users;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Optional;
import java.util.zip.ZipInputStream;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Instancse of this class provide the test data.
 * 
 */
public class PersonGenerator implements AutoCloseable {
    private static final Logger logger = LoggerFactory.getLogger(PersonGenerator.class);
    private static final String FAKE_ADDRESS_NUM = "1234 ";
    private static final String FAKE_CITY = "Raleigh";

    private static final String AGE_FIELD = "age";
    private static final String FIRST_NAME_FIELD = "first";
    private static final String LAST_NAME_FIELD = "last";
    private static final String STREET_FIELD = "street";
    private static final String STATE_FIELD = "state";
    private static final String ZIP_CODE_FIELD = "zip";

    private JsonParser jsonParser;
    private InputStream is; 



    public PersonGenerator() { 
        try { 
            this.is = getClass().getResourceAsStream("/example_people.zip");
            BufferedInputStream bis = new BufferedInputStream(this.is);
            ZipInputStream zipIs = new ZipInputStream(bis, Charset.forName("UTF-8"));
            zipIs.getNextEntry();
            JsonFactory jsonFactory = new JsonFactory();
            this.jsonParser = jsonFactory.createParser(zipIs);
        } catch(Exception e) { 
            logger.error("Error creating json parser", e);
        }
       
    }

    public Optional<Person> getNextPerson() {
        Person person = new Person();
        try {
            JsonToken nextToken = this.jsonParser.nextToken();

            if (nextToken == JsonToken.END_ARRAY) {
                return Optional.empty();
            }

            while (nextToken != JsonToken.END_OBJECT) { 
                String fieldName = jsonParser.getCurrentName();

                if (AGE_FIELD.equals(fieldName)) { 
                    jsonParser.nextToken();
                    person.setAge(jsonParser.getIntValue());
                } else if (FIRST_NAME_FIELD.equals(fieldName)) { 
                    jsonParser.nextToken();
                    person.setFirstName(jsonParser.getText());
                } else if (LAST_NAME_FIELD.equals(fieldName)) { 
                    jsonParser.nextToken();
                    person.setLastName(jsonParser.getText());
                } else if (STREET_FIELD.equals(fieldName)) {
                    jsonParser.nextToken();
                    person.setStreetAddress(FAKE_ADDRESS_NUM + jsonParser.getText());
                } else if (STATE_FIELD.equals(fieldName)) {
                    jsonParser.nextToken();
                    person.setState(jsonParser.getText());
                } else if (ZIP_CODE_FIELD.equals(fieldName)) { 
                    jsonParser.nextToken();
                    person.setZip(jsonParser.getText());
                }

                nextToken = jsonParser.nextToken();
            }
            
            person.setCity(FAKE_CITY);

            return Optional.of(person);
        } catch (Exception e) {
            logger.error("Error generating a person", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() { 
        try { 
            this.jsonParser.close();
        } catch(Exception e) {
            logger.error("Error closing json parser", e);
        }

        try { 
            // Make sure the stream is closed.
            is.close();
        } catch(Exception e) { 
            logger.error("Error closing streams", e);
        }
        
    }
}
