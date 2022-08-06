package com.kevin.music_streaming_app.db;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class User {
    String name;
    int id;

    public User(String name) {
        this.name = name;
    }

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static User searchUserById(int id) {
        try {
            Statement s = DB.getConnection().createStatement();
            String query = "SELECT * FROM User WHERE username = 'Daft Punk'";
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

    public static List<String> returnAll() {
        List<String> list = new ArrayList<String>();
        Statement statement = DB.createStatement(DB.getConnection());

        try {
            String query = "SELECT * FROM User";
            ResultSet rs = statement.executeQuery(query);
            String id, name;

            while (rs.next()) {
                id = "id: " + rs.getString("id");
                name = ", name: " + rs.getString("username");
                list.add(id + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
