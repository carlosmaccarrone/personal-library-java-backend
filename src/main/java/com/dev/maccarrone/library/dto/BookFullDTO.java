package com.dev.maccarrone.library.dto;

import java.util.List;

public record BookFullDTO(
    String isbn,
    String title,
    List<String> authors,
    List<String> genres
) {}