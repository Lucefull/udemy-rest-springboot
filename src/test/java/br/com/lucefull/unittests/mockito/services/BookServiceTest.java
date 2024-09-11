package br.com.lucefull.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.lucefull.data.vo.v1.BookVO;
import br.com.lucefull.exceptions.RequiredObjectIsNullException;
import br.com.lucefull.model.Book;
import br.com.lucefull.repositories.BookRepository;
import br.com.lucefull.services.BookService;
import br.com.lucefull.unittests.mapper.mocks.MockBook;

public class BookServiceTest {

    MockBook input;

    @InjectMocks
    private BookService service;
    @Mock
    BookRepository bookRepository;

    @BeforeEach
    void setUpMocks() {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Book entity = input.mockEntity();
        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(bookRepository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

        assertEquals("Author test 1", result.getAuthor());
        assertEquals(10.0, result.getPrice());
        assertEquals(new Date(1999, 9, 28), result.getLaunchDate());
        assertEquals("Teste 1", result.getTitle());
    }

    @Test
    void testCreateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });
        String expectedMessage = "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDelete() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));

        service.delete(1L);
    }

    @Test
    void testFindAll() {
        List<Book> books = input.mockEntityList();
        when(bookRepository.findAll()).thenReturn(books);

        List<BookVO> vo = service.findAll();
        assertNotNull(vo);
        assertEquals(vo.size(), 14);

        var result = vo.get(0);

        assertTrue(result.toString().contains("links: [</api/book/v1/0>;rel=\"self\"]"));
        assertEquals("Author test 0", result.getAuthor());
        assertEquals(10.0, result.getPrice());
        assertEquals(new Date(1999, 9, 28), result.getLaunchDate());
        assertEquals("Teste 0", result.getTitle());

        result = vo.get(1);

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author test 1", result.getAuthor());
        assertEquals(10.0, result.getPrice());
        assertEquals(new Date(1999, 9, 28), result.getLaunchDate());
        assertEquals("Teste 1", result.getTitle());

        result = vo.get(2);

        assertTrue(result.toString().contains("links: [</api/book/v1/2>;rel=\"self\"]"));
        assertEquals("Author test 2", result.getAuthor());
        assertEquals(10.0, result.getPrice());
        assertEquals(new Date(1999, 9, 28), result.getLaunchDate());
        assertEquals("Teste 2", result.getTitle());

        result = vo.get(3);

        assertTrue(result.toString().contains("links: [</api/book/v1/3>;rel=\"self\"]"));
        assertEquals("Author test 3", result.getAuthor());
        assertEquals(10.0, result.getPrice());
        assertEquals(new Date(1999, 9, 28), result.getLaunchDate());
        assertEquals("Teste 3", result.getTitle());
    }

    @Test
    void testFindById() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertEquals("Author test 1", result.getAuthor());
        assertEquals(10.0, result.getPrice());
        assertEquals(new Date(1999, 9, 28), result.getLaunchDate());
        assertEquals("Teste 1", result.getTitle());
    }

    @Test
    void testUpdate() {
        Book entity = input.mockEntity();
        Book persistence = entity;

        entity.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(bookRepository.save(entity)).thenReturn(persistence);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));

        assertEquals("Author test 1", result.getAuthor());
        assertEquals(10.0, result.getPrice());
        assertEquals(new Date(1999, 9, 28), result.getLaunchDate());
        assertEquals("Teste 1", result.getTitle());
    }
}
