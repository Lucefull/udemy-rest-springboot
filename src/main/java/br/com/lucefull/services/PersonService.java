package br.com.lucefull.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.lucefull.model.Person;

@Service
public class PersonService {
    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll() {
        logger.info("Finding all Persons");
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            persons.add(mockPersons(i));
        }
        return persons;
    }

    public Person findById(String id) {

        logger.info("Finding one Person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Leandro");
        person.setLastName("Costa");
        person.setAddress("Uberlandia - MG");
        person.setGender("M");
        return person;
    }

    public Person create(Person person) {
        logger.info("Creating one Person");
        return person;
    }

    public Person update(Person person) {
        logger.info("Updating one Person");
        return person;
    }

    public void delete(String id) {
        logger.info("Updating one Person");

    }

    private Person mockPersons(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("firstName " + i);
        person.setLastName("lastName " + i);
        person.setAddress("some address " + i);
        person.setGender("M");
        return person;
    }
}
