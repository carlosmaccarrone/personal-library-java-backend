package com.dev.maccarrone.library.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.dev.maccarrone.library.service.AuthorService;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import com.dev.maccarrone.library.model.Author;
import com.dev.maccarrone.library.model.Book;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.List;
import java.util.Set;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService service;

    @Test
    void getAllAuthors_returnsList() throws Exception {
        List<Author> authors = List.of(new Author("Author1"), new Author("Author2"));
        when(service.getAllAuthors()).thenReturn(authors);

        mockMvc.perform(get("/api/authors/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].authorName").value("Author1"));
    }

    @Test
    void getAuthorById_found() throws Exception {
        Author author = new Author("Author1");
        when(service.getAuthorById(1L)).thenReturn(Optional.of(author));

        mockMvc.perform(get("/api/authors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorName").value("Author1"));
    }

    @Test
    void getAuthorById_notFound() throws Exception {
        when(service.getAuthorById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/authors/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createAuthor_success() throws Exception {
        Author author = new Author("New Author");
        when(service.createAuthor(any(Author.class))).thenReturn(author);

        mockMvc.perform(post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"authorName\":\"New Author\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorName").value("New Author"));
    }

    @Test
    void getBooksByAuthor_found() throws Exception {
        Book book1 = new Book("123-456", "Book1");
        Book book2 = new Book("789-101", "Book2");
        when(service.getBooksByAuthorId(1L)).thenReturn(Set.of(book1, book2));

        mockMvc.perform(get("/api/authors/1/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getBooksByAuthor_notFound() throws Exception {
        when(service.getBooksByAuthorId(1L)).thenThrow(new RuntimeException("Not found"));

        mockMvc.perform(get("/api/authors/1/books"))
                .andExpect(status().isNotFound());
    }
}