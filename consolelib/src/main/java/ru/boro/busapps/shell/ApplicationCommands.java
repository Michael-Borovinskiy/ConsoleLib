package ru.boro.busapps.shell;


import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.boro.busapps.domain.Author;
import ru.boro.busapps.domain.Book;
import ru.boro.busapps.domain.Comment;
import ru.boro.busapps.domain.Genre;
import ru.boro.busapps.dto.BookDto;
import ru.boro.busapps.exception.NullDataException;
import ru.boro.busapps.service.LibService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {

    private final LibService libService;

    @ShellMethod(value = "Get Book by ID", key = {"getBookById", "gBID"})
    public Book getBookById(@ShellOption long id
    ) throws NullDataException {
        return libService.findById(id);
    }

    @ShellMethod(value = "Get All Books", key = {"getBooks", "gB"})
    public List<Book> getAllBooks(
    ) {
        return libService.findAllBooks();
    }

    @ShellMethod(value = "Get All Authors", key = {"getAuthors", "gA"})
    public List<Author> getAllAuthors(
    ) {
        return libService.findAllAuthors();
    }

    @ShellMethod(value = "Get All Genres", key = {"getGenres", "gG"})
    public List<Genre> getAllGenres(
    ) {
        return libService.findAllGenres();
    }

    @ShellMethod(value = "Get Auth by ID", key = {"getAuthById", "gAID"})
    public Author getAuthById(@ShellOption long id
    ) throws NullDataException {
        return libService.findAuthorById(id);
    }

    @ShellMethod(value = "Get All Books Info", key = {"getBooksInfo", "gBInf"})
    public List<BookDto> getAllBooksInfo(
    ) {
        return libService.findAllBookMainInfo();
    }

    @ShellMethod(value = "Get Books by Author FullName", key = {"gBbyFullName"})
    public List<Book> getBookByAuthFullName(@ShellOption String fullName
    ) {
        return libService.findBookByAuthorFullName(fullName);
    }

    @ShellMethod(value = "Get Books by GenreName", key = {"gBbyGenreName"})
    public List<Book> getBookByGenreName(@ShellOption String genreName
    ) {
        return libService.findBookByGenreName(genreName);
    }

    @ShellMethod(value = "Save Book  enter - title, authorName, genreName", key = {"saveBook", "saveB"})
    public Book saveBook(@ShellOption String title, @ShellOption String authorName, @ShellOption String genreName) {

        return libService.saveBook(title, authorName, genreName);
    }

    @ShellMethod(value = "Save new Comment enter - commentText idBook", key = {"saveComment", "saveC"})
    public Comment saveComment(@ShellOption String commentText, @ShellOption long idBook
    ) throws NullDataException {
        return libService.saveComment(commentText, idBook);
    }

    @ShellMethod(value = "Get Comments by idBook enter - idBook", key = {"getComments", "getC"})
    public List<Comment> getComments(@ShellOption long idBook
    ) throws NullDataException {
        return libService.findAllCommentsByBook(idBook);
    }

    @ShellMethod(value = "Get Auth by Name", key = {"getAuthByName", "gAName"})
    public Author getAuthByName(@ShellOption String name
    ) throws NullDataException {
        return libService.findAuthorByName(name);
    }

    @ShellMethod(value = "Update Book Title enter - id newTitle", key = {"updateBookTitle", "updTitleBook"})
    public void updateBookTitle(@ShellOption long id, @ShellOption String title
    ) {
        libService.updateBookTitle(id, title);
    }

    @ShellMethod(value = "Delete Book By ID", key = {"deleteBook", "deleteB"})
    public void deleteBook(@ShellOption long id) {
        libService.deleteBookById(id);
    }
}

