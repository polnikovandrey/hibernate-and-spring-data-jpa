package com.mcfly.hibernate_dao;

import com.mcfly.hibernate_dao.dao.AuthorDao;
import com.mcfly.hibernate_dao.dao.AuthorDaoImpl;
import com.mcfly.hibernate_dao.dao.BookDao;
import com.mcfly.hibernate_dao.dao.BookDaoImpl;
import com.mcfly.hibernate_dao.domain.Author;
import com.mcfly.hibernate_dao.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@Import({AuthorDaoImpl.class, BookDaoImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;

    @Test
    void testDeleteBook() {
        final Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        final Book saved = bookDao.saveNewBook(book);
        bookDao.deleteBookById(saved.getId());
        final Book deleted = bookDao.getById(saved.getId());
        assertThat(deleted).isNull();
    }

    @Test
    void updateBookTest() {
        final Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(3L);
        final Book saved = bookDao.saveNewBook(book);
        saved.setTitle("New Book");
        bookDao.updateBook(saved);
        final Book fetched = bookDao.getById(saved.getId());
        assertThat(fetched.getTitle()).isEqualTo("New Book");
    }

    @Test
    void testSaveBook() {
        final Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(3L);
        final Book saved = bookDao.saveNewBook(book);
        assertThat(saved).isNotNull();
    }

    @Test
    void testGetBookByName() {
        final Book book = bookDao.findBookByTitle("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void testGetBook() {
        final Book book = bookDao.getById(3L);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    void testDeleteAuthor() {
        final Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");
        final Author saved = authorDao.saveNewAuthor(author);
        authorDao.deleteAuthorById(saved.getId());
        final Author deleted = authorDao.getById(saved.getId());
        assertThat(deleted).isNull();
    }

    @Test
    void testUpdateAuthor() {
        final Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");
        final Author saved = authorDao.saveNewAuthor(author);
        saved.setLastName("Thompson");
        final Author updated = authorDao.updateAuthor(saved);
        assertThat(updated.getLastName()).isEqualTo("Thompson");
    }

    @Test
    void testSaveAuthor() {
        final Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Thompson");
        Author saved = authorDao.saveNewAuthor(author);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void testGetAuthorByName() {
        final Author author = authorDao.findAuthorByName("Craig", "Walls");
        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthor() {
        final Author author = authorDao.getById(1L);
        assertThat(author).isNotNull();

    }
}
