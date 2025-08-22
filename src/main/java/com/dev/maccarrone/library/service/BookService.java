package com.dev.maccarrone.library.service;

import org.springframework.transaction.annotation.Transactional;
import com.dev.maccarrone.library.repository.BookRepository;
import com.dev.maccarrone.library.dto.BookSummaryDTO;
import com.dev.maccarrone.library.dto.BookFullDTO;
import org.springframework.stereotype.Service;
import com.dev.maccarrone.library.model.Book;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookSummaryDTO> getAllBooksSummary() {
        return bookRepository.findAll().stream()
            .map(b -> new BookSummaryDTO(b.getIsbn(), b.getTitle()))
            .toList();
    }

    public BookSummaryDTO getBookSummaryByIsbn(String isbn) {
        Book b = bookRepository.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return new BookSummaryDTO(b.getIsbn(), b.getTitle());
    }

    @Transactional(readOnly = true)
    public List<BookFullDTO> getAllBooksFull() {
        return bookRepository.findAll().stream()
                .map(book -> new BookFullDTO(
                    book.getIsbn(),
                    book.getTitle(),
                    book.getAuthors().stream().map(a -> a.getAuthorName()).toList(),
                    book.getGenres().stream().map(g -> g.getGenreName()).toList()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public BookFullDTO getBookFullByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return new BookFullDTO(
                book.getIsbn(),
                book.getTitle(),
                book.getAuthors().stream().map(a -> a.getAuthorName()).toList(),
                book.getGenres().stream().map(g -> g.getGenreName()).toList()
        );
    }

    public List<BookFullDTO> searchByTitle(String term){
        return bookRepository.findByTitleContainingIgnoreCase(term).stream()
            .map(b -> new BookFullDTO(
                b.getIsbn(),
                b.getTitle(),
                b.getAuthors().stream().map(a -> a.getAuthorName()).toList(),
                b.getGenres().stream().map(g -> g.getGenreName()).toList()
            ))
            .toList();
    }

    public BookSummaryDTO createBook(BookSummaryDTO dto) {
        Book book = new Book();
        book.setIsbn(dto.isbn());
        book.setTitle(dto.title());
        bookRepository.save(book);
        return new BookSummaryDTO(book.getIsbn(), book.getTitle());
    }

    public BookSummaryDTO updateBook(String isbn, BookSummaryDTO dto) {
        Book book = bookRepository.findById(isbn)
                        .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setTitle(dto.title());
        bookRepository.save(book);
        return new BookSummaryDTO(book.getIsbn(), book.getTitle());
    }

    public void deleteBook(String isbn) {
        if (!bookRepository.existsById(isbn)) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(isbn);
    }    
}