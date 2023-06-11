package com.mcfly.sdjpajdbc.dao;

import com.mcfly.sdjpajdbc.domain.Book;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class BookDaoImpl implements BookDao {

    private final DataSource dataSource;

    public BookDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Book getById(Long anId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from book where id = ?")) {
            preparedStatement.setLong(1, anId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    final Long id = resultSet.getLong("id");
                    final String title = resultSet.getString("title");
                    final String isbn = resultSet.getString("isbn");
                    final String publisher = resultSet.getString("publisher");
                    final long authorId = resultSet.getLong("author_id");
                    final Book book = new Book(title, isbn, publisher, authorId);
                    book.setId(id);
                    return book;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book getByTitle(String aTitle) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from book where title = ?")) {
            preparedStatement.setString(1, aTitle);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    final Long id = resultSet.getLong("id");
                    final String title = resultSet.getString("title");
                    final String isbn = resultSet.getString("isbn");
                    final String publisher = resultSet.getString("publisher");
                    final long authorId = resultSet.getLong("author_id");
                    final Book book = new Book(title, isbn, publisher, authorId);
                    book.setId(id);
                    return book;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book createNewBook(Book aBook) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into book (title, isbn, publisher, author_id) values (?, ?, ?, ?)")) {
            preparedStatement.setString(1, aBook.getTitle());
            preparedStatement.setString(2, aBook.getIsbn());
            preparedStatement.setString(3, aBook.getPublisher());
            preparedStatement.setLong(4, aBook.getAuthorId());
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
    public Book updateBook(Book aBook) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("update book set title = ?, isbn = ?, publisher = ?, author_id = ? where book.id = ?")) {
            preparedStatement.setString(1, aBook.getTitle());
            preparedStatement.setString(2, aBook.getIsbn());
            preparedStatement.setString(3, aBook.getPublisher());
            preparedStatement.setLong(4, aBook.getAuthorId());
            preparedStatement.setLong(5, aBook.getId());
            preparedStatement.execute();
            return getById(aBook.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteBookById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from book where id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
