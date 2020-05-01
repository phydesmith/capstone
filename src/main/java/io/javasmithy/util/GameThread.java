package io.javasmithy.util;


import io.javasmithy.controller.game.GameController;

/** Static class used to create threads separate from the JavaFX thread.
 * @author Peter Hyde-Smith
 */
public class GameThread extends Thread {
    /**
     * The game controller executing the run() method.
     */
    private GameController gc;

    /**
     * @param gameController the game controller that will execute the run() combat loop
     */
    public GameThread(GameController gameController) {
        this.setDaemon(true);
        this.gc = gameController;
    }

    /** Overrides Thread.run(), calls run() from the game controller.
     *
     */
    @Override
    public void run() {
        System.out.println("Log: starting GC run thread.");
        this.gc.run();
    }
}