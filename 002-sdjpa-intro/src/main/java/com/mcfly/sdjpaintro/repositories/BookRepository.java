package com.mcfly.sdjpaintro.repositories;

import com.mcfly.sdjpaintro.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
