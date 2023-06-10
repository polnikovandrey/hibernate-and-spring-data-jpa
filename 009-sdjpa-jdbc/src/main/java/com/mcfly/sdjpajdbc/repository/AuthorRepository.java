package com.mcfly.sdjpajdbc.repository;

import com.mcfly.sdjpajdbc.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
