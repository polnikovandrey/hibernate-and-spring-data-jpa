package com.mcfly.sdjpajdbctemplate.dao;

import com.mcfly.sdjpajdbctemplate.domain.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public List<Author> findByLastName(String lastName) {
        return jdbcTemplate.query("select * from author where last_name = ?", getSimpleRowMapper(), lastName);
    }

    @Override
    public List<Author> findByLastNameSortByFirstName(String lastName, Pageable pageable) {
        return jdbcTemplate.query("select * from author where last_name = ? order by first_name " + pageable.getSort().getOrderFor("first_name").getDirection() + " limit ? offset ?", getSimpleRowMapper(), lastName, pageable.getPageSize(), pageable.getOffset());
    }

    private RowMapper<Author> getSimpleRowMapper() {
        return (rs, rowNum) -> {
            final Author author = new Author();
            author.setId(rs.getLong("id"));
            author.setFirstName(rs.getString("first_name"));
            author.setLastName(rs.getString("last_name"));
            return author;
        };
    }
}
