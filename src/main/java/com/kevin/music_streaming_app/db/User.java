package com.kevin.music_streaming_app.db;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class User {
    String name, password;
    int id;

    public User(String name) {
        this.name = name;
    }

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean insert() {
        try {
            String query = "INSERT INTO User (username, password)" +
                    " VALUES (?, ?)";

            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, password);
            preparedStmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User searchUserById(int id) {
        try {
            Statement s = DB.getConnection().createStatement();
            String query = "SELECT * FROM User WHERE id = " + id;
            ResultSet rs = s.executeQuery(query);

            String username;
            int userId;

            while (rs.next()) {
                username = rs.getString("username");
                userId = rs.getInt("id");
                return new User(username, userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static User searchUserByName(String name) {
        try {
            Statement s = DB.getConnection().createStatement();
            String query = "SELECT * FROM User WHERE username = '" + name + "'";
            ResultSet rs = s.executeQuery(query);

            String username;
            int userId;

            while (rs.next()) {
                username = rs.getString("username");
                userId = rs.getInt("id");
                return new User(username, userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<User> returnAllThatContain(String word, int limit) {
        List<User> users = new ArrayList<>();
        int count = 0;

        try {
            String query = "SELECT id, username FROM User WHERE username LIKE ?";
            PreparedStatement ps = DB.getConnection().prepareStatement(query);
            ps.setString(1,"%" + word + "%");

            ResultSet rs = ps.executeQuery();
            int id;
            String name;

            while (rs.next() && count++ < limit) {
                id = rs.getInt("id");
                name = rs.getString("username");
                users.add(new User(name, id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }


    public static Object[] verifyLogin(String username, String password) {
        Statement statement = DB.createStatement(DB.getConnection());

        try {
            String query = "SELECT * FROM User";
            ResultSet rs = statement.executeQuery(query);
            String currentName, currentPassword;
            int id;

            while (rs.next()) {
                currentName = rs.getString("username");
                currentPassword = rs.getString("password");
                if (currentName.equals(username) && currentPassword.equals(password)) {
                    id = rs.getInt("id");
                    Object[] arr = {true, new User(username, id)};
                    return arr;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[] arr = {false};
        return arr;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
