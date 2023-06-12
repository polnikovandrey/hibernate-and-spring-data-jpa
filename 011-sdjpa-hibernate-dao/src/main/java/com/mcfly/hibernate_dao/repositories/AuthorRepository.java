package com.mcfly.hibernate_dao.repositories;


import com.mcfly.hibernate_dao.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
