package com.kevin.music_streaming_app.audio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioPlayer extends Thread {
    private static String filePath;
    private static String previousFilePath;
    private static Player jlPlayer;


    @Override
    public void run() {

    }

    public static void play(String newFilePath) {
        previousFilePath = filePath;
        filePath = newFilePath;

        if (!filePath.equals(previousFilePath)) {
            try {
                FileInputStream fileInputStream = new FileInputStream(filePath);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                jlPlayer = new Player(bufferedInputStream);
            } catch (Exception e) {
                System.out.println("Problem playing mp3 file " + filePath);
                System.out.println(e.getMessage());
            }

            try {
                jlPlayer.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close() {
        if (jlPlayer != null) jlPlayer.close();
    }
}


