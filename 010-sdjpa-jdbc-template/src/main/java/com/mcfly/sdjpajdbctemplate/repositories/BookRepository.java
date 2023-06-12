package com.mcfly.sdjpajdbctemplate.repositories;

import com.mcfly.sdjpajdbctemplate.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
