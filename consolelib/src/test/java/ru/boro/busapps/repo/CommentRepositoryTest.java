package ru.boro.busapps.repo;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.boro.busapps.domain.Book;
import ru.boro.busapps.domain.Comment;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Репозиторий для работы с комментами")
@DataJpaTest
public class CommentRepositoryTest {

    private static final long EXPECTED_COUNT = 2;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager em;

    @DisplayName("Проверка добавления комментария в БД")
    @Test
    void shouldInsertComment() {
        var savedBook = commentRepository.save(new Comment("good", new Book()));
        var actualBook = commentRepository.findById(3L).orElse(new Comment());
        assertThat(actualBook).isEqualTo(savedBook);
    }

    @DisplayName("Возврат комментариев по id книги")
    @Test
    void shouldReturnCommentsById() {

        val actualComments = commentRepository.findCommentsByIdBook(3L);
        Comment comment1 = em.find(Comment.class, 2L);
        List<Comment> expectedComments = List.of(comment1);

        Assertions.assertThat(actualComments).containsExactlyElementsOf(expectedComments);
    }

}
