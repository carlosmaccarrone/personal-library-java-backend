package com.dev.maccarrone.library.service;

import com.dev.maccarrone.library.repository.GenreRepository;
import com.dev.maccarrone.library.dto.BookWithGenresDTO;
import static org.junit.jupiter.api.Assertions.*;
import com.dev.maccarrone.library.model.Genre;
import com.dev.maccarrone.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import java.util.*;

class GenreServiceTest {

    private GenreRepository repository;
    private GenreService service;

    @BeforeEach
    void setup() {
        repository = mock(GenreRepository.class);
        service = new GenreService(repository);
    }

    @Test
    void getAllGenres() {
        Genre genre = new Genre("Rock");
        when(repository.findAll()).thenReturn(List.of(genre));

        List<Genre> result = service.getAllGenres();
        assertEquals(1, result.size());
        assertEquals("Rock", result.get(0).getGenreName());
    }

    @Test
    void createGenre() {
        Genre genre = new Genre("Jazz");
        when(repository.save(genre)).thenReturn(genre);

        Genre result = service.createGenre(genre);
        assertEquals("Jazz", result.getGenreName());
        verify(repository, times(1)).save(genre);
    }

    @Test
    void getBooksByGenreId() {
        Genre genre = new Genre("Pop");
        Book book = new Book("978-123", "Pop Hits");
        book.setGenres(Set.of(genre));
        genre.setBooks(Set.of(book));

        when(repository.findById(1L)).thenReturn(Optional.of(genre));

        List<BookWithGenresDTO> result = service.getBooksByGenreId(1L);
        assertEquals(1, result.size());
        assertEquals("978-123", result.get(0).isbn());
        assertEquals("Pop Hits", result.get(0).title());
        assertTrue(result.get(0).genres().contains("Pop"));
    }
}