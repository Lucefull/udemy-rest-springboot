package br.com.lucefull.mapper.custom;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.lucefull.data.vo.v1.BookVO;
import br.com.lucefull.model.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "key", target = "id")
    Book bookVoToBook(BookVO book);

    @Mapping(source = "id", target = "key")
    BookVO bookToBookVO(Book book);

    @Mapping(source = "key", target = "id")
    List<Book> bookVoToBook(List<BookVO> book);

    @Mapping(source = "id", target = "key")
    List<BookVO> bookToBookVO(List<Book> book);

}
