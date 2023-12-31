package com.mcfly.spring_data_jpa.repositories;


import com.mcfly.spring_data_jpa.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> getAuthorById(Long id);

    Optional<Author> findAuthorByFirstNameAndLastName(String firstName, String lastName);

    Page<Author> findAuthorsByLastNameOrderByFirstName(String lastName, Pageable pageable);

}
