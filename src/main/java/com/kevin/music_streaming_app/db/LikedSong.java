package com.kevin.music_streaming_app.db;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikedSong {
    private int songId, userId;
    private Song song;
    private User user;
    private String songName, userName;

    public LikedSong(int songId, int userId) {
        this.songId = songId;
        this.userId = userId;
    }

    public LikedSong(String songName, String userName) {
        this.songName = songName;
        this.userName = userName;
    }

    public boolean insert() {
        try {
            String query = "INSERT INTO LikedSong (user_id, song_id)" +
                    " VALUES (?, ?)";

            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, this.userId);
            preparedStmt.setInt(2, this.songId);
            preparedStmt.execute();

            System.out.println("User " + userId + " liked " + songId);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete() {
        try {
            String query = "DELETE FROM LikedSong WHERE user_id = ? AND song_id = ?";

            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, this.userId);
            preparedStmt.setInt(2, this.songId);
            preparedStmt.execute();

            System.out.println("User " + userId + " un-liked " + songId);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists() {
        try {
            String query = "SELECT * FROM LikedSong WHERE user_id = ? AND song_id = ?";

            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, this.userId);
            preparedStmt.setInt(2, this.songId);
            ResultSet rs = preparedStmt.executeQuery();

            if (rs.next()) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
