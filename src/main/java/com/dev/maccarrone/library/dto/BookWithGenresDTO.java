package com.dev.maccarrone.library.dto;

import java.util.List;

public record BookWithGenresDTO(String isbn, String title, List<String> genres) {}