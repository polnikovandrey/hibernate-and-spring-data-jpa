package com.mcfly.sdjpajdbc;

import com.mcfly.sdjpajdbc.dao.BookDao;
import com.mcfly.sdjpajdbc.domain.Book;
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
public class BookDaoIntegrationTest {

    @Autowired
    BookDao bookDao;

    @Test
    void testGetBookById() {
        final Book book = new Book("Test book", "Test isbn", "Test publisher", 1L);
        final Book createdBook = bookDao.createNewBook(book);
        final Book foundBook = bookDao.getById(createdBook.getId());
        assertThat(foundBook).isNotNull();
        System.out.println("Found book id: " + foundBook.getId());
    }

    @Test
    void testGetBookByTitle() {
        final Book book = new Book("Test book", "Test isbn", "Test publisher", 1L);
        bookDao.createNewBook(book);
        final Book foundBook = bookDao.getByTitle(book.getTitle());
        assertThat(foundBook).isNotNull();
        System.out.println("Found book id: " + foundBook.getId());
    }

    @Test
    void testCreateNewBook() {
        final Book book = new Book("Test book", "Test isbn", "Test publisher", 1L);
        final Book createdBook = bookDao.createNewBook(book);
        assertThat(createdBook).isNotNull();
        assertThat(createdBook.getId()).isNotNull();
        System.out.println("Created book id: " + createdBook.getId());
    }

    @Test
    void testUpdateBook() {
        final Book book = new Book("Test book", "Test isbn", "Test publisher", 1L);
        final Book createdBook = bookDao.createNewBook(book);
        createdBook.setTitle("Updated test book");
        final Book updatedBook = bookDao.updateBook(createdBook);
        assertThat(updatedBook.getTitle()).isEqualTo(createdBook.getTitle());
        System.out.println("Updated book title: " + updatedBook.getTitle());
    }

    @Test
    void deleteBook() {
        final Book book = new Book("Test book", "Test isbn", "Test publisher", 1L);
        final Book createdBook = bookDao.createNewBook(book);
        bookDao.deleteBookById(createdBook.getId());
        final Book deletedNotFoundBook = bookDao.getById(createdBook.getId());
        assertThat(deletedNotFoundBook).isNull();
        System.out.println("Deleted book id: " + createdBook.getId());
    }
}
