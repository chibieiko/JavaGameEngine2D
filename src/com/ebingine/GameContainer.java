package com.ebingine;

import com.ebingine.GUI.Window;
import com.ebingine.gameObjects.GameObject;
import com.ebingine.utils.Drawable;
import com.ebingine.utils.Input;

import java.awt.*;
import java.util.ArrayList;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1025
 * @since 1.7
 */
public class GameContainer implements Runnable {

    public static int width = 4000;
    public static int height = 3000;
    private String title = "Ebingine";
    private Thread thread;
    private Game game;
    private Window window;
    public Input input;
    private Render renderer;
    public static ArrayList<Drawable> drawables = new ArrayList<>();

    // Indicates whether the game loop is running or not.
    private boolean running = false;
    // Limits frame rate to 60fps.
    private double frameRate = 1.0 / 60.0;

    public GameContainer(Game game) {
        this.game = game;

        // Draws images faster.
        System.setProperty("sun.java2d.opengl", "true");

        start();
    }

    public void start() {
        // If game is running, doesn't start it again.
        if (running)
            return;

        window = new Window(this);
        input = new Input(this, 0);
        game.create(this);

        renderer = new Render(this);

        // GameContainer implements Runnable so it can be passed to Thread.
        thread = new Thread(this);
        // Starts the game loop.
        thread.start();
    }

    public void run() {
        running = true;
        // Divider converts the time to seconds.
        float startTime = (float) (System.nanoTime() / 1000000000.0);
        float stopTime;
        float loopTime;
        float unprocessedTime = 0;

        // For calculating fps.
        double frameTime = 0;
        int frames = 0;

        // Loops the game.
        while (running) {
            boolean render = false;

            stopTime = (float) (System.nanoTime() / 1000000000.0);
            // Indicates how long it takes for the while loop to loop once.
            // Imitates delta time. Enables smooth movement.
            loopTime = stopTime - startTime;
            startTime = stopTime;

            unprocessedTime += loopTime;
            frameTime += loopTime;

            // Updates game every time loopTime in total equals or goes over
            // frameRate. Basically limits fps to frame rate value.
            while (unprocessedTime >= frameRate) {

                // Updates game.
                game.update(this, frameRate);

                unprocessedTime -= frameRate;
                render = true;

                // Prints fps (frames per second).
                if (frameTime >= 1) {
                    frameTime = 0;
                    System.out.println(frames);
                    frames = 0;
                }
            }

            if (render) {
                // Repaints the screen.
                window.update();

                frames++;
            } else {
                // When there is nothing to render, puts thread to sleep.
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

    public void drawGameObject(GameObject go) {
        drawables.add(new Drawable(go.getImg(), go.getX(), go.getY(), go
                .getWidth(), go.getHeight()));
    }

    public void drawImg(Image img, int x, int y, int width, int height) {
        drawables.add(new Drawable(img, x, y, width, height));
    }

    public void clear() {
        window.clear();
        game.clear(this);
        drawables.clear();
    }

    public Input getInput() {
        return input;
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

    public Window getWindow() {
        return window;
    }

    public Game getGame() {
        return game;
    }

    // Stops one of game's screens, changes to another screen and renders that
    // screen.
    // todo when returning to the previous screen, does it look the same?
    public void setGame(Game game) {
        stop();
        this.game = game;
        run();
    }

    public Render getRenderer() {
        return renderer;
    }
}
