package com.kevin.music_streaming_app.db;

import com.kevin.music_streaming_app.App;
import com.kevin.music_streaming_app.audio.AudioPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Song {
    String name, user = null, genre;
    File songPath, songCover;
    Blob song, cover;
    int userId;
    static AudioPlayer player;

    public Song(String name, String user, String songPath, String songCover, String genre) {
        this.name = name;
        this.user = user;
        this.songPath = new File(songPath);
        this.songCover = new File(songCover);
    }

    public Song(String name, int userId, Blob song, Blob cover, String genre) {
        this.name = name;
        this.userId = userId;
        this.song = song;
        this.cover = cover;
        this.genre = genre;
    }

    public void insert() {
        try {
            String query = "INSERT INTO Song (user_id, song_name, song, song_cover, genre, release_date)" +
                    " VALUES (?, ?, ?, ?, ?, ?)";

            FileInputStream songStream = new FileInputStream(songPath);
            FileInputStream songImage = new FileInputStream(songCover);

            int id = searchUserId();
            System.out.println(id);

            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, id);
            preparedStmt.setString(2, name);
            preparedStmt.setBlob(3, songStream);
            preparedStmt.setBlob(4, songImage);
            preparedStmt.setString(5, genre);
            preparedStmt.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
            preparedStmt.execute();

            System.out.println("Song " + name + "by " + user + " added to Database");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int searchUserId() {
        try {
            Statement s = DB.getConnection().createStatement();
            String query = "SELECT * FROM User WHERE username = 'Daft Punk'";
            ResultSet rs = s.executeQuery(query);

            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public Blob searchSong() throws SQLException {
        Statement s = DB.getConnection().createStatement();

        String query = "SELECT * FROM Song";
        ResultSet rs = s.executeQuery(query);
        int id = searchUserId();

        while (rs.next()) {
            boolean isUser = rs.getInt("user_id") == id;
            boolean isSong = rs.getString("song_name").equals(name);
            if (isUser && isSong) return rs.getBlob("song");
        }

        return null;
    }

    public static List<Song> returnAll(String condition) {
        List<Song> songs = new ArrayList<Song>();

        try {
            Statement s = DB.getConnection().createStatement();

            String query = "SELECT * FROM Song";
            if (condition.equals("newest")) {
                query = "SELECT * FROM SONG ORDER BY release_date asc";
            }

            ResultSet rs = s.executeQuery(query);
            int id, userId;
            String songName, genre;
            Blob song, songCover;

            while (rs.next()) {
                id = rs.getInt("id");
                songName = rs.getString("song_name");
                genre = rs.getString("genre");
                userId = rs.getInt("user_id");
                song = rs.getBlob("song");
                songCover = rs.getBlob("song_cover");
                songs.add(new Song(songName, userId, song, songCover, genre));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return songs;
    }

    public static void play(Blob song) {
        App.getPlayers().forEach((t) -> t.stop());
        App.getPlayers().clear();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player = new AudioPlayer(song);
        App.getPlayers().add(player);
        player.start();
    }

    public static void pause() {
        App.getPlayer().pause();
    }

    public static void resume() {
        App.getPlayer().resume();
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        if (user != null) return user;
        else {
            return User.searchUserById(userId).getName();
        }
    }

    public Blob getSong() {
        return song;
    }

    public Blob getCover() {
        return cover;
    }
}
