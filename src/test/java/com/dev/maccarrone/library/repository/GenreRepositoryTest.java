package com.dev.maccarrone.library.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;
import com.dev.maccarrone.library.model.Genre;
import org.junit.jupiter.api.Test;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
class GenreRepositoryTest {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    void basicSaveAndFind() {
        Genre genre = new Genre();
        genre.setGenreName("Test Genre");
        genreRepository.save(genre);

        assertTrue(genreRepository.findById(genre.getGenreId()).isPresent());
    }

    @Test
    void findAllGenres() {
        Genre rock = new Genre();
        rock.setGenreName("Rock");
        genreRepository.save(rock);
        Genre jazz = new Genre();
        jazz.setGenreName("Jazz");
        genreRepository.save(jazz);

        List<Genre> genres = genreRepository.findAll();
        assertEquals(2, genres.size());
    }

    @Test
    void updateGenreName() {
        Genre genre = new Genre();
        genre.setGenreName("Old Name");
        genre = genreRepository.save(genre);

        genre.setGenreName("New Name");
        genreRepository.save(genre);

        Genre updated = genreRepository.findById(genre.getGenreId()).orElseThrow();
        assertEquals("New Name", updated.getGenreName());
    }

    @Test
    void deleteGenre() {
        Genre genre = new Genre();
        genre.setGenreName("To Delete");
        genre = genreRepository.save(genre);

        genreRepository.deleteById(genre.getGenreId());
        assertFalse(genreRepository.findById(genre.getGenreId()).isPresent());
    }
}