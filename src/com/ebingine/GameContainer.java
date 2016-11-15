package com.ebingine;

import com.ebingine.GUI.Screen;
import com.ebingine.utils.Input;

import java.awt.*;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1025
 * @since 1.7
 */
public class GameContainer implements Runnable{

    public static int width = 4000;
    public static int height = 3000;
    private String title = "Ebingine";
    private Thread thread;
    private Game game;
    private Screen screen;
    public Input input;
    private Render renderer;

    // Indicates whether the game loop is running or not.
    private boolean running = false;
    // Limits frame rate to 60fps.
    private double frameRate = 1.0 / 60.0;

    public GameContainer(Game game) {
        this.game = game;



        System.setProperty("sun.java2d.opengl", "true");
    }

    public void start() {
        // If game is running, doesn't start it again.
        if (running)
            return;

        screen = new Screen(this);
        renderer = new Render(this);
        input = new Input(this, 0);
        String[] keyArray = {"SPACE", "w", "a", "s", "d"};
        input.addInputKey("UP", 0, 5);
        input.addInputKey("DOWN", 0, -5);
        input.addInputKey("RIGHT", 5, 0);
        input.addInputKey("LEFT", -5, 0);

        // GameContainer implements Runnable so it can be passed to Thread.
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

        // For calculating fps.
        double frameTime = 0;
        int frames = 0;
        // Loops the game.
        while (running) {
            boolean render = false;

            stopTime = System.nanoTime() / 1000000000.0;
            // Indicates how long it takes for the while loop to loop once.
            // Imitates delta time. Enables smooth movement.
            loopTime = stopTime - startTime;
            startTime = stopTime;

            unprocessedTime += loopTime;
            frameTime += loopTime;

            // Updates game every time loopTime in total equals or goes over
            // frameRate.
          //  while (unprocessedTime >= frameRate) {

                // Updates game, todo consider passing frameRate as float
                game.update(this, frameRate);

                unprocessedTime -= frameRate;
                render = true;

                // Prints fps (frames per second)
                if (frameTime >= 1) {
                    frameTime = 0;
                    System.out.println(frames);
                    frames = 0;
                }
           // }

            if (render) {
                renderer.clearScreen();
                // render the game game.render(this, renderer);
                screen.update();
                frames++;
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
        screen.clear();
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

    public Screen getScreen() {
        return screen;
    }
}
