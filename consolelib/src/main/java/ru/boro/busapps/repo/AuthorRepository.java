package ru.boro.busapps.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.boro.busapps.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author save(Author author);

    Optional<Author> findAuthorById(long id);

    List<Author> findAll();

    List<Author> findAuthorByFullName(String name);
}
