package com.mcfly.hibernate_dao.dao;

import com.mcfly.hibernate_dao.domain.Book;

public interface BookDao {

    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    void updateBook(Book book);

    void deleteBookById(Long id);

    Book findByIsbn(String isbn);
}
