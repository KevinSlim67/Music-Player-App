package com.kevin.music_streaming_app.audio;

import java.io.InputStream;
import java.sql.Blob;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class AudioPlayer extends Thread {
    private Blob data;
    private AdvancedPlayer jlPlayer;
    private int pausedOnFrame = 0;

    public AudioPlayer(Blob song) {
        data = song;
    }

    @Override
    public void run() {
       play(data);
    }

    public void play(Blob song) {
        data = song;

        try {
            InputStream inputStream = data.getBinaryStream(); //makes blob data readable
            //BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            jlPlayer = new AdvancedPlayer(inputStream);
            jlPlayer.setPlayBackListener(new PlaybackListener() {
                @Override
                public void playbackFinished(PlaybackEvent event) {
                    pausedOnFrame = event.getFrame();
                }
            });
        } catch (Exception e) {
            System.out.println("Problem playing mp3 file " + data);
            System.out.println(e.getMessage());
        }

        try {
            jlPlayer.play(pausedOnFrame, Integer.MAX_VALUE);
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        if (jlPlayer != null) jlPlayer.close();
    }


    public void setSong(Blob song) {
        data = song;
    }
}


