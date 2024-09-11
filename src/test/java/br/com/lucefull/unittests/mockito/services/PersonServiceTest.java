package br.com.lucefull.unittests.mockito.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import br.com.lucefull.data.vo.v1.PersonVO;
import br.com.lucefull.exceptions.RequiredObjectIsNullException;
import br.com.lucefull.model.Person;
import br.com.lucefull.repositories.PersonRepository;
import br.com.lucefull.services.PersonService;
import br.com.lucefull.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockPerson();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdate() throws Exception {
        Person entity = input.mockEntity();
        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(personRepository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testUpdateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void testCreateV2() {

    }

    @Test
    void testDelete() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));

        service.delete(1L);
    }

    @Test
    void testFindAll() throws Exception {
        List<Person> list = input.mockEntityList();
        // entity.setId(1L);

        when(personRepository.findAll()).thenReturn(list);

        List<PersonVO> people = service.findAll();
        assertNotNull(people);
        assertEquals(list.size(), 14);

        var peopleOne = people.get(1);

        assertNotNull(peopleOne);
        assertNotNull(peopleOne.getKey());
        assertNotNull(peopleOne.getLinks());

        assertTrue(peopleOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", peopleOne.getAddress());
        assertEquals("First Name Test1", peopleOne.getFirstName());
        assertEquals("Last Name Test1", peopleOne.getLastName());
        assertEquals("Female", peopleOne.getGender());

        peopleOne = people.get(4);

        assertNotNull(peopleOne);
        assertNotNull(peopleOne.getKey());
        assertNotNull(peopleOne.getLinks());

        assertTrue(peopleOne.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
        assertEquals("Address Test4", peopleOne.getAddress());
        assertEquals("First Name Test4", peopleOne.getFirstName());
        assertEquals("Last Name Test4", peopleOne.getLastName());
        assertEquals("Male", peopleOne.getGender());

        peopleOne = people.get(7);

        assertNotNull(peopleOne);
        assertNotNull(peopleOne.getKey());
        assertNotNull(peopleOne.getLinks());

        assertTrue(peopleOne.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
        assertEquals("Address Test7", peopleOne.getAddress());
        assertEquals("First Name Test7", peopleOne.getFirstName());
        assertEquals("Last Name Test7", peopleOne.getLastName());
        assertEquals("Female", peopleOne.getGender());

    }

    @Test
    void testFindById() throws Exception {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        System.out.println(result.toString());
        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreate() throws Exception {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        Person persisted = entity;
        persisted.setId(1L);

        PersonVO vo = input.mockVO(1);
        vo.setKey(1L);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testCreateWithNullPerson() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void testUpdateV2() {

    }
}
