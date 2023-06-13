package com.mcfly.hibernate_dao.dao;

import com.mcfly.hibernate_dao.domain.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public Book findBookByTitleCriteria(String title) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        final Root<Book> root = query.from(Book.class);
        final ParameterExpression<String> titleParameter = criteriaBuilder.parameter(String.class);
        final Predicate titlePredicate = criteriaBuilder.equal(root.get("title"), titleParameter);
        query.select(root).where(titlePredicate);
        final TypedQuery<Book> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(titleParameter, title);
        return typedQuery.getSingleResult();
    }

    @Override
    public Book findBookByTitleNative(String title) {
        final Query query = entityManager.createNativeQuery("select * from book b where b.title = ?", Book.class);
        query.setParameter(1, title);
        return (Book)query.getSingleResult();
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

    @Override
    public Book findByIsbn(String isbn) {
        final TypedQuery<Book> query = entityManager.createNamedQuery("book_by_isbn", Book.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    @Override
    public List<Book> findAll() {
        final TypedQuery<Book> query = entityManager.createNamedQuery("book_find_all", Book.class);
        return query.getResultList();
    }
}
