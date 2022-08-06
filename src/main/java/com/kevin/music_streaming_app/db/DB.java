package com.kevin.music_streaming_app.db;

import com.kevin.music_streaming_app.audio.AudioPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;


public class DB {
    private static Connection connection;

    public static void main() {
        connection = connectToDatabase();
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

    public static Blob returnSong(Connection c) throws SQLException {
        Statement s = c.createStatement();

        String query = "SELECT * FROM Song WHERE user_id = 2";
        ResultSet rs = s.executeQuery(query);

        while (rs.next()) {
            return rs.getBlob("content");
        }

        return null;

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

    public static Connection getConnection() {
        return connection;
    }
}
