package com.mcfly.hibernate_dao.dao;

import com.mcfly.hibernate_dao.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author findByLastName(String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);

    List<Author> listAuthorByLastNameLike(String lastNameLike);

    List<Author> findAll();
}
