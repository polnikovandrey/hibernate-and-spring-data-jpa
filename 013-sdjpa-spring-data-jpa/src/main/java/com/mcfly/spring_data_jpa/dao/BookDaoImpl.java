package com.mcfly.spring_data_jpa.dao;

import com.mcfly.spring_data_jpa.domain.Book;
import com.mcfly.spring_data_jpa.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

    private final BookRepository bookRepository;

    public BookDaoImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.getBookById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book findBookByTitle(String title) {
        return bookRepository.getBookByTitle(title).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book saveNewBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public void updateBook(Book book) {
        final Book found = bookRepository.getBookById(book.getId()).orElseThrow(EntityNotFoundException::new);
        found.setIsbn(book.getIsbn());
        found.setPublisher(book.getPublisher());
        found.setTitle(book.getTitle());
        found.setAuthorId(book.getAuthorId());
        bookRepository.save(found);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
