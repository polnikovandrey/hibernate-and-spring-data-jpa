package com.mcfly.sdjpajdbctemplate.dao;

import com.mcfly.sdjpajdbctemplate.domain.Author;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorExtractor implements ResultSetExtractor<Author> {

    @Override
    public Author extractData(@NonNull ResultSet rs) throws SQLException, DataAccessException {
        return new AuthorMapper().mapRow(rs, 0);
    }
}
