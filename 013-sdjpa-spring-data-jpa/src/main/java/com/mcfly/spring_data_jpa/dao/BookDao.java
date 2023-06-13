package com.mcfly.spring_data_jpa.dao;

import com.mcfly.spring_data_jpa.domain.Book;

public interface BookDao {

    Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    void updateBook(Book saved);

    void deleteBookById(Long id);
}
