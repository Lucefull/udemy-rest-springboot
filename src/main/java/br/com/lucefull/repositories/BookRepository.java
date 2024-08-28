package br.com.lucefull.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lucefull.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
