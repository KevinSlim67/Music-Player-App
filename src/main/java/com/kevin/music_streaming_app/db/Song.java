package com.kevin.music_streaming_app.db;

import com.kevin.music_streaming_app.AppStage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Song {
    private String name, user = null, genre;
    private File songFile, songCoverFile;
    private Blob song, cover;
    private Integer userId, id;

    public Song(String name, String user, File songFile, File songCoverFile, String genre) {
        this.name = name;
        this.user = user;
        this.songFile = songFile;
        this.songCoverFile = songCoverFile;
        this.genre = genre;
    }

    public Song(String name, int userId, Blob song, Blob cover, String genre) {
        this.name = name;
        this.userId = userId;
        this.song = song;
        this.cover = cover;
        this.genre = genre;
    }

    public Song(String name, int userId, Blob cover, String genre) {
        this.name = name;
        this.userId = userId;
        this.cover = cover;
        this.genre = genre;
    }

    public boolean insert() {
        try {
            String query = "INSERT INTO Song (user_id, song_name, song, song_cover, genre, release_date)" +
                    " VALUES (?, ?, ?, ?, ?, ?)";

            FileInputStream songStream = new FileInputStream(songFile);
            FileInputStream songImage = new FileInputStream(songCoverFile);

            int userId = searchUserId();

            PreparedStatement preparedStmt = DB.getConnection().prepareStatement(query);
            preparedStmt.setInt(1, userId);
            preparedStmt.setString(2, name);
            preparedStmt.setBlob(3, songStream);
            preparedStmt.setBlob(4, songImage);
            preparedStmt.setString(5, genre);
            preparedStmt.setTimestamp(6, new java.sql.Timestamp(new java.util.Date().getTime()));
            preparedStmt.execute();

            System.out.println("Song " + name + " by " + user + " added to Database");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int searchUserId() {
        try {
            Statement s = DB.getConnection().createStatement();
            String query = "SELECT * FROM User WHERE username = '" + this.user + "'";
            ResultSet rs = s.executeQuery(query);

            while (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int searchId(String name) {
        try {
            Statement s = DB.getConnection().createStatement();
            String query = "SELECT id FROM Song WHERE song_name = '" + name + "'";
            ResultSet rs = s.executeQuery(query);

            while (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static List<Song> returnNewest(int limit) {
        List<Song> songs = new ArrayList<Song>();
        int count = 0;

        try {
            Statement s = DB.getConnection().createStatement();
            String query = "SELECT song_name, genre, user_id, song_cover FROM Song ORDER BY release_date DESC";

            ResultSet rs = s.executeQuery(query);
            int userId;
            String songName, genre;
            Blob songCover;

            while (rs.next() && count++ < limit) {
                songName = rs.getString("song_name");
                genre = rs.getString("genre");
                userId = rs.getInt("user_id");
                songCover = rs.getBlob("song_cover");
                songs.add(new Song(songName, userId, songCover, genre));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return songs;
    }

    public static List<Song> returnLiked(int usId, int limit) {
        List<Song> songs = new ArrayList<Song>();
        int count = 0;

        try {
            Statement s = DB.getConnection().createStatement();
            String query = "SELECT song_name, genre, Song.user_id, song_cover FROM Song JOIN LikedSong ON Song.id = LikedSong.song_id WHERE LikedSong.user_id = '" + usId + "'";

            ResultSet rs = s.executeQuery(query);
            int userId;
            String songName, genre;
            Blob songCover;

            while (rs.next() && count++ < limit) {
                songName = rs.getString("song_name");
                genre = rs.getString("genre");
                userId = rs.getInt("user_id");
                songCover = rs.getBlob("song_cover");
                songs.add(new Song(songName, userId, songCover, genre));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return songs;
    }

    public static List<Song> returnGenre(String genre, int limit) {
        List<Song> songs = new ArrayList<Song>();
        int count = 0;

        try {
            Statement s = DB.getConnection().createStatement();
            String query = "SELECT song_name, genre, user_id, song_cover FROM Song WHERE genre = '"+
                    genre + "' ORDER BY release_date DESC";

            ResultSet rs = s.executeQuery(query);
            int userId;
            String songName, songGenre;
            Blob songCover;

            while (rs.next() && count++ < limit) {
                songName = rs.getString("song_name");
                songGenre = rs.getString("genre");
                userId = rs.getInt("user_id");
                songCover = rs.getBlob("song_cover");
                songs.add(new Song(songName, userId, songCover, genre));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return songs;
    }


    public static List<Song> returnUserSongs(int usId, int limit) {
        List<Song> songs = new ArrayList<Song>();
        int count = 0;

        try {
            Statement s = DB.getConnection().createStatement();
            String query = "SELECT song_name, genre, user_id, song_cover FROM Song WHERE user_id = " + usId;

            ResultSet rs = s.executeQuery(query);
            int userId;
            String songName, genre;
            Blob songCover;

            while (rs.next() && count++ < limit) {
                songName = rs.getString("song_name");
                genre = rs.getString("genre");
                userId = rs.getInt("user_id");
                songCover = rs.getBlob("song_cover");
                songs.add(new Song(songName, userId, songCover, genre));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return songs;
    }

    public static void pause() {
        AppStage.getPlayer().pause();
    }

    public static void resume() {
        AppStage.getPlayer().resume();
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

    public int getId() {
        if (id != null) return id;
        else {
            return Song.searchId(this.name);
        }
    }

    public Blob getSong() {
        if (song == null) {
            try {
                Statement s = DB.getConnection().createStatement();
                String query = "SELECT song FROM Song WHERE song_name = '" + this.name + "' AND user_id = " + this.userId;

                ResultSet rs = s.executeQuery(query);

                while (rs.next()) {
                    Blob content = rs.getBlob("song");
                    this.song = content;
                    return content;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return song;
    }

    public Blob getCover() {
        return cover;
    }
}
