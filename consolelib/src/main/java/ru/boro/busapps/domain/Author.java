package ru.boro.busapps.domain;

import lombok.*;
import ru.boro.busapps.dto.AuthorDto;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FULLNAME")
    private String fullName;

    public Author(String fullName) {
        this.fullName = fullName;
    }

    public AuthorDto toDto() {
        return new AuthorDto(this.getId(), this.getFullName());
    }
}
