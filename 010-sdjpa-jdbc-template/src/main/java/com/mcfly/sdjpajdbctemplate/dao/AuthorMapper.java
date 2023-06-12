package com.mcfly.sdjpajdbctemplate.dao;

import com.mcfly.sdjpajdbctemplate.domain.Author;
import com.mcfly.sdjpajdbctemplate.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (rs.next()) {
            final Author author = new Author();
            author.setId(rs.getLong("id"));
            author.setFirstName(rs.getString("first_name"));
            author.setLastName(rs.getString("last_name"));
            author.setBooks(new ArrayList<>());
            try {
                if (rs.getString("isbn") != null) {
                    author.getBooks().add(mapBook(author.getId(), rs));
                    while (rs.next()) {
                        if (rs.getString("isbn") != null) {
                            author.getBooks().add(mapBook(author.getId(), rs));
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return author;
        }
        return null;
    }

    private Book mapBook(Long authorId, ResultSet rs) throws SQLException {
        final Book book = new Book();
        book.setId(rs.getLong("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublisher(rs.getString("publisher"));
        book.setTitle(rs.getString("title"));
        book.setAuthorId(authorId);
        return book;
    }
}
