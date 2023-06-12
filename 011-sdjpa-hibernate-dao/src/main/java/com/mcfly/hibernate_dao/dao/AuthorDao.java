package com.mcfly.hibernate_dao.dao;

import com.mcfly.hibernate_dao.domain.Author;

public interface AuthorDao {

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);
}
