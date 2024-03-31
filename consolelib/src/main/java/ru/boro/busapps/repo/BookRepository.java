package ru.boro.busapps.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.boro.busapps.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(long id);

    @Query("select b from Book b join fetch b.author join fetch b.genre")
    List<Book> findAll();

    @Query("select b from Book b  join fetch b.author where b.author.fullName = :fullName")
    List<Book> findByAuthorFullName(String fullName);

    @Query("select b from Book b  join fetch  b.genre where b.genre.genreName = :genreName")
    List<Book> findByGenreName(String genreName);

    Book save(Book book);

    @Modifying
    @Query("update Book b set b.title = :title where b.id = :id")
    void updateBookTitleById(@Param("id") long id, @Param("title") String title);

    void deleteById(long id);
}
