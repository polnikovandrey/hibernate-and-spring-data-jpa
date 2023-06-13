package com.mcfly.spring_data_jpa.repositories;

import com.mcfly.spring_data_jpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> getBookById(Long id);

    Optional<Book> getBookByTitle(String title);

    Book readBookByTitle(String title);

    @Nullable
    Book findBookByTitle(@Nullable String title);

}
