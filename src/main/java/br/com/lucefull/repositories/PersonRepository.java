package br.com.lucefull.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lucefull.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
