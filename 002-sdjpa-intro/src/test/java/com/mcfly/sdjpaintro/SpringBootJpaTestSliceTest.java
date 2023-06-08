package com.mcfly.sdjpaintro;

import com.mcfly.sdjpaintro.domain.Book;
import com.mcfly.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SpringBootJpaTestSliceTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testJpaTestSlice() {
        final long countBefore = bookRepository.count();
        bookRepository.save(new Book("My Book", "1235555", "Self"));
        final long countAfter = bookRepository.count();
        assertThat(countBefore).isLessThan(countAfter);
    }
}
