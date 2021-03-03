package org.geektimes.projects.user.repository;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.sql.DBConnectionManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class DatabaseUserRepository implements UserRepository {

    private final DataSource dataSource;

    public DatabaseUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DatabaseUserRepository() {
        this.dataSource = initDataSource();
    }

    public static final String INSERT_USER_DML_SQL = "INSERT INTO users(name,password,email,phoneNumber) VALUES " + "(?,?,?,?)";

    private DataSource initDataSource() {
        Context context = null;
        try {
            context = new InitialContext();
            return (DataSource) context.lookup("jdbc/UserPlatformDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = new DBConnectionManager().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_USER_DML_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhoneNumber());
            int i = preparedStatement.executeUpdate();
            return i == 1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(Long userId) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User getById(Long userId) {
        return null;
    }

    @Override
    public User getByNameAndPassword(String userName, String password) {
        return null;
    }

    @Override
    public Collection<User> getAll() {
        return null;
    }
}
