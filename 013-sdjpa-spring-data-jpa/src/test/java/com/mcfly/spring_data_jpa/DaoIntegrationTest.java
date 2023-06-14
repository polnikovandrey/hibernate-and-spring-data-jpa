package com.mcfly.spring_data_jpa;

import com.mcfly.spring_data_jpa.dao.AuthorDao;
import com.mcfly.spring_data_jpa.dao.AuthorDaoImpl;
import com.mcfly.spring_data_jpa.dao.BookDao;
import com.mcfly.spring_data_jpa.dao.BookDaoImpl;
import com.mcfly.spring_data_jpa.domain.Author;
import com.mcfly.spring_data_jpa.domain.Book;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Created by jt on 8/28/21.
 */
@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"guru.springframework.jdbc.dao"})
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
        assertThrows(EntityNotFoundException.class, () -> bookDao.getById(saved.getId()));
    }

    @Test
    void updateBookTest() {
        final Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(1L);
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
        book.setAuthorId(1L);
        final Book saved = bookDao.saveNewBook(book);
        assertThat(saved).isNotNull();
    }

    @Test
    void testGetBookByName() {
        final Book book = bookDao.findBookByTitle("Clean Code");
        assertThat(book).isNotNull();
        assertThrows(EntityNotFoundException.class, () -> bookDao.findBookByTitle("1"));
    }

    @Test
    void testGetBook() {
        final Book book = bookDao.getById(3L);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    void testFindBooksFirstPage() {
        final List<Book> books = bookDao.findAllBooks(10, 0);
        Assertions.assertThat(books).isNotNull();
        Assertions.assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void testFindBooksSecondPage() {
        final List<Book> books = bookDao.findAllBooks(10, 10);
        Assertions.assertThat(books).isNotNull();
        Assertions.assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    void testFindBooksTenthPage() {
        final List<Book> books = bookDao.findAllBooks(10, 100);
        Assertions.assertThat(books).isNotNull();
        Assertions.assertThat(books.size()).isEqualTo(0);
    }

    @Test
    void testFindBooksFirstPagePageable() {
        final List<Book> books = bookDao.findAllBooks(PageRequest.of(0, 10));
        Assertions.assertThat(books).isNotNull();
        Assertions.assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void testFindBooksSecondPagePageable() {
        final List<Book> books = bookDao.findAllBooks(PageRequest.of(1, 10));
        Assertions.assertThat(books).isNotNull();
        Assertions.assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    void testFindBooksTenthPagePageable() {
        final List<Book> books = bookDao.findAllBooks(PageRequest.of(9, 10));
        Assertions.assertThat(books).isNotNull();
        Assertions.assertThat(books.size()).isEqualTo(0);
    }

    @Test
    void testFindBooksFirstPagePageableSortedByTitle() {
        final List<Book> books = bookDao.findAllBooksSortByTitle(PageRequest.of(0, 10));
        Assertions.assertThat(books).isNotNull();
        Assertions.assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void testDeleteAuthor() {
        final Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");
        final Author saved = authorDao.saveNewAuthor(author);
        authorDao.deleteAuthorById(saved.getId());
        assertThrows(EntityNotFoundException.class, () -> authorDao.getById(saved.getId()));
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
        final Author saved = authorDao.saveNewAuthor(author);
        assertThat(saved).isNotNull();
    }

    @Test
    void testGetAuthorByName() {
        final Author author = authorDao.findAuthorByName("Craig", "Walls");
        assertThat(author).isNotNull();
        assertThrows(EntityNotFoundException.class, () -> authorDao.findAuthorByName("1", "2"));
    }

    @Test
    void testGetAuthor() {
        final Author author = authorDao.getById(1L);
        assertThat(author).isNotNull();
    }

    @Test
    void testFindAuthorsByLastNameSortByFirstNameFirstPage() {
        final List<Author> smiths = authorDao.findByLastNameSortByFirstName("Smith", PageRequest.of(0, 10));
        Assertions.assertThat(smiths).isNotNull();
        Assertions.assertThat(smiths.size()).isEqualTo(10);
    }

    @Test
    void testFindAuthorsByLastNameSortByFirstNameSecondPage() {
        final List<Author> smiths = authorDao.findByLastNameSortByFirstName("Smith", PageRequest.of(1, 10));
        Assertions.assertThat(smiths).isNotNull();
        Assertions.assertThat(smiths.size()).isEqualTo(10);
    }
}
