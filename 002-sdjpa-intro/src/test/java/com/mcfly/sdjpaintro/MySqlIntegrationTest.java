package com.mcfly.sdjpaintro;

import com.mcfly.sdjpaintro.domain.AuthorUuid;
import com.mcfly.sdjpaintro.domain.BookNatural;
import com.mcfly.sdjpaintro.domain.BookUuid;
import com.mcfly.sdjpaintro.repositories.AuthorUuidRepository;
import com.mcfly.sdjpaintro.repositories.BookNaturalRepository;
import com.mcfly.sdjpaintro.repositories.BookRepository;
import com.mcfly.sdjpaintro.repositories.BookUuidRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.mcfly.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MySqlIntegrationTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookUuidRepository bookUuidRepository;
    @Autowired
    AuthorUuidRepository authorUuidRepository;
    @Autowired
    BookNaturalRepository bookNaturalRepository;

    @Test
    void testJpaTestSliceTransaction() {
        final long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }

    @Test
    void testBookUuidSaveAndGetById() {
        final BookUuid bookUuid = new BookUuid("Test book", null, null);
        final BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);
        final UUID uuid = savedBookUuid.getId();
        assertThat(uuid).isNotNull();
        final BookUuid foundBookUuid = bookUuidRepository.findById(uuid).orElse(null);
        assertThat(foundBookUuid).isNotNull();
    }

    @Test
    void testAuthorUuidSaveAndGetById() {
        final AuthorUuid authorUuid = new AuthorUuid("Test first name", "Test last name");
        final AuthorUuid savedAuthorUuid = authorUuidRepository.save(authorUuid);
        final UUID uuid = savedAuthorUuid.getId();
        assertThat(uuid).isNotNull();
        final AuthorUuid foundAuthorUuid = authorUuidRepository.findById(uuid).orElse(null);
        assertThat(foundAuthorUuid).isNotNull();
    }

    @Test
    void testBookNaturalSaveAndGetById() {
        final String titleAsId = "My book";
        final BookNatural bookNatural = new BookNatural(titleAsId, null, null);
        final BookNatural savedBookNatural = bookNaturalRepository.save(bookNatural);
        assertThat(savedBookNatural).isNotNull();
        final BookNatural foundBookNatural = bookNaturalRepository.findById(titleAsId).orElse(null);
        assertThat(foundBookNatural).isNotNull();
    }
}
