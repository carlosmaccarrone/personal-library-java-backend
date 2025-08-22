package com.dev.maccarrone.library.controller;

import com.dev.maccarrone.library.service.BookService;
import com.dev.maccarrone.library.dto.BookSummaryDTO;
import com.dev.maccarrone.library.dto.BookFullDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService svc;
    public BookController(BookService svc){ this.svc = svc; }

    @GetMapping
    public List<BookSummaryDTO> getAllBooks() {
        return svc.getAllBooksSummary();
    }

    @GetMapping("/{isbn}")
    public BookSummaryDTO getBookSummaryByIsbn(@PathVariable String isbn) {
        return svc.getBookSummaryByIsbn(isbn);
    }

    @GetMapping("/search")
    public List<BookFullDTO> search(@RequestParam("q") String q){ 
        return svc.searchByTitle(q); 
    }

    @GetMapping("/full")
    public List<BookFullDTO> allBooksFull() {
        return svc.getAllBooksFull();
    }

    @GetMapping("/{isbn}/full")
    public BookFullDTO bookFull(@PathVariable String isbn) {
        return svc.getBookFullByIsbn(isbn);
    }

    @PostMapping
    public ResponseEntity<BookSummaryDTO> createBook(@RequestBody BookSummaryDTO bookDto) {
        BookSummaryDTO created = svc.createBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<BookSummaryDTO> updateBook(
            @PathVariable String isbn,
            @RequestBody BookSummaryDTO bookDto) {
        BookSummaryDTO updated = svc.updateBook(isbn, bookDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        svc.deleteBook(isbn);
        return ResponseEntity.noContent().build();
    }
}