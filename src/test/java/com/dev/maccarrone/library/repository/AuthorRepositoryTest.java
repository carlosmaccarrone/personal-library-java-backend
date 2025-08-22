package com.dev.maccarrone.library.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;
import com.dev.maccarrone.library.model.Author;
import org.junit.jupiter.api.Test;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void basicSaveAndFind() {
        Author author = new Author();
        author.setAuthorName("Test Author");
        authorRepository.save(author);

        assertTrue(authorRepository.findById(author.getAuthorId()).isPresent());
    }

    @Test
    void findAllAuthors() {
		Author authorOne = new Author();
		authorOne.setAuthorName("Test One");
		authorRepository.save(authorOne);
		Author authorTwo = new Author();
		authorTwo.setAuthorName("Test Two");
		authorRepository.save(authorTwo);

        List<Author> authors = authorRepository.findAll();
        assertEquals(2, authors.size());
    }

    @Test
    void updateAuthorName() {
        Author author = new Author();
        author.setAuthorName("Old Name");
        author = authorRepository.save(author);

        author.setAuthorName("New Name");
        authorRepository.save(author);

        Author updated = authorRepository.findById(author.getAuthorId()).orElseThrow();
        assertEquals("New Name", updated.getAuthorName());
    }

    @Test
    void deleteAuthor() {
        Author author = new Author();
        author.setAuthorName("To Delete");
        author = authorRepository.save(author);

        authorRepository.deleteById(author.getAuthorId());
        assertFalse(authorRepository.findById(author.getAuthorId()).isPresent());
    }
}