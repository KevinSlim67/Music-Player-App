package com.kevin.music_streaming_app.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.kevin.music_streaming_app.db.DB.connection;

public class User {
    public User() {

    }

    public static List<String> returnAll() {
        List<String> list = new ArrayList<String>();
        Statement statement = DB.createStatement(connection);

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
}
