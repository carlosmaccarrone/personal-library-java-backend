package com.dev.maccarrone.library.service;

import org.springframework.transaction.annotation.Transactional;
import com.dev.maccarrone.library.repository.GenreRepository;
import com.dev.maccarrone.library.dto.BookWithGenresDTO;
import com.dev.maccarrone.library.model.Genre;
import org.springframework.stereotype.Service;
import com.dev.maccarrone.library.model.Book;
import java.util.Optional;
import java.util.List;
import java.util.Set;

@Service
public class GenreService {
    private final GenreRepository repository;

    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public List<Genre> getAllGenres() {
        return repository.findAll();
    }

    public Genre createGenre(Genre genre) {
        return repository.save(genre);
    }

    @Transactional(readOnly = true)
    public List<BookWithGenresDTO> getBooksByGenreId(Long genreId){
        Genre genre = repository.findById(genreId)
            .orElseThrow(() -> new RuntimeException("Genre not found"));

        return genre.getBooks().stream()
            .map(book -> new BookWithGenresDTO(
                book.getIsbn(),
                book.getTitle(),
                book.getGenres().stream().map(Genre::getGenreName).toList()
            ))
            .toList();
    }
}