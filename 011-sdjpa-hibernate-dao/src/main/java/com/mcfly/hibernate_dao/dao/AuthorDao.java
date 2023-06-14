package com.mcfly.hibernate_dao.dao;

import com.mcfly.hibernate_dao.domain.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorDao {

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author findAuthorByNameNative(String firstName, String lastName);

    Author findByLastName(String lastName);

    Author findByFirstName(String firstName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);

    List<Author> listAuthorByLastNameLike(String lastNameLike);

    List<Author> findAll();

    List<Author> findByLastNameSortByFirstName(String lastName, Pageable pageable);
}
