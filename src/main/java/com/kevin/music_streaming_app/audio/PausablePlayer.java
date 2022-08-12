package com.kevin.music_streaming_app.audio;

import com.kevin.music_streaming_app.AppStage;
import com.kevin.music_streaming_app.db.RecentlyListened;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class PausablePlayer {

    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;

    // keeps track of which frame the song is on
    private int pausedOnFrame = 1000;

    // the player actually doing all the work
    private Player player = null;

    // locking object used to communicate with player thread
    private final Object playerLock = new Object();

    // status variable what player thread is doing/supposed to do
    private int playerStatus = NOTSTARTED;

    public PausablePlayer(final Blob song) {
        AppStage.getThreads().forEach(t -> t.stop());
        AppStage.getThreads().clear();
        InputStream inputStream = null;
        try {
            inputStream = song.getBinaryStream();
            this.player = new Player(inputStream);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts playback (resumes if paused)
     */
    public void play() {
        synchronized (playerLock) {
            switch (playerStatus) {
                case NOTSTARTED:
                    final Runnable r = () -> playInternal();
                    final Thread t = new Thread(r);
                    t.setDaemon(true);
                    t.setPriority(Thread.MAX_PRIORITY);
                    playerStatus = PLAYING;
                    AppStage.getThreads().add(t);

                    try {
                        Thread.sleep(50); //to add a little delay between switching songs
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    t.start();
                    break;
                case PAUSED:
                    resume();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Pauses playback. Returns true if new state is PAUSED.
     */
    public boolean pause() {
        synchronized (playerLock) {
            if (playerStatus == PLAYING) {
                playerStatus = PAUSED;
            }
            return playerStatus == PAUSED;
        }
    }

    /**
     * Resumes playback. Returns true if the new state is PLAYING.
     */
    public boolean resume() {
        synchronized (playerLock) {
            if (playerStatus == PAUSED) {
                playerStatus = PLAYING;
                playerLock.notifyAll();
            }
            return playerStatus == PLAYING;
        }
    }

    /**
     * Stops playback. If not playing, does nothing
     */
    public void stop() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
            playerLock.notifyAll();
        }
    }

    private void playInternal() {
        while (playerStatus != FINISHED) {
            try {
                if (!player.play(1)) {
                    break;
                }
            } catch (final JavaLayerException e) {
                break;
            }
            // check if paused or terminated
            synchronized (playerLock) {
                while (playerStatus == PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
                        // terminate player
                        break;
                    }
                }
            }
        }
        player.close();
    }


    public int getPausedOnFrame() {
        return pausedOnFrame;
    }

    public void setPausedOnFrame(int pausedOnFrame) {
        this.pausedOnFrame = pausedOnFrame;
    }
}