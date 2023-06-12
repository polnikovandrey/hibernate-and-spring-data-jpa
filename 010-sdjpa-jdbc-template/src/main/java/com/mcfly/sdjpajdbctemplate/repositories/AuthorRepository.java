package com.mcfly.sdjpajdbctemplate.repositories;


import com.mcfly.sdjpajdbctemplate.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
