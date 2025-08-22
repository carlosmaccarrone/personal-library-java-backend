package com.dev.maccarrone.library.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BookSummaryDTOTest {

    @Test
    void createAndGetValues() {
        BookSummaryDTO dto = new BookSummaryDTO("123-456", "Test Book");

        assertEquals("123-456", dto.isbn());
        assertEquals("Test Book", dto.title());
    }

    @Test
    void equalityTest() {
        BookSummaryDTO dto1 = new BookSummaryDTO("123-456", "Test Book");
        BookSummaryDTO dto2 = new BookSummaryDTO("123-456", "Test Book");
        BookSummaryDTO dto3 = new BookSummaryDTO("789-101", "Another Book");

        assertEquals(dto1, dto2);
        assertNotEquals(dto1, dto3);
    }
}