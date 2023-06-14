package com.mcfly.sdjpajdbctemplate.dao;

import com.mcfly.sdjpajdbctemplate.domain.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorDao {

    Author getById(Long id);

    Author getByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);

    List<Author> findByLastName(String lastName);

    List<Author> findByLastNameSortByFirstName(String lastName, Pageable pageable);
}
