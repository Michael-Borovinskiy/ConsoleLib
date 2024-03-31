package ru.boro.busapps.domain;


import lombok.*;
import ru.boro.busapps.dto.BookDto;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "TITLE")
    private String title;

    @ToString.Exclude
    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY
    )
    @JoinColumn(name = "authorid", referencedColumnName = "ID")
    private Author author;

    @ToString.Exclude
    @ManyToOne(targetEntity = Genre.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY
    )
    @JoinColumn(name = "genreid", referencedColumnName = "ID")
    private Genre genre;

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public BookDto toDto() {
        return new BookDto(this.getId(),
                this.getTitle(),
                this.getAuthor().toDto(),
                this.getGenre().toDto());
    }

}
