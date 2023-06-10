package com.mcfly.sdjpajdbc;

import com.mcfly.sdjpajdbc.domain.Author;
import com.mcfly.sdjpajdbc.domain.Book;
import com.mcfly.sdjpajdbc.repository.AuthorRepository;
import com.mcfly.sdjpajdbc.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySqlIntegrationTests {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

    @Test
    void testBookSaveAndFind() {
        final Book book = new Book("Test book", null, null, null);
        final Book savedBook = bookRepository.save(book);
        final Long id = savedBook.getId();
        assertThat(id).isNotNull();
        final Book foundBook = bookRepository.findById(id).orElse(null);
        assertThat(foundBook).isNotNull();
    }

    @Test
    void testAuthorSaveAndFind() {
        final Author author = new Author("Test first name", "Test last name");
        final Author savedAuthor = authorRepository.save(author);
        final Long id = savedAuthor.getId();
        assertThat(id).isNotNull();
        final Author foundAuthor = authorRepository.findById(id).orElse(null);
        assertThat(foundAuthor).isNotNull();
    }
}
