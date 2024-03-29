package com.await.bdpw8.da;

import com.await.bdpw8.da.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void addUser(String name, String email, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name, email, password) VALUES (?, ?, ?)");
        statement.setString(1, name);
        statement.setString(2, email);
        statement.setString(3, password);
        statement.executeUpdate();
    }

    public void updateUser(int id, String name, String email, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE users SET name=?, email=?, password=? WHERE id=?");
        statement.setString(1, name);
        statement.setString(2, email);
        statement.setString(3, password);
        statement.setInt(4, id);
        statement.executeUpdate();
    }

    public void deleteUser(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public User getUserById(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id=?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
        }
        return null;
    }

    public List<User> getAllUsers() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
        ResultSet rs = statement.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password")));
        }
        return users;
    }
}



