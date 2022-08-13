package com.kevin.music_streaming_app.db;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowedUser {
    private int userId, followedUserId;

    public FollowedUser(int userId, int followedUserId) {
        this.userId = userId;
        this.followedUserId = followedUserId;
    }

    public boolean insert() {
        try {
            String query = "INSERT INTO FollowedUser (user_id, followed_user_id)" +
                    " VALUES (?, ?)";

            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, this.userId);
            preparedStmt.setInt(2, this.followedUserId);
            preparedStmt.execute();

            System.out.println("User " + userId + " followed " + followedUserId);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete() {
        try {
            String query = "DELETE FROM FollowedUser WHERE user_id = ? AND followed_user_id = ?";

            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, this.userId);
            preparedStmt.setInt(2, this.followedUserId);
            preparedStmt.execute();

            System.out.println("User " + userId + " un-followed " + followedUserId);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean exists() {
        try {
            String query = "SELECT * FROM FollowedUser WHERE user_id = ? AND followed_user_id = ?";

            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, this.userId);
            preparedStmt.setInt(2, this.followedUserId);
            ResultSet rs = preparedStmt.executeQuery();

            if (rs.next()) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
