package com.mcfly.spring_data_jpa.dao;

import com.mcfly.spring_data_jpa.domain.Author;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by jt on 8/22/21.
 */
public interface AuthorDao {

    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);

    List<Author> findByLastNameSortByFirstName(String lastName, Pageable pageable);
}
