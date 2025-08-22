package com.dev.maccarrone.library.controller;

import com.dev.maccarrone.library.service.AuthorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity; 
import com.dev.maccarrone.library.model.Author;
import com.dev.maccarrone.library.model.Book;
import java.util.Optional;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Author> getAllAuthors() {
        return service.getAllAuthors();
    }

	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
	    return service.getAuthorById(id)
	            .map(ResponseEntity::ok)
	            .orElse(ResponseEntity.notFound().build());
	}

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        Author saved = service.createAuthor(author);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}/books")
    public ResponseEntity<Set<Book>> getBooksByAuthor(@PathVariable Long id) {
        try {
            Set<Book> books = service.getBooksByAuthorId(id);
            return ResponseEntity.ok(books);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}