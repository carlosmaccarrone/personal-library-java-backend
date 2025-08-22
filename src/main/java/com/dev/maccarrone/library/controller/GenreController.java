package com.dev.maccarrone.library.controller;

import com.dev.maccarrone.library.dto.BookWithGenresDTO;
import com.dev.maccarrone.library.service.GenreService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.dev.maccarrone.library.model.Genre;
import com.dev.maccarrone.library.model.Book;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService service;

    public GenreController(GenreService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Genre> getAllGenres() {
        return service.getAllGenres();
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookWithGenresDTO>> getBooksByGenre(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBooksByGenreId(id));
    }

    @PostMapping
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
        Genre saved = service.createGenre(genre);
        return ResponseEntity.ok(saved);
    }
}