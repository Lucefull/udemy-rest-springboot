package br.com.lucefull.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.lucefull.data.vo.v1.BookVO;
import br.com.lucefull.model.Book;

public class MockBook {
    public Book mockEntity() {
        return mockEntity(0);
    }

    public BookVO mockVo() {
        return mockVO(0);
    }

    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> books = new ArrayList<BookVO>();
        for (int i = 0; i < 14; i++) {
            books.add(mockVO(i));
        }
        return books;
    }

    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setAuthor("Author test " + number);
        book.setLaunchDate(new Date(1999, 9, 28));
        book.setPrice(10.0);
        book.setTitle("Teste " + number);
        book.setId(number.longValue());
        return book;
    }

    public BookVO mockVO(Integer number) {
        BookVO book = new BookVO();
        book.setAuthor("Author test " + number);
        book.setLaunchDate(new Date(1999, 9, 28));
        book.setPrice(10.0);
        book.setTitle("Teste " + number);
        book.setKey(number.longValue());
        return book;
    }
}
