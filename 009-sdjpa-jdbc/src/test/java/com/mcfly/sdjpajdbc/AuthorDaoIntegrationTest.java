package com.mcfly.sdjpajdbc;

import com.mcfly.sdjpajdbc.dao.AuthorDao;
import com.mcfly.sdjpajdbc.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = "com.mcfly.sdjpajdbc.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorDaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testGetAuthorById() {
        final Author author = authorDao.getById(1L);
        assertThat(author).isNotNull();
        System.out.println("Found author last name: " + author.getLastName());
    }

    @Test
    void testGetAuthorByName() {
        final Author author = authorDao.getByName("Eric", "Evans");
        assertThat(author).isNotNull();
        System.out.println("Found author id: " + author.getId());
    }
}
