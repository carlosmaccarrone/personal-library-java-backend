package com.dev.maccarrone.library.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.dev.maccarrone.library.service.BookService;
import com.dev.maccarrone.library.dto.BookSummaryDTO;
import org.springframework.test.web.servlet.MockMvc;
import com.dev.maccarrone.library.dto.BookFullDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @Test
    void getAllBooks_returnsList() throws Exception {
        List<BookSummaryDTO> books = List.of(
                new BookSummaryDTO("123-456", "Book1"),
                new BookSummaryDTO("789-101", "Book2")
        );
        when(service.getAllBooksSummary()).thenReturn(books);

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].isbn").value("123-456"));
    }

    @Test
    void getBookSummaryByIsbn_returnsBook() throws Exception {
        BookSummaryDTO book = new BookSummaryDTO("123-456", "Book1");
        when(service.getBookSummaryByIsbn("123-456")).thenReturn(book);

        mockMvc.perform(get("/api/books/123-456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book1"));
    }

    @Test
    void search_returnsFullBooks() throws Exception {
        List<BookFullDTO> results = List.of(
                new BookFullDTO("123-456", "Book1", List.of("Author1"), List.of("Genre1"))
        );
        when(service.searchByTitle("Book")).thenReturn(results);

        mockMvc.perform(get("/api/books/search").param("q", "Book"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].isbn").value("123-456"));
    }

    @Test
    void allBooksFull_returnsList() throws Exception {
        List<BookFullDTO> books = List.of(
                new BookFullDTO("123-456", "Book1", List.of("Author1"), List.of("Genre1"))
        );
        when(service.getAllBooksFull()).thenReturn(books);

        mockMvc.perform(get("/api/books/full"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void bookFullByIsbn_returnsBook() throws Exception {
        BookFullDTO book = new BookFullDTO("123-456", "Book1", List.of("Author1"), List.of("Genre1"));
        when(service.getBookFullByIsbn("123-456")).thenReturn(book);

        mockMvc.perform(get("/api/books/123-456/full"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("123-456"));
    }

    @Test
    void createBook_returnsCreated() throws Exception {
        BookSummaryDTO book = new BookSummaryDTO("123-456", "Book1");
        when(service.createBook(any(BookSummaryDTO.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isbn\":\"123-456\",\"title\":\"Book1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isbn").value("123-456"));
    }

    @Test
    void updateBook_returnsUpdated() throws Exception {
        BookSummaryDTO book = new BookSummaryDTO("123-456", "Updated Book");
        when(service.updateBook(eq("123-456"), any(BookSummaryDTO.class))).thenReturn(book);

        mockMvc.perform(put("/api/books/123-456")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"isbn\":\"123-456\",\"title\":\"Updated Book\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"));
    }

    @Test
    void deleteBook_returnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/books/123-456"))
                .andExpect(status().isNoContent());
    }
}