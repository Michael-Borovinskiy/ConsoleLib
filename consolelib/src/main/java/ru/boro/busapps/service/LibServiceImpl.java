package ru.boro.busapps.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.boro.busapps.domain.Author;
import ru.boro.busapps.domain.Book;
import ru.boro.busapps.domain.Comment;
import ru.boro.busapps.domain.Genre;
import ru.boro.busapps.dto.BookDto;
import ru.boro.busapps.exception.NullDataException;
import ru.boro.busapps.repo.AuthorRepository;
import ru.boro.busapps.repo.BookRepository;
import ru.boro.busapps.repo.CommentRepository;
import ru.boro.busapps.repo.GenreRepository;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibServiceImpl implements LibService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    public Book findById(long id) throws NullDataException {
        return bookRepository.findById(id).orElseThrow(() -> new NullDataException(MessageFormat.format("Книга по ID {0} не найден", id)));
    }

    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<BookDto> findAllBookMainInfo() {
        return bookRepository.findAll().stream().map(Book::toDto).collect(Collectors.toList());
    }

    @Override
    public List<Book> findBookByAuthorFullName(String fullName) {
        return bookRepository.findByAuthorFullName(fullName);
    }

    @Override
    public List<Book> findBookByGenreName(String genreName) {
        return bookRepository.findByGenreName(genreName);
    }

    @Transactional
    public Book saveBook(String title, String authorName, String genreName) {

        var author = authorRepository.findAuthorByFullName(authorName).stream()
                .findFirst()
                .orElseGet(() -> authorRepository.save(new Author(authorName)));

        var genre = genreRepository.findGenreByGenreName(genreName).stream()
                .findFirst()
                .orElseGet(() -> genreRepository.save(new Genre(genreName)));

        return bookRepository.save(new Book(title, author, genre));
    }

    @Transactional
    public Comment saveComment(String commentText, long idBook) throws NullDataException { //ок

        Book book = bookRepository.findById(idBook).orElseThrow(() -> new NullDataException(MessageFormat.format("Книга по ID {0} не найдена", idBook)));
        return commentRepository.save(new Comment(commentText, book));
    }


    public List<Comment> findAllCommentsByBook(long idBook) {
        return commentRepository.findCommentsByIdBook(idBook);
    }

    @Transactional
    public void updateBookTitle(long id, String title) {
        bookRepository.updateBookTitleById(id, title);
    }

    @Transactional
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }


    public List<Author> findAllAuthors() { //ок
        return authorRepository.findAll();
    }

    public List<Genre> findAllGenres() { //ок
        return genreRepository.findAll();
    }

    public Author findAuthorById(long id) throws NullDataException {
        return authorRepository.findAuthorById(id).orElseThrow(() -> new NullDataException(MessageFormat.format("Автор по ID {0} не найден", id)));
    }

    public Author findAuthorByName(String name) throws NullDataException {
        return authorRepository.findAuthorByFullName(name).stream().findFirst().orElseThrow(() -> new NullDataException(MessageFormat.format("Автор по имени {0} не найден", name)));
    }

}
