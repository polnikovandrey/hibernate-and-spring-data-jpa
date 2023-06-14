package com.mcfly.sdjpajdbctemplate;

import com.mcfly.sdjpajdbctemplate.dao.BookDao;
import com.mcfly.sdjpajdbctemplate.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = "com.mcfly.sdjpajdbctemplate.dao")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookDaoIntegrationTest {

    @Autowired
    BookDao bookDao;

    @Test
    void testGetBookById() {
        final Book book = new Book("Test book", "Test isbn", "Test publisher", null);
        final Book createdBook = bookDao.saveNewBook(book);
        final Book foundBook = bookDao.getById(createdBook.getId());
        assertThat(foundBook).isNotNull();
        System.out.println("Found book id: " + foundBook.getId());
    }

    @Test
    void testGetBookByTitle() {
        final Book book = new Book("Test book", "Test isbn", "Test publisher", null);
        bookDao.saveNewBook(book);
        final Book foundBook = bookDao.getByTitle(book.getTitle());
        assertThat(foundBook).isNotNull();
        System.out.println("Found book id: " + foundBook.getId());
    }

    @Test
    void testCreateNewBook() {
        final Book book = new Book("Test book", "Test isbn", "Test publisher", 1L);
        final Book createdBook = bookDao.saveNewBook(book);
        assertThat(createdBook).isNotNull();
        assertThat(createdBook.getId()).isNotNull();
        System.out.println("Created book id: " + createdBook.getId());
    }

    @Test
    void testUpdateBook() {
        final Book book = new Book("Test book", "Test isbn", "Test publisher", 1L);
        final Book createdBook = bookDao.saveNewBook(book);
        createdBook.setTitle("Updated test book");
        final Book updatedBook = bookDao.updateBook(createdBook);
        assertThat(updatedBook.getTitle()).isEqualTo(createdBook.getTitle());
        System.out.println("Updated book title: " + updatedBook.getTitle());
    }

    @Test
    void deleteBook() {
        final Book book = new Book("Test book", "Test isbn", "Test publisher", null);
        final Book createdBook = bookDao.saveNewBook(book);
        bookDao.deleteBookById(createdBook.getId());
        assertThrows(EmptyResultDataAccessException.class, () -> bookDao.getById(createdBook.getId()));
        System.out.println("Deleted book id: " + createdBook.getId());
    }

    @Test
    void testFindAllBooks() {
        final List<Book> books = bookDao.findAllBooks();
        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    void testFindBooksFirstPage() {
        final List<Book> books = bookDao.findAllBooks(10, 0);
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void testFindBooksSecondPage() {
        final List<Book> books = bookDao.findAllBooks(10, 10);
        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    void testFindBooksTenthPage() {
        final List<Book> books = bookDao.findAllBooks(10, 100);
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    void testFindBooksFirstPagePageable() {
        final List<Book> books = bookDao.findAllBooks(PageRequest.of(0, 10));
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void testFindBooksSecondPagePageable() {
        final List<Book> books = bookDao.findAllBooks(PageRequest.of(1, 10));
        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(0);
    }

    @Test
    void testFindBooksTenthPagePageable() {
        final List<Book> books = bookDao.findAllBooks(PageRequest.of(9, 10));
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    void testFindBooksFirstPagePageableSortedByTitle() {
        final List<Book> books = bookDao.findAllBooksSortByTitle(PageRequest.of(0, 10, Sort.by(Sort.Order.desc("title"))));
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }
}
