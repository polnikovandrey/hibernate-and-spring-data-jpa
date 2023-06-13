package com.mcfly.hibernate_dao.dao;

import com.mcfly.hibernate_dao.domain.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
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
    public Author findAuthorByNameNative(String firstName, String lastName) {
        final Query query = entityManager.createNativeQuery("select * from author a where a.first_name = ? and a.last_name = ?", Author.class);
        query.setParameter(1, firstName);
        query.setParameter(2, lastName);
        return (Author)query.getSingleResult();
    }

    @Override
    public Author findByLastName(String lastName) {
        final TypedQuery<Author> query = entityManager.createNamedQuery("author_find_by_last_name", Author.class);
        query.setParameter("last_name", lastName);
        return query.getSingleResult();
    }

    @Override
    public Author findByFirstName(String firstName) {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Author> query = criteriaBuilder.createQuery(Author.class);
        final Root<Author> root = query.from(Author.class);
        final ParameterExpression<String> firstNameParameter = criteriaBuilder.parameter(String.class);
        final Predicate firstNamePredicate = criteriaBuilder.equal(root.get("firstName"), firstNameParameter);
        query.select(root).where(criteriaBuilder.and(firstNamePredicate));
        final TypedQuery<Author> typedQuery = entityManager.createQuery(query);
        typedQuery.setParameter(firstNameParameter, firstName);
        return typedQuery.getSingleResult();
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
