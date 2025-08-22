package com.dev.maccarrone.library.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

class BookFullDTOTest {

    @Test
    void createAndGetValues() {
        List<String> authors = List.of("Author One", "Author Two");
        List<String> genres = List.of("Fiction", "Adventure");

        BookFullDTO dto = new BookFullDTO("123-456", "Test Book", authors, genres);

        assertEquals("123-456", dto.isbn());
        assertEquals("Test Book", dto.title());
        assertEquals(authors, dto.authors());
        assertEquals(genres, dto.genres());
    }
}