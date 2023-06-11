package com.mcfly.sdjpajdbc.dao;

import com.mcfly.sdjpajdbc.domain.Book;

public interface BookDao {

    Book getById(Long id);

    Book getByTitle(String title);

    Book createNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

}
