package com.ebingine;

import com.ebingine.GUI.Window;
import com.ebingine.utils.Camera;
import com.ebingine.utils.Drawable;
import com.ebingine.utils.Input;
import com.ebingine.utils.Utils;

import java.util.ArrayList;

/**
 * Holds the game loop.
 * <p>
 * Creates necessary classes for the game loop and loops the game.
 *
 * @author Erika Sankari
 * @version 2016.1219
 * @since 1.7
 */
public class GameContainer implements Runnable {

    /**
     * Holds the width of the game world.
     */
    public static int width;

    /**
     * Holds the height of the game world.
     */
    public static int height;

    /**
     * Name and version of the game engine.
     */
    private String title = "Ebingine v0.0.1";

    /**
     * Helps to loop the game.
     */
    private Thread thread;

    /**
     * The active game screen.
     */
    private Screen screen;

    /**
     * The window that displays the game.
     */
    private Window window;

    /**
     * Holds the input logic.
     */
    public static Input input;

    /**
     * Contains few helpful methods.
     */
    public static Utils utils;

    /**
     * Contains the things that will be drawn in the game.
     */
    public static ArrayList<Drawable> drawables = new ArrayList<>();

    /**
     * Determines the size of the window.
     *
     * Basically determines how much the player can
     * see of the game world.
     */
    private Camera camera;

    /**
     * Indicates whether the screen loop is running or not.
     */
    private boolean running = false;

    /**
     * Limits frame rate to fixed 60fps.
     */
    private double deltaTime = 1.0 / 60.0;

    /**
     * Initializes the game container.
     *
     * @param screen currently active game screen
     */
    public GameContainer(Screen screen) {
        width = 2000;
        height = 1500;
        this.screen = screen;
        camera = new Camera(this);
        // Draws images faster.
        System.setProperty("sun.java2d.opengl", "true");
    }

    /**
     * Initializes necessary classes and starts the game loop.
     */
    public void start() {
        // If screen is running, doesn't start it again.
        if (running) {
            return;
        }

        window = new Window(this);
        input = new Input(this);
        utils = new Utils();
        screen.create(this);

        // GameContainer implements Runnable so it can be passed to Thread.
        thread = new Thread(this);
        // Starts the screen loop thread.
        thread.start();
    }

    /**
     * Runs the game loop.
     */
    @Override
    public void run() {
        running = true;
        // Divider converts the time to seconds.
        double currentTime = System.nanoTime() / 1_000_000_000.0;
        double newTime;
        // Tells how long it takes to loop through the screen.
        double loopTime;
        double unprocessedTime = 0;

        // For calculating fps.
        double frameTime = 0;
        int frames = 0;
        boolean render;

        // Loops the screen.
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

            // Updates screen every time loopTime in total equals or goes over
            // deltaTime. Basically limits fps to frame rate value.
            while (unprocessedTime >= deltaTime) {

                // Updates screen with fixed deltaTime.
                screen.update(this, deltaTime);

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

    /**
     * Stops the game loop.
     */
    public void stop() {
        // No need to stop if it's already stopped.
        if (!running) {
            return;
        }

        // Stops the screen's game loop.
        running = false;
    }

    /**
     * Clears window, screen and drawables array.
     */
    public void clear() {
        window.clear();
        screen.clear(this);
        drawables.clear();
    }

    /**
     * Returns game world's width.
     *
     * @return the width of the game world.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets game world's width.
     *
     * @param width the new width of the game world
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns game world's height.
     *
     * @return the height of the game world
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets game world's height.
     *
     * @param height the new height of the game world.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns game's title.
     *
     * @return the title of the game
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets a new title for the game.
     *
     * @param title the new title for the game
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the game's window.
     *
     * @return the window of the game
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Returns the currently active game screen.
     *
     * @return the currently active game screen.
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     * Changes screens.
     *
     * Stops the current game screen, changes to another screen and loops
     * that screen.
     *
     * @param screen the screen to change to
     */
    public void setScreen(Screen screen) {
        stop();
        this.screen = screen;
        run();
    }

    /**
     * Returns the game's camera.
     *
     * @return the game's camera
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * Sets a new camera for the game.
     *
     * @param camera the new camera
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * Clears the array of drawables.
     */
    public void clearRender() {
        drawables.clear();
    }
}
