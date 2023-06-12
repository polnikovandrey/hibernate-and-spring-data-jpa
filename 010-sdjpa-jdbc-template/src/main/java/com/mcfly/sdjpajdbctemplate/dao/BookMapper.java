package com.mcfly.sdjpajdbctemplate.dao;

import com.mcfly.sdjpajdbctemplate.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Long id = rs.getLong("id");
        final String title = rs.getString("title");
        final String isbn = rs.getString("isbn");
        final String publisher = rs.getString("publisher");
        final Long authorId = rs.getLong("author_id");
        final Book book = new Book(title, isbn, publisher, authorId);
        book.setId(id);
        return book;
    }
}
