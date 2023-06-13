package com.mcfly.hibernate_dao.dao;

import com.mcfly.hibernate_dao.domain.Book;

import java.util.List;

public interface BookDao {

    Book getById(Long id);

    Book findBookByTitle(String title);

    Book findBookByTitleCriteria(String title);

    Book findBookByTitleNative(String title);

    Book saveNewBook(Book book);

    void updateBook(Book book);

    void deleteBookById(Long id);

    Book findByIsbn(String isbn);

    List<Book> findAll();
}
