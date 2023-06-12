package com.mcfly.hibernate_dao.dao;

import com.mcfly.hibernate_dao.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

@Component
public class BookDaoImpl implements BookDao {

    private final EntityManager entityManager;

    public BookDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Book getById(Long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public Book findBookByTitle(String title) {
        final TypedQuery<Book> query
                = entityManager.createQuery("select b from Book b where b.title = :title", Book.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }

    @Override
    public Book saveNewBook(Book book) {
        entityManager.joinTransaction();
        entityManager.persist(book);
        entityManager.flush();
        return entityManager.find(Book.class, book.getId());
    }

    @Override
    public void updateBook(Book book) {
        entityManager.joinTransaction();
        entityManager.merge(book);
        entityManager.flush();
    }

    @Override
    public void deleteBookById(Long id) {
        entityManager.joinTransaction();
        final Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
        entityManager.flush();
    }
}
