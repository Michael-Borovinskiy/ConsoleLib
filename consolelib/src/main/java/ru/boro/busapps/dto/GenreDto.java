package ru.boro.busapps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class GenreDto {

    private final long id;
    private final String genreName;

}
