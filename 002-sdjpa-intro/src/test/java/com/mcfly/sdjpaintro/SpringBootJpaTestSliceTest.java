package com.mcfly.sdjpaintro;

import com.mcfly.sdjpaintro.domain.AuthorUuid;
import com.mcfly.sdjpaintro.domain.Book;
import com.mcfly.sdjpaintro.domain.BookUuid;
import com.mcfly.sdjpaintro.repositories.AuthorUuidRepository;
import com.mcfly.sdjpaintro.repositories.BookRepository;
import com.mcfly.sdjpaintro.repositories.BookUuidRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DataJpaTest
@ComponentScan(basePackages = {"com.mcfly.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringBootJpaTestSliceTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookUuidRepository bookUuidRepository;
    @Autowired
    AuthorUuidRepository authorUuidRepository;

    @Commit  // Same as @Rollback(value = false)
    @Order(1)
    @Test
    void testJpaTestSlice() {
        final long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
        bookRepository.save(new Book("My Book", "1235555", "Self", null));
        final long countAfter = bookRepository.count();
        assertThat(countBefore).isLessThan(countAfter);
    }

    @Order(2)
    @Test
    void testJpaTestSliceTransaction() {
        final long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(3);
    }

    @Order(3)
    @Test
    void testBookUuidSaveAndGetById() {
        final BookUuid bookUuid = new BookUuid("Test book", null, null);
        final BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);
        final UUID uuid = savedBookUuid.getId();
        assertThat(uuid).isNotNull();
        final BookUuid foundBookUuid = bookUuidRepository.findById(uuid).orElse(null);
        assertThat(foundBookUuid).isNotNull();
    }

    @Order(3)
    @Test
    void testAuthorUuidSaveAndGetById() {
        final AuthorUuid authorUuid = new AuthorUuid("Test first name", "Test last name");
        final AuthorUuid savedAuthorUuid = authorUuidRepository.save(authorUuid);
        final UUID uuid = savedAuthorUuid.getId();
        assertThat(uuid).isNotNull();
        final AuthorUuid foundAuthorUuid = authorUuidRepository.findById(uuid).orElse(null);
        assertThat(foundAuthorUuid).isNotNull();
    }
}