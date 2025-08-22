package com.dev.maccarrone.library.service;

import com.dev.maccarrone.library.repository.AuthorRepository;
import static org.junit.jupiter.api.Assertions.*;
import com.dev.maccarrone.library.model.Author;
import com.dev.maccarrone.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.*;

class AuthorServiceTest {

    private AuthorRepository repository;
    private AuthorService service;

    @BeforeEach
    void setup() {
        repository = mock(AuthorRepository.class);
        service = new AuthorService(repository);
    }

    @Test
    void getAllAuthors() {
        List<Author> authors = List.of(new Author("Rocanrol"));
        when(repository.findAll()).thenReturn(authors);

        List<Author> result = service.getAllAuthors();
        assertEquals(1, result.size());
        assertEquals("Rocanrol", result.get(0).getAuthorName());
    }

    @Test
    void getAuthorById() {
        Author author = new Author("Rocanrol");
        author.setAuthorId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(author));

        Optional<Author> result = service.getAuthorById(1L);
        assertTrue(result.isPresent());
        assertEquals("Rocanrol", result.get().getAuthorName());
    }

    @Test
    void createAuthor() {
        Author author = new Author("Rocanrol");
        when(repository.save(author)).thenReturn(author);

        Author result = service.createAuthor(author);
        assertEquals("Rocanrol", result.getAuthorName());
        verify(repository, times(1)).save(author);
    }

    @Test
    void getBooksByAuthorId() {
        Author author = new Author("Rocanrol");
        Book book = new Book("978-123", "Rock Classics");
        author.setBooks(Set.of(book));

        when(repository.findById(1L)).thenReturn(Optional.of(author));

        Set<Book> books = service.getBooksByAuthorId(1L);
        assertEquals(1, books.size());
        assertTrue(books.contains(book));
    }
}