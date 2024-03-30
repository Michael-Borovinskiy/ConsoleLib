package ru.boro.busapps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BookDto {

    private final long id;
    private final String title;
    private final AuthorDto author;
    private final GenreDto genre;

}
