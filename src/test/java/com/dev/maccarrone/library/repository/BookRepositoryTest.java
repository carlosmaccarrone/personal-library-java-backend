package com.dev.maccarrone.library.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;
import com.dev.maccarrone.library.model.Book;
import org.junit.jupiter.api.Test;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void basicSaveAndFind() {
        Book book = new Book();
        book.setIsbn("123-456");
        book.setTitle("Test Book");
        bookRepository.save(book);

        assertTrue(bookRepository.findById("123-456").isPresent());
    }

    @Test
    void findAllBooks() {
        bookRepository.save(new Book("111-111", "Book One"));
        bookRepository.save(new Book("222-222", "Book Two"));

        List<Book> books = bookRepository.findAll();
        assertEquals(2, books.size());
    }

    @Test
    void updateBookTitle() {
        Book book = new Book("333-333", "Old Title");
        bookRepository.save(book);

        book.setTitle("New Title");
        bookRepository.save(book);

        Book updated = bookRepository.findById("333-333").orElseThrow();
        assertEquals("New Title", updated.getTitle());
    }

    @Test
    void deleteBook() {
        Book book = new Book("444-444", "To Delete");
        bookRepository.save(book);

        bookRepository.deleteById("444-444");
        assertFalse(bookRepository.findById("444-444").isPresent());
    }
}