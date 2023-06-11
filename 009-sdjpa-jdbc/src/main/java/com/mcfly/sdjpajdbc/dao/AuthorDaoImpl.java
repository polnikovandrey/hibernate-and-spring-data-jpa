package com.mcfly.sdjpajdbc.dao;

import com.mcfly.sdjpajdbc.domain.Author;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final DataSource dataSource;

    public AuthorDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Author getById(Long id) {
        return getById(id, true);
    }

    private Author getById(Long id, boolean usePreparedStatement) {
        return usePreparedStatement
                ? getByIdWithPreparedStatement(id)
                : getByIdWithStatement(id);
    }

    private Author getByIdWithStatement(Long id) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM author where id = " + id)) {
            if (resultSet.next()) {
                final Author author = new Author();
                author.setId(id);
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                return author;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Author getByIdWithPreparedStatement(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from author where id = ?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    final Author author = new Author();
                    author.setId(id);
                    author.setFirstName(resultSet.getString("first_name"));
                    author.setLastName(resultSet.getString("last_name"));
                    return author;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Author getByName(String firstName, String lastName) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from author where first_name = ? and last_name = ?")) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    final Author author = new Author();
                    author.setId(resultSet.getLong("id"));
                    author.setFirstName(firstName);
                    author.setLastName(lastName);
                    return author;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Author saveNewAuthor(Author author) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into author (first_name, last_name) values (?, ?)")) {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.execute();
            try (Statement innerStatement = connection.createStatement();
                 ResultSet resultSet = innerStatement.executeQuery("select last_insert_id()")) {
                if (resultSet.next()) {
                    final Long savedId = resultSet.getLong(1);
                    return getById(savedId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update author set first_name = ?, last_name = ? where author.id = ?")) {
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            preparedStatement.setLong(3, author.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getById(author.getId());
    }

    @Override
    public void deleteAuthorById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from author where author.id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
