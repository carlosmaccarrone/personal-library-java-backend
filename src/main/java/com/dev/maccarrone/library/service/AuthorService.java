package com.dev.maccarrone.library.service;

import org.springframework.transaction.annotation.Transactional;
import com.dev.maccarrone.library.repository.AuthorRepository;
import com.dev.maccarrone.library.model.Author;
import org.springframework.stereotype.Service;
import com.dev.maccarrone.library.model.Book;
import java.util.Optional;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return repository.findById(id);
    }

    public Author createAuthor(Author author) {
        return repository.save(author);
    }

    @Transactional(readOnly = true)
    public Set<Book> getBooksByAuthorId(Long id) {
        return repository.findById(id)
            .map(author -> {
                Set<Book> books = author.getBooks();
                books.forEach(book -> book.getGenres().size()); // inicializa genres
                return books;
            })
            .orElseThrow(() -> new RuntimeException("Author not found"));
    }
}