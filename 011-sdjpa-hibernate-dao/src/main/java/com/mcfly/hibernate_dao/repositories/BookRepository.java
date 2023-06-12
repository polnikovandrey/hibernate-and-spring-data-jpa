package com.mcfly.hibernate_dao.repositories;

import com.mcfly.hibernate_dao.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
