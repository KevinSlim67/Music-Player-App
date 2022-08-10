package com.kevin.music_streaming_app.features;

import javafx.scene.Node;

import java.util.Timer;
import java.util.TimerTask;

public class Visibility {

    public static void show(Node node) {
        if (!node.isVisible()) {
            node.setVisible(true);
            node.setManaged(true);
        }
    }

    public static void showTemporarly(Node node, int time) {
        //if the node isn't visible, display it for x seconds
        if (!node.isVisible()) {
            node.setVisible(true);
            node.setManaged(true);
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    node.setVisible(false);
                    node.setManaged(false);
                }
            }, time);
        }
    }

    public static void hide(Node node) {
        if (node.isVisible()) {
            node.setVisible(false);
            node.setManaged(false);
        }
    }
}
