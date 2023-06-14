package com.mcfly.sdjpajdbctemplate.dao;

import com.mcfly.sdjpajdbctemplate.domain.Book;

import java.util.List;

public interface BookDao {

    Book getById(Long id);

    Book getByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);


    // Paging with JdbcTemplate section

    List<Book> findAllBooks();

    List<Book> findAllBooks(int pageSize, int offset);

}
