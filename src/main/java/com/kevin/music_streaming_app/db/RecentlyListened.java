package com.kevin.music_streaming_app.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecentlyListened {

    private int songId, userId;

    public RecentlyListened(int userId, int songId) {
        this.songId = songId;
        this.userId = userId;
    }


    public boolean insert() {
        try {
            String query = "INSERT INTO RecentlyListened (user_id, song_id)" +
                    " VALUES (?, ?)";

            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, this.userId);
            preparedStmt.setInt(2, this.songId);
            preparedStmt.execute();

            System.out.println("User " + userId + " listened to " + songId);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete() {
        try {
            String query = "DELETE FROM RecentlyListened WHERE user_id = ? AND song_id = ?";

            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, this.userId);
            preparedStmt.setInt(2, this.songId);
            preparedStmt.execute();

            System.out.println("Removed song " + this.songId + " from user " + this.userId + "'s list");

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
