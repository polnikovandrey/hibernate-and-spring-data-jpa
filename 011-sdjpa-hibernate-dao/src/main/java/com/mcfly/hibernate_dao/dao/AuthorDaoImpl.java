package com.mcfly.hibernate_dao.dao;

import com.mcfly.hibernate_dao.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManagerFactory entityManagerFactory;

    public AuthorDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Author getById(Long id) {
        return createEntityManager(entityManager -> entityManager.find(Author.class, id));
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return createEntityManager(entityManager -> {
            final TypedQuery<Author> query
                    = entityManager.createQuery(
                    "select a from Author a where a.firstName = :first_name and a.lastName = :last_name",
                    Author.class);
            query.setParameter("first_name", firstName);
            query.setParameter("last_name", lastName);
            return query.getSingleResult();
        });
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return createEntityManager(entityManager -> {
            entityManager.joinTransaction();
            entityManager.persist(author);
            entityManager.flush();
            return author;
        });
    }

    @Override
    public Author updateAuthor(Author author) {
        return createEntityManager(entityManager -> {
            entityManager.joinTransaction();
            final Author merged = entityManager.merge(author);
            entityManager.flush();
            entityManager.clear();
            return entityManager.find(Author.class, merged.getId());
        });
    }

    @Override
    public void deleteAuthorById(Long id) {

    }

    private <T> T createEntityManager(Function<EntityManager, T> entityManagerToTypeFunction) {
        try (final EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManagerToTypeFunction.apply(entityManager);
        }
    }
}
