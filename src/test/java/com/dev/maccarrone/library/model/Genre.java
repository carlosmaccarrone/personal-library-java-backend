package com.dev.maccarrone.library.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashSet;

class GenreTest {

    @Test
    void genreCreation() {
        Genre genre = new Genre("Rock");
        assertEquals("Rock", genre.getGenreName());
    }

    @Test
    void genreSetGetId() {
        Genre genre = new Genre("Jazz");
        genre.setGenreId(99L);
        assertEquals(99L, genre.getGenreId());
    }

    @Test
    void genreSetGetBooks() {
        Genre genre = new Genre("Pop");
        HashSet<Book> books = new HashSet<>();
        genre.setBooks(books);
        assertEquals(books, genre.getBooks());
    }
}