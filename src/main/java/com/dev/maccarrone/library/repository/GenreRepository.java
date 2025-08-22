package com.dev.maccarrone.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dev.maccarrone.library.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> { }