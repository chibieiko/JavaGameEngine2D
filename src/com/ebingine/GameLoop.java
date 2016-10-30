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
public class GameLoop implements Runnable{

    private int width = 800;
    private int height = 600;
    private String title = "Ebingine 1.0";
    private Thread thread;
    private Game game;
    // Indicates whether the game loop is running or not.
    private boolean running = false;
    // Enables 60fps
    private double frameRate = 1.0 / 60.0;

    public GameLoop() {

    }

    public void start() {
        // If game is running, doesn't start it again.
        if (running)
            return;

        // todo initialize engine components

        // GameLoop implements Runnable so it can be passed to Thread.
        thread = new Thread(this);
        // Starts the game loop.
        thread.run();
    }

    public void run() {
        running = true;
        // Divider converts the time to seconds.
        double startTime = System.nanoTime() / 1000000000.0;
        double stopTime;
        double loopTime;
        double unprocessedTime = 0;

        // Loops the game
        while (running) {
            boolean render = false;

            stopTime = System.nanoTime() / 1000000000.0;
            // Indicates how long it takes for the while loop to loop once.
            // Imitates delta time.
            loopTime = stopTime - startTime;
            startTime = stopTime;
            unprocessedTime += loopTime;

            // Updates game every time loopTime in total equals or goes over
            // frameRate.
            while (unprocessedTime >= frameRate) {

                // Updates game, todo consider passing frameRate as float
                game.update(this, frameRate);

                unprocessedTime -= frameRate;
                render = true;
            }

            if (render) {
                // todo clear screen
                // render the game game.render(this, renderer);
                // update screen
            } else {
                // When there is nothing to render, puts thread to sleep
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        clear();
    }

    public void stop() {
        // No need to stop if it's already stopped.
        if (!running)
            return;

        // Stops the game loop.
        running = false;
    }

    public void clear() {

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
