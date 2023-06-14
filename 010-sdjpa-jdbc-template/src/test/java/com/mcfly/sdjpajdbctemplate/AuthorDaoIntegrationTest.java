package com.mcfly.sdjpajdbctemplate;

import com.mcfly.sdjpajdbctemplate.dao.AuthorDao;
import com.mcfly.sdjpajdbctemplate.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = "com.mcfly.sdjpajdbctemplate.dao")
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

    @Test
    void testSaveNewAuthor() {
        final Author author = new Author("John", "Thompson");
        final Author savedAuthor = authorDao.saveNewAuthor(author);
        assertThat(savedAuthor).isNotNull();
        assertThat(savedAuthor.getId()).isNotNull();
        System.out.println("Saved author id: " + savedAuthor.getId());
    }

    @Test
    void testUpdateAuthor() {
        final Author author = new Author("John", "T");
        final Author savedAuthor = authorDao.saveNewAuthor(author);
        savedAuthor.setLastName("Thompson");
        final Author updatedAuthor = authorDao.updateAuthor(savedAuthor);
        assertThat(updatedAuthor.getLastName()).isEqualTo("Thompson");
        System.out.println("Updated author last name: "  + updatedAuthor.getLastName());
    }

    @Test
    void testDeleteAuthorById() {
        final Author author = new Author("John", "T");
        final Author savedAuthor = authorDao.saveNewAuthor(author);
        authorDao.deleteAuthorById(savedAuthor.getId());
        final Author deletedNotFoundAuthor = authorDao.getById(savedAuthor.getId());
        assertThat(deletedNotFoundAuthor).isNull();
        System.out.println("Deleted author id: " + savedAuthor.getId());
    }

    @Test
    void testFindAuthorsByLastName() {
        final List<Author> smiths = authorDao.findByLastName("Smith");
        assertThat(smiths).isNotNull();
        assertThat(smiths.size()).isEqualTo(40);
    }

    @Test
    void testFindAuthorsByLastNameSortByFirstNameFirstPage() {
        final List<Author> smiths = authorDao.findByLastNameSortByFirstName("Smith", PageRequest.of(0, 10, Sort.by("first_name")));
        assertThat(smiths).isNotNull();
        assertThat(smiths.size()).isEqualTo(10);
    }

    @Test
    void testFindAuthorsByLastNameSortByFirstNameSecondPage() {
        final List<Author> smiths = authorDao.findByLastNameSortByFirstName("Smith", PageRequest.of(1, 10, Sort.by("first_name")));
        assertThat(smiths).isNotNull();
        assertThat(smiths.size()).isEqualTo(10);
    }
}
