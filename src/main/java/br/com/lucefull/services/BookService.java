package br.com.lucefull.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.lucefull.controllers.BooksController;
import br.com.lucefull.data.vo.v1.BookVO;
import br.com.lucefull.exceptions.RequiredObjectIsNullException;
import br.com.lucefull.exceptions.ResourceNotFoundException;
import br.com.lucefull.mapper.custom.BookMapper;
import br.com.lucefull.model.Book;
import br.com.lucefull.repositories.BookRepository;

@Service
public class BookService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    BookRepository repository;

    public List<BookVO> findAll() {
        logger.info("Finding all books");

        List<BookVO> books = BookMapper.INSTANCE.bookToBookVO(repository.findAll());

        books.stream().forEach(e -> {
            e.add(linkTo(methodOn(BooksController.class).findById(e.getKey())).withSelfRel());
        });
        return books;
    }

    public BookVO findById(Long id) {
        logger.info("Find one Book");
        Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));

        BookVO vo = BookMapper.INSTANCE.bookToBookVO(book);
        vo.add(linkTo(methodOn(BooksController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO vo) {
        logger.info("Creating a book");
        if (vo == null)
            throw new RequiredObjectIsNullException();

        Book book = new Book();
        book.setAuthor(vo.getAuthor());
        book.setLaunchDate(vo.getLaunchDate());
        book.setPrice(vo.getPrice());
        book.setTitle(vo.getTitle());
        repository.save(book);

        vo = BookMapper.INSTANCE.bookToBookVO(book);
        vo.add(linkTo(methodOn(BooksController.class).findById(vo.getKey())).withSelfRel());

        return vo;
    }

    public BookVO update(BookVO vo) {
        if (vo == null)
            throw new RequiredObjectIsNullException();
        logger.info("Updateing a book");
        Book book = repository.findById(vo.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records for this id!"));

        book.setAuthor(vo.getAuthor());
        book.setLaunchDate(vo.getLaunchDate());
        book.setPrice(vo.getPrice());
        book.setTitle(vo.getTitle());

        BookVO result = BookMapper.INSTANCE.bookToBookVO(repository.save(book));

        result.add(linkTo(methodOn(BooksController.class).findById(result.getKey())).withSelfRel());

        return result;
    }

    public void delete(long id) {
        logger.info("Deleting a book");
        Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records for this ID!"));
        repository.delete(book);
    }
}
