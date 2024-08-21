package br.com.lucefull.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucefull.exceptions.ResourceNotFoundException;
import br.com.lucefull.model.Person;
import br.com.lucefull.repositories.PersonRepository;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll() {
        logger.info("Finding all Persons");

        return repository.findAll();
    }

    public Person findById(Long id) {

        logger.info("Finding one Person");

        return repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
    }

    public Person create(Person person) {
        logger.info("Creating one Person");

        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one Person");
        Person entity = repository
                .findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(entity);
    }

    public void delete(Long id) {
        logger.info("Updating one Person");
        Person entity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        repository.delete(entity);
    }

}
