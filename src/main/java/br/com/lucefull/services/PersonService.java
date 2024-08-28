package br.com.lucefull.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.lucefull.controllers.PersonController;
import br.com.lucefull.data.vo.v1.PersonVO;
import br.com.lucefull.data.vo.v2.PersonVOV2;
import br.com.lucefull.exceptions.RequiredObjectIsNullException;
import br.com.lucefull.exceptions.ResourceNotFoundException;
import br.com.lucefull.mapper.custom.PersonMapper;
import br.com.lucefull.model.Person;
import br.com.lucefull.repositories.PersonRepository;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    // private final PersonMapper personMapper;
    @Autowired
    PersonRepository repository;

    // @Autowired
    // PersonMapper mapper;

    public List<PersonVO> findAll() throws Exception {
        logger.info("Finding all Persons");
        List<PersonVO> persons = PersonMapper.INSTANCE.personToPersonVO(repository.findAll());

        persons
                .stream()
                .forEach(p -> {
                    try {
                        p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                });
        return persons;
    }

    public PersonVO findById(Long id) throws Exception {

        logger.info("Finding one Person");

        Person person = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));

        PersonVO vo = PersonMapper.INSTANCE.personToPersonVO(person);
        vo.add(
                linkTo(
                        methodOn(PersonController.class)
                                .findById(vo.getKey()))
                        .withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) throws Exception {
        if (person == null)
            throw new RequiredObjectIsNullException();
        logger.info("Creating one Person");
        Person p = PersonMapper.INSTANCE.personVoToPerson(person);
        PersonVO vo = PersonMapper.INSTANCE.personToPersonVO(p);

        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("Creating one Person");
        Person p = PersonMapper.INSTANCE.personVoToPerson(person); // Mapper.parseObject(person, Person.class);
        PersonVOV2 vo = PersonMapper.INSTANCE.personToPersonVOV2(repository.save(p)); // mapper.convertEntityToVo(repository.save(p));

        return vo;
    }

    public PersonVO update(PersonVO person) throws Exception {
        if (person == null)
            throw new RequiredObjectIsNullException();
        logger.info("Updating one Person");
        Person entity = repository
                .findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        PersonVO vo = PersonMapper.INSTANCE.personToPersonVO(repository.save(entity));
        vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public PersonVOV2 updateV2(PersonVOV2 person) {
        logger.info("Updating one Person");
        Person entity = repository
                .findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());
        entity.setBirthDay(person.getBirthDay());

        return PersonMapper.INSTANCE.personToPersonVOV2(entity); // mapper.convertEntityToVo(repository.save(entity));
    }

    public void delete(Long id) {
        logger.info("Updating one Person");
        Person entity = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        repository.delete(entity);
    }

}
