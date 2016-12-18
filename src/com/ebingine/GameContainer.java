package com.ebingine;

import com.ebingine.GUI.Window;
import com.ebingine.utils.Camera;
import com.ebingine.utils.Drawable;
import com.ebingine.utils.Input;
import com.ebingine.utils.Utils;

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

    private String title = "Ebingine v0.0.1";
    private Thread thread;
    private Screen screen;
    private Window window;
    public static Input input;
    public static Utils utils;
    public static ArrayList<Drawable> drawables = new ArrayList<>();
    private Camera camera;
    // Indicates whether the screen loop is running or not.
    private boolean running = false;
    // Limits frame rate to 60fps.
    private double deltaTime = 1.0 / 60.0;

    public GameContainer(Screen screen) {
        this.screen = screen;
        camera = new Camera(this);
        // Draws images faster.
        System.setProperty("sun.java2d.opengl", "true");
    }

    public void start() {
        // If screen is running, doesn't start it again.
        if (running)
            return;

        window = new Window(this);
        input = new Input(this, 0);
        utils = new Utils();
        screen.create(this);

        // GameContainer implements Runnable so it can be passed to Thread.
        thread = new Thread(this);
        // Starts the screen loop thread.
        thread.start();
    }

    // Runnable's run method;
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

    public void stop() {
        // No need to stop if it's already stopped.
        if (!running)
            return;

        // Stops the screen loop.
        running = false;
    }

    public void clear() {
        window.clear();
        screen.clear(this);
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

    public Screen getScreen() {
        return screen;
    }

    // Stops one of screen's screens, changes to another screen and renders that
    // screen.
    public void setScreen(Screen screen) {
        stop();
        this.screen = screen;
        run();
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void clearRender() {
        drawables.clear();
    }
}
