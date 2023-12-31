package com.mcfly.sdjpaintro.bootstrap;

import com.mcfly.sdjpaintro.domain.AuthorUuid;
import com.mcfly.sdjpaintro.domain.Book;
import com.mcfly.sdjpaintro.domain.BookUuid;
import com.mcfly.sdjpaintro.repositories.AuthorUuidRepository;
import com.mcfly.sdjpaintro.repositories.BookRepository;
import com.mcfly.sdjpaintro.repositories.BookUuidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final AuthorUuidRepository authorUuidRepository;
    private final BookUuidRepository bookUuidRepository;

    @Autowired
    public DataInitializer(BookRepository bookRepository, AuthorUuidRepository authorUuidRepository, BookUuidRepository bookUuidRepository) {
        this.bookRepository = bookRepository;
        this.authorUuidRepository = authorUuidRepository;
        this.bookUuidRepository = bookUuidRepository;
    }

    @Override
    public void run(String... args) {
        bookRepository.deleteAll();
        final Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse", null);
        final Book savedDDD = bookRepository.save(bookDDD);
        final Book bookSIA = new Book("Spring In Action", "234234", "Oreily", null);
        final Book savedSIA = bookRepository.save(bookSIA);
        bookRepository.findAll()
                      .forEach(book -> {
                          System.out.println("Book Id: " + book.getId());
                          System.out.println("Book Title: " + book.getTitle());
                      });

        final AuthorUuid authorUuid = new AuthorUuid("Joe", "Buck");
        final AuthorUuid savedAuthorUuid = authorUuidRepository.save(authorUuid);
        System.out.println("Saved Author UUID: " + savedAuthorUuid.getId());

        final BookUuid bookUuid = new BookUuid("All about UUIDs", null, null);
        final BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);
        System.out.println("Saved Book UUID: " + savedBookUuid.getId());
    }
}
