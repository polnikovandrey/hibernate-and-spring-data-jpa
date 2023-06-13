package com.mcfly.spring_data_jpa.repositories;

import com.mcfly.spring_data_jpa.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> getBookById(Long id);

    Optional<Book> getBookByTitle(String title);

    Book readBookByTitle(String title);

    @Nullable
    Book findBookByTitle(@Nullable String title);

    Stream<Book> findAllByTitleNotNull();

    @Async
    Future<Book> queryByTitle(String title);

    @Query("select b from Book b where b.title = ?1")
    Book findBookByTitleWithQuery(String title);

    @Query("select b from Book b where b.title = :title")
    Book findBookByTitleWithQueryNamed(@Param("title") String title);

    @Query(value = "select * from book where title = :title", nativeQuery = true)
    Book findBookByTitleWithNativeQuery(@Param("title") String title);

    Book jpaNamed(@Param("title") String title);
}
