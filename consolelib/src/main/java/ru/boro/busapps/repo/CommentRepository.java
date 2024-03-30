package ru.boro.busapps.repo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.boro.busapps.domain.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment save(Comment comment);

    @EntityGraph(attributePaths = {"book"})
    @Query("select c from Comment c where c.book.id= :idBook")
    List<Comment> findCommentsByIdBook(long idBook);

    @Modifying
    @Query("delete Comment c where c.book.id = :id")
    void deleteByBookId(long id);
}
