package ru.boro.busapps.repo;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.boro.busapps.domain.Author;
import ru.boro.busapps.domain.Book;
import ru.boro.busapps.domain.Genre;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Репозиторий для работы с книгами")
@DataJpaTest
public class BookRepositoryTest {

    private static final long EXPECTED_COUNT = 3;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;


    @DisplayName("Кол-во возвращаемых книг совпадает с данными из dao")
    @Test
    void shouldReturnBooksCnt() {
        List<Book> listBooks = bookRepository.findAll();
        assertThat(listBooks.size()).isEqualTo(EXPECTED_COUNT);
    }

    @DisplayName("Возврат книги по id")
    @Test
    void shouldReturnBookById() {
        val expectedBook = em.find(Book.class, 3L);
        val actualBook = bookRepository.findById(3L).orElse(new Book());
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @DisplayName("Проверка добавления книги в БД")
    @Test
    void shouldInsertBook() {
        var savedBook = bookRepository.save(new Book(5L, "New Fantasics", new Author(2L, "Bronte"), new Genre(2L, "Novel")));
        var actualBook = em.find(Book.class, savedBook.getId());
        assertThat(actualBook).isEqualTo(savedBook);
    }

    @DisplayName("Возврат всех книг")
    @Test
    void shouldReturnBooks() {
        var actualBooks = bookRepository.findAll();
        Book book1 = em.find(Book.class, 1L);
        Book book2 = em.find(Book.class, 2L);
        Book book3 = em.find(Book.class, 3L);

        var booksListExpected =
                List.of(book1,
                        book2,
                        book3);
        Assertions.assertThat(actualBooks).containsExactlyElementsOf(booksListExpected);
    }

}
