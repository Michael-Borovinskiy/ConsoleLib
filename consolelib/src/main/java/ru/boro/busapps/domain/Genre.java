package ru.boro.busapps.domain;

import lombok.*;
import ru.boro.busapps.dto.GenreDto;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "GENRENAME")
    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public GenreDto toDto() {
        return new GenreDto(this.getId(), this.getGenreName());
    }
}
