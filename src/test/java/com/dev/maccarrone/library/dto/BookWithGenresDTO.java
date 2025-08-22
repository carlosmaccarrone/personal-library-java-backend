package com.dev.maccarrone.library.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

class BookWithGenresDTOTest {

    @Test
    void createAndGetValues() {
        BookWithGenresDTO dto = new BookWithGenresDTO("123-456", "Test Book", List.of("Rock", "Jazz"));

        assertEquals("123-456", dto.isbn());
        assertEquals("Test Book", dto.title());
        assertEquals(List.of("Rock", "Jazz"), dto.genres());
    }

    @Test
    void equalityTest() {
        BookWithGenresDTO dto1 = new BookWithGenresDTO("123-456", "Test Book", List.of("Rock", "Jazz"));
        BookWithGenresDTO dto2 = new BookWithGenresDTO("123-456", "Test Book", List.of("Rock", "Jazz"));
        BookWithGenresDTO dto3 = new BookWithGenresDTO("789-101", "Another Book", List.of("Pop"));

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
    }
}