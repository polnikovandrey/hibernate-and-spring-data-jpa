package com.mcfly.sdjpajdbctemplate.dao;

import com.mcfly.sdjpajdbctemplate.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author getById(Long id) {
        return jdbcTemplate.queryForObject("select * from author where id = ?", getRowMapper(), id);
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        return jdbcTemplate.queryForObject("select * from author where first_name = ? and last_name = ?", getRowMapper(), firstName, lastName);
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

    private RowMapper<Author> getRowMapper() {
        return new AuthorMapper();
    }
}
