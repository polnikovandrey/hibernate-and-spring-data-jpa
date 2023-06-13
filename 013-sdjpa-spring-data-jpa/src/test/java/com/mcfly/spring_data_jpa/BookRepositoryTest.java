package com.mcfly.spring_data_jpa;

import com.mcfly.spring_data_jpa.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.mcfly.spring_data_jpa"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testEmptyResultException() {
        assertThrows(EmptyResultDataAccessException.class, () -> bookRepository.readBookByTitle("No such title"));
    }

    @Test
    void testNullParam() {
        assertNull(bookRepository.findBookByTitle(null));
    }

    @Test
    void testNoException() {
        assertNull(bookRepository.findBookByTitle("No such title"));
    }
}
