package ru.boro.busapps.service;

import ru.boro.busapps.domain.Author;
import ru.boro.busapps.domain.Book;
import ru.boro.busapps.domain.Comment;
import ru.boro.busapps.domain.Genre;
import ru.boro.busapps.dto.BookDto;
import ru.boro.busapps.exception.NullDataException;

import java.util.List;

public interface LibService {

    Author findAuthorById(long id) throws NullDataException;

    Author findAuthorByName(String name) throws NullDataException;

    List<Author> findAllAuthors();

    List<Genre> findAllGenres();

    Book findById(long id) throws NullDataException;

    List<Book> findAllBooks();

    List<Book> findBookByAuthorFullName(String fullName);

    List<Book> findBookByGenreName(String genreName);

    Book saveBook(String title, String authorName, String genreName);

    void updateBookTitle(long id, String title);

    void deleteBookById(long id);

    Comment saveComment(String commentText, long idBook) throws NullDataException;

    List<Comment> findAllCommentsByBook(long idBook) throws NullDataException;

    public List<BookDto> findAllBookMainInfo();
}
