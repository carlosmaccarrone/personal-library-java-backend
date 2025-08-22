package com.dev.maccarrone.library.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashSet;

class BookTest {

    @Test
    void bookCreation() {
        Book book = new Book("978-1234567890", "Rock Classics");
        assertEquals("978-1234567890", book.getIsbn());
        assertEquals("Rock Classics", book.getTitle());
    }

    @Test
    void bookSetGetAuthors() {
        Book book = new Book("978-1234567890", "Rock Classics");
        HashSet<Author> authors = new HashSet<>();
        book.setAuthors(authors);
        assertEquals(authors, book.getAuthors());
    }

    @Test
    void bookSetGetGenres() {
        Book book = new Book("978-1234567890", "Rock Classics");
        HashSet<Genre> genres = new HashSet<>();
        book.setGenres(genres);
        assertEquals(genres, book.getGenres());
    }
}