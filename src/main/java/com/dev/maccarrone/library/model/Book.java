package com.dev.maccarrone.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Book {
    @Id
    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "title")
    private String title;

    @ManyToMany
    @JoinTable(name = "book_author",
        joinColumns = @JoinColumn(name = "isbn", referencedColumnName = "isbn"),
        inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "author_id"))
    @JsonIgnore
    private Set<Author> authors = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "book_genre",
        joinColumns = @JoinColumn(name = "isbn", referencedColumnName = "isbn"),
        inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    public Book() {}
    public Book(String isbn, String title) { this.isbn = isbn; this.title = title; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Set<Author> getAuthors() { return authors; }
    public void setAuthors(Set<Author> authors) { this.authors = authors; }

    public Set<Genre> getGenres() { return genres; }
    public void setGenres(Set<Genre> genres) { this.genres = genres; }
}