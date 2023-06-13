package com.mcfly.spring_data_jpa.repositories;


import com.mcfly.spring_data_jpa.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
