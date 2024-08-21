package br.com.lucefull.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucefull.data.vo.v1.PersonVO;
import br.com.lucefull.exceptions.ResourceNotFoundException;
import br.com.lucefull.mapper.Mapper;
import br.com.lucefull.model.Person;
import br.com.lucefull.repositories.PersonRepository;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll() {
        logger.info("Finding all Persons");

        return Mapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        logger.info("Finding one Person");

        Person person = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));

        return Mapper.parseObject(person, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating one Person");
        Person p = Mapper.parseObject(person, Person.class);
        PersonVO vo = Mapper.parseObject(repository.save(p), PersonVO.class);

        return vo;
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating one Person");
        Person entity = repository
                .findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return Mapper.parseObject(repository.save(entity), PersonVO.class);
    }

    public void delete(Long id) {
        logger.info("Updating one Person");
        Person entity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        repository.delete(entity);
    }

}
