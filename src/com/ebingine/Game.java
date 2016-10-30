package com.ebingine;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1025
 * @since 1.7
 */
public class Game implements Runnable{

    private Thread thread;
    private boolean running = false;

    public Game() {

    }

    public void start() {
        // If game is running, doesn't start it again
        if (running)
            return;

        // todo initialize engine components

        // Game implements Runnable so it can be passed to Thread
        thread = new Thread(this);
        // Starts the game loop
        thread.run();

    }

    public void run() {
        //
        double firstTime = System.nanoTime() / 1000000000.0;

        // While the game loop is running
        while (running) {

        }
    }

    public void stop() {
        // No need to stop if it's already stopped
        if (!running)
            return;

        // Stops the game loop
        running = false;
    }

    public void clear() {

    }

}
