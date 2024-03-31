package ru.boro.busapps.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.boro.busapps.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre save(Genre author);

    Optional<Genre> findGenreById(long id);

    List<Genre> findAll();

    List<Genre> findGenreByGenreName(String name);

}
