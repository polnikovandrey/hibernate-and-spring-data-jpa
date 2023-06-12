package com.mcfly.sdjpajdbctemplate.dao;

import com.mcfly.sdjpajdbctemplate.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(Long id) {
        final String sql
                = "select author.id as id, first_name, last_name, book.id as book_id, book.isbn, book.publisher, book.title from author left outer join book on author.id = book.author_id where author.id = ?";
        return jdbcTemplate.query(sql, new AuthorExtractor(), id);
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        return jdbcTemplate.query("select author.id as id, first_name, last_name, book.id as book_id, book.isbn, book.publisher, book.title from author left outer join book on author.id = book.author_id where first_name = ? and last_name = ?", new AuthorExtractor(), firstName, lastName);
    }

    @Override
    public Author saveNewAuthor(Author author) {
        jdbcTemplate.update("insert into author (first_name, last_name) values (?, ?)", author.getFirstName(), author.getLastName());
        final Long createdId = jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
        return getById(createdId);
    }

    @Override
    public Author updateAuthor(Author author) {
        jdbcTemplate.update("update author set first_name = ?, last_name = ? where id = ?", author.getFirstName(), author.getLastName(), author.getId());
        return getById(author.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {
        jdbcTemplate.update("delete from author where id = ?", id);
    }
}
