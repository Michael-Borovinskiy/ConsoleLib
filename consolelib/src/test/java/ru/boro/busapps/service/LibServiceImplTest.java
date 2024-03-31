package ru.boro.busapps.service;

import lombok.val;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.boro.busapps.repo.AuthorRepository;
import ru.boro.busapps.repo.GenreRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Сервис библиотеки книг")
@DataJpaTest
@Import(LibServiceImpl.class)
public class LibServiceImplTest {

    private static final int EXPECTED_NUMBER_OF_BOOKS = 3;
    private static final long EXPECTED_QUERIES_COUNT = 1;


    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private LibServiceImpl libService;
    @Autowired
    private TestEntityManager em;



    @DisplayName("Проверка корректного сохранения автора по имени единожды при неоднократной записи книги с одним автором")
    @Test
    void shouldSaveOnlyOneAuthorByName() {

        var expectedCount = authorRepository.findAll().size();
        libService.saveBook("New Fantasics2","Bronte","Novel");
        var actualCount = authorRepository.findAll().size();
        assertThat(actualCount).isEqualTo(expectedCount);

    }

    @DisplayName("Проверка корректного сохранения жанра по названию единожды при неоднократной записи книги с одним жанром")
    @Test
    void shouldSaveOnlyOneGenreByName() {

        var expectedCount = genreRepository.findAll().size();
        libService.saveBook("New Novel2","Bronte","Novel");
        var actualCount = genreRepository.findAll().size();
        assertThat(actualCount).isEqualTo(expectedCount);

    }

    @DisplayName("должен загружать список всех книг с основной информацией о них")
    @Test
    void shouldReturnCorrectListWithAllInfo() {
        val books = libService.findAllBookMainInfo();
        AssertionsForInterfaceTypes.assertThat(books).hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(s -> !s.getTitle().equals(""))
                .allMatch(s -> s.getAuthor() != null)
                .allMatch(s -> s.getGenre() != null);

        books.forEach(System.out::println);

    }

    @DisplayName("Возвратит ожидаемое кол-во запросов к БД = 1")
    @Test
    void shouldReturnCorrectStatementCount() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory().unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        libService.findAllBookMainInfo();

        assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }



}
