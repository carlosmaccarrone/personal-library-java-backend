package com.dev.maccarrone.library.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.HashSet;

class AuthorTest {

    @Test
    void authorCreation() {
        Author a = new Author("Rocanrol");
        assertEquals("Rocanrol", a.getAuthorName());
    }

    @Test
    void authorSetGetId() {
        Author a = new Author("Rocanrol");
        a.setAuthorId(42L);
        assertEquals(42L, a.getAuthorId());
    }

    @Test
    void authorSetGetBooks() {
        Author a = new Author("Rocanrol");
        HashSet<Book> books = new HashSet<>();
        a.setBooks(books);
        assertEquals(books, a.getBooks());
    }
}