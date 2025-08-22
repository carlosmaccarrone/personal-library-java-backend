package com.dev.maccarrone.library.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.dev.maccarrone.library.dto.BookWithGenresDTO;
import com.dev.maccarrone.library.service.GenreService;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import com.dev.maccarrone.library.model.Genre;
import static org.mockito.ArgumentMatchers.eq;
import org.springframework.http.MediaType;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;

@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService service;

    @Test
    void getAllGenres_returnsList() throws Exception {
        List<Genre> genres = List.of(new Genre("Rock"), new Genre("Jazz"));
        when(service.getAllGenres()).thenReturn(genres);

        mockMvc.perform(get("/api/genres/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].genreName").value("Rock"));
    }

    @Test
    void getBooksByGenre_returnsBooks() throws Exception {
        List<BookWithGenresDTO> books = List.of(
                new BookWithGenresDTO("123-456", "Book1", List.of("Rock"))
        );
        when(service.getBooksByGenreId(1L)).thenReturn(books);

        mockMvc.perform(get("/api/genres/1/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].isbn").value("123-456"));
    }

    @Test
    void createGenre_returnsCreatedGenre() throws Exception {
        Genre genre = new Genre("Rock");
        when(service.createGenre(any(Genre.class))).thenReturn(genre);

        mockMvc.perform(post("/api/genres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"genreName\":\"Rock\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.genreName").value("Rock"));
    }
}