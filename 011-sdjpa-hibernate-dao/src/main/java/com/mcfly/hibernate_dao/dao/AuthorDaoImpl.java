package com.mcfly.hibernate_dao.dao;

import com.mcfly.hibernate_dao.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final EntityManager entityManager;

    public AuthorDaoImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Author getById(Long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        final TypedQuery<Author> query
                = entityManager.createQuery(
                "select a from Author a where a.firstName = :first_name and a.lastName = :last_name",
                Author.class);
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);
        return query.getSingleResult();
    }

    @Override
    public Author saveNewAuthor(Author author) {
        entityManager.joinTransaction();
        entityManager.persist(author);
        entityManager.flush();
        entityManager.clear();
        return entityManager.find(Author.class, author.getId());
    }

    @Override
    public Author updateAuthor(Author author) {
        entityManager.joinTransaction();
        final Author merged = entityManager.merge(author);
        return entityManager.find(Author.class, merged.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {
        final Author author = entityManager.find(Author.class, id);
        entityManager.joinTransaction();
        entityManager.remove(author);
    }

    @Override
    public List<Author> listAuthorByLastNameLike(String lastNameLike) {
        final Query query = entityManager.createQuery("select a from Author a where a.lastName like :last_name");
        query.setParameter("last_name", lastNameLike + "%");
        //noinspection unchecked
        return query.getResultList();
    }

    @Override
    public List<Author> findAll() {
        final TypedQuery<Author> query = entityManager.createNamedQuery("author_find_all", Author.class);
        return query.getResultList();
    }
}
