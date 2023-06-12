package com.mcfly.sdjpajdbctemplate.dao;

import com.mcfly.sdjpajdbctemplate.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Book getById(Long id) {
        return jdbcTemplate.queryForObject("select * from book where id = ?", getBookMapper(), id);
    }

    @Override
    public Book getByTitle(String title) {
        return jdbcTemplate.queryForObject("select * from book where title = ?", getBookMapper(), title);
    }

    @Override
    public Book saveNewBook(Book book) {
        jdbcTemplate.update("insert into book (title, isbn, publisher, author_id) values (?, ?, ?, ?)", book.getTitle(), book.getIsbn(), book.getPublisher(), book.getAuthorId());
        final Long id = jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
        return getById(id);
    }

    @Override
    public Book updateBook(Book book) {
        jdbcTemplate.update("update book set title = ?, isbn = ?, publisher = ?, author_id = ? where id = ?", book.getTitle(), book.getIsbn(), book.getPublisher(), book.getAuthorId(), book.getId());
        return getById(book.getId());
    }

    @Override
    public void deleteBookById(Long id) {
        jdbcTemplate.update("delete from book where id = ?", id);
    }

    private BookMapper getBookMapper() {
        return new BookMapper();
    }
}
