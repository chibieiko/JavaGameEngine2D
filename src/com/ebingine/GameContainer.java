package com.ebingine;

import com.ebingine.GUI.Window;
import com.ebingine.utils.Camera;
import com.ebingine.utils.Drawable;
import com.ebingine.utils.Input;

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

    private String title = "Ebiswingine";
    private Thread thread;
    private Game game;
    private Window window;
    public static Input input;
    public static ArrayList<Drawable> drawables = new ArrayList<>();
    private Camera camera;
    // Indicates whether the game loop is running or not.
    private boolean running = false;
    // Limits frame rate to 60fps.
    private double deltaTime = 1.0 / 60.0;

    public GameContainer(Game game) {
        this.game = game;
        camera = new Camera(this);
        // Draws images faster.
        System.setProperty("sun.java2d.opengl", "true");
    }

    public void start() {
        // If game is running, doesn't start it again.
        if (running)
            return;

        window = new Window(this);
        input = new Input(this, 0);
        game.create(this);

        // GameContainer implements Runnable so it can be passed to Thread.
        thread = new Thread(this);
        // Starts the game loop thread.
        thread.start();
    }

    // Runnable's run method;
    @Override
    public void run() {
        running = true;
        // Divider converts the time to seconds.
        double currentTime = System.nanoTime() / 1_000_000_000.0;
        double newTime;
        // Tells how long it takes to loop through the game.
        double loopTime;
        double unprocessedTime = 0;

        // For calculating fps.
        double frameTime = 0;
        int frames = 0;
        boolean render;

        // Loops the game.
        while (running) {
            render = false;

            newTime = System.nanoTime() / 1_000_000_000.0;
            // Indicates how long it takes for the while loop to loop once.
            loopTime = newTime - currentTime;
            if (loopTime > 0.25)
                loopTime = 0.25;

            currentTime = newTime;

            unprocessedTime += loopTime;
            frameTime += loopTime;

            // Updates game every time loopTime in total equals or goes over
            // deltaTime. Basically limits fps to frame rate value.
            while (unprocessedTime >= deltaTime) {

                // Updates game with fixed deltaTime.
                game.update(this, deltaTime);

                unprocessedTime -= deltaTime;
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
    // todo multiple screens
    public void setGame(Game game) {
        stop();
        this.game = game;
        run();
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
