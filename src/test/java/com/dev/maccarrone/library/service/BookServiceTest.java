package com.dev.maccarrone.library.service;

import com.dev.maccarrone.library.repository.BookRepository;
import com.dev.maccarrone.library.dto.BookSummaryDTO;
import com.dev.maccarrone.library.dto.BookFullDTO;
import static org.junit.jupiter.api.Assertions.*;
import com.dev.maccarrone.library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import java.util.*;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setup() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void getAllBooksSummary() {
        Book book = new Book("978-123", "Rock Classics");
        when(bookRepository.findAll()).thenReturn(List.of(book));

        var result = bookService.getAllBooksSummary();
        assertEquals(1, result.size());
        assertEquals("978-123", result.get(0).isbn());
        assertEquals("Rock Classics", result.get(0).title());
    }

    @Test
    void getBookSummaryByIsbn() {
        Book book = new Book("978-123", "Rock Classics");
        when(bookRepository.findById("978-123")).thenReturn(Optional.of(book));

        var result = bookService.getBookSummaryByIsbn("978-123");
        assertEquals("978-123", result.isbn());
        assertEquals("Rock Classics", result.title());
    }

    @Test
    void createBook() {
        BookSummaryDTO dto = new BookSummaryDTO("978-999", "New Book");
        when(bookRepository.save(any(Book.class))).thenAnswer(i -> i.getArgument(0));

        var result = bookService.createBook(dto);
        assertEquals("978-999", result.isbn());
        assertEquals("New Book", result.title());
        verify(bookRepository, times(1)).save(any(Book.class));
    }
}