package com.mcfly.spring_data_jpa.dao;

import com.mcfly.spring_data_jpa.domain.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookDao {

    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    void updateBook(Book saved);

    void deleteBookById(Long id);

    List<Book> findAllBooks(int pageSize, int offset);

    List<Book> findAllBooks(Pageable pageable);

    List<Book> findAllBooksSortByTitle(Pageable pageable);
}
