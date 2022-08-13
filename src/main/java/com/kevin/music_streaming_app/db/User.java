package com.kevin.music_streaming_app.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class User {
    String name, password;
    int id;
    Blob profilePicture = null;

    public User(String name) {
        this.name = name;
    }

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public User(String name, int id, Blob profilePicture) {
        this.name = name;
        this.id = id;
        this.profilePicture = profilePicture;
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
            String query = "SELECT id, username, profile_picture FROM User WHERE id = " + id;
            ResultSet rs = s.executeQuery(query);

            String username;
            int userId;
            Blob picture;

            while (rs.next()) {
                username = rs.getString("username");
                userId = rs.getInt("id");
                picture = rs.getBlob("profile_picture");
                return new User(username, userId, picture);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static User searchUserByName(String name) {
        try {
            Statement s = DB.getConnection().createStatement();
            String query = "SELECT id, username, profile_picture FROM User WHERE username = '" + name + "'";
            ResultSet rs = s.executeQuery(query);

            String username;
            int userId;
            Blob picture;

            while (rs.next()) {
                username = rs.getString("username");
                userId = rs.getInt("id");
                picture = rs.getBlob("profile_picture");
                return new User(username, userId, picture);
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
            String query = "SELECT id, username, profile_picture FROM User WHERE username LIKE ?";
            PreparedStatement ps = DB.getConnection().prepareStatement(query);
            ps.setString(1,"%" + word + "%");

            ResultSet rs = ps.executeQuery();
            int id;
            String name;
            Blob picture;

            while (rs.next() && count++ < limit) {
                id = rs.getInt("id");
                name = rs.getString("username");
                picture = rs.getBlob("profile_picture");
                users.add(new User(name, id, picture));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
    public List<User> returnFollowed(int limit) {
        List<User> users = new ArrayList<>();
        int count = 0;

        try {
            String query = "SELECT followed_user_id FROM FollowedUSER" +
                    " JOIN User on id = user_id WHERE id = ?";
            PreparedStatement ps = DB.getConnection().prepareStatement(query);
            ps.setInt(1, this.id);

            ResultSet rs = ps.executeQuery();
            int id;
            String name;
            Blob picture;

            while (rs.next() && count++ < limit) {
                id = rs.getInt("followed_user_id");
                User user = User.searchUserById(id);
                name = user.getName();
                picture = user.getProfilePicture();
                users.add(new User(name, id, picture));
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
            Blob image;
            int id;

            while (rs.next()) {
                currentName = rs.getString("username");
                currentPassword = rs.getString("password");
                if (currentName.equals(username) && currentPassword.equals(password)) {
                    id = rs.getInt("id");
                    image = rs.getBlob("profile_picture");
                    Object[] arr = {true, new User(username, id, image)};
                    return arr;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object[] arr = {false};
        return arr;
    }

    public static void updateProfilePicture(String name, String path) {
        try {
            String query = "UPDATE User SET profile_picture = ? WHERE username = ?";

            File file = new File(path);
            FileInputStream image = new FileInputStream(file);


            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setBlob(1, image);
            preparedStmt.setString(2, name);
            preparedStmt.execute();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Blob getProfilePicture() {
        return profilePicture;
    }
}
