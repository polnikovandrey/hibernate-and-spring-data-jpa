package com.mcfly.sdjpajdbctemplate.dao;

import com.mcfly.sdjpajdbctemplate.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

    @Override
    public Book getById(Long id) {
        return null;
    }

    @Override
    public Book getByTitle(String title) {
        return null;
    }

    @Override
    public Book saveNewBook(Book book) {
        return null;
    }

    @Override
    public Book updateBook(Book book) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {

    }
}
