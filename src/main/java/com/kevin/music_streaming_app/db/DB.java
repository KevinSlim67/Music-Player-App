package com.kevin.music_streaming_app.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DB {
    public static Connection connection;

    public static void main() {
        connection = connectToDatabase();

        try {
            System.out.println(returnAll(connection));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insert(Connection c) throws SQLException, FileNotFoundException {
        String query = "INSERT INTO Song (user_id, name, content)" +
                " VALUES (?, ?, ?)";

        File file = new File("assets/test_song.mp3");
        FileInputStream inputStream = new FileInputStream(file);

        PreparedStatement preparedStmt = c.prepareStatement(query);
        preparedStmt.setInt (1, 1);
        preparedStmt.setString (2, "Make It Better");
        preparedStmt.setBinaryStream (3,  inputStream, file.length());
        preparedStmt.execute();

        System.out.println("Done");
    }

    public static List<String> returnAll(Connection c) throws SQLException {
        List<String> list = new ArrayList<String>();
        Statement s = c.createStatement();

        String query = "SELECT * FROM Song WHERE id = 1";
        ResultSet rs = s.executeQuery(query);
        String id, name, content;

        while (rs.next()) {
            id = "id: " + rs.getString("id");
            name = ", name: " + rs.getString("name");
            content = ", content: " + rs.getBlob("content");

            list.add(id + name + content);
        }
        return list;
    }

    static public Connection connectToDatabase() {
        try {
            return DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/music_streaming_app", "root", Password.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static public Statement createStatement(Connection c) {
        try {
            return c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //this is a generic method that will work on all types of lists
    static <G> void printList(List<G> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
