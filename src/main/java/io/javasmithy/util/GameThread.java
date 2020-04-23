package io.javasmithy.util;

import io.javasmithy.controller.game.GameController;

public class GameThread extends Thread {
    private GameController gc;

    public GameThread(GameController gameController) {
        this.setDaemon(true);
        this.gc = gameController;
    }

    @Override
    public void run() {
        // operate on mSomeClass
        System.out.println("DEBUG - In Game Thread");
        this.gc.run();
    }
}