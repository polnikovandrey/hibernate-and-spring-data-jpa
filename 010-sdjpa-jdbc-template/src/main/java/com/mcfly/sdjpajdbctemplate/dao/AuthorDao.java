package com.mcfly.sdjpajdbctemplate.dao;

import com.mcfly.sdjpajdbctemplate.domain.Author;

public interface AuthorDao {

    Author getById(Long id);

    Author getByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}