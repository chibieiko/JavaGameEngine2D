package com.ebingine;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * Provides methods for handling a game screen.
 * <p>
 * All abstract methods are called by the GameContainer class. Class also
 * provides save and load methods. Game maker can save whole objects or choose
 * some values to save as text.
 *
 * @author Erika Sankari
 * @version 2016.1219
 * @since 1.7
 */
public abstract class Screen {

    /**
     * Creates the contents of the game screen.
     *
     * @param gc a game container
     */
    public abstract void create(GameContainer gc);

    /**
     * Updates game state.
     *
     * @param gc a game container
     * @param deltaTime a fixed time step
     */
    public abstract void update(GameContainer gc, double deltaTime);

    /**
     * Clears game screen.
     * <p>
     * Called by the GameContainer class when the game is excited.
     *
     * @param gc a game container
     */
    public abstract void clear(GameContainer gc);

    /**
     * Saves objects as binary data.
     *
     * @param path a path where to save the file
     * @param object the object intended to save
     */
    public void saveGame(String path, Object object) {
        File file = new File(path + ".dat");
        System.out.println(file);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads binary data into an object.
     *
     * @param path a path where to retrieve a save file from
     * @return object including save file information
     * @throws FileNotFoundException when the file is not valid
     */
    public Object loadSave(String path) throws FileNotFoundException {
        File file = new File(path + ".dat");
        Object toReturn;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            toReturn = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return toReturn;
    }

    /**
     * Saves string key - value pairs to a text file separated by a comma.
     *
     * @param values the values intended to save
     * @param path a path where to save the file
     */
    public void saveInfo(String[] values, String path) {
        File file = new File(path + ".txt");
        Charset charset = Charset.defaultCharset();
        // Writer inside try for autoclosing
        try (BufferedWriter writer =
                     Files.newBufferedWriter(file.toPath(), charset)) {
            for (int i = 0; i < values.length; i++) {
                writer.write(values[i] + ",");
            }

        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /**
     * Returns string key - value pairs.
     *
     * @param path a path where to retrieve a save file from
     * @return key - value pairs as a String array
     */
    public String[] loadInfo(String path) {
        File file = new File(path + ".txt");
        String[] values;
        try {
            java.util.List<String> fileContent =
                    Files.readAllLines(file.toPath(),
                            Charset.defaultCharset());
            String allData = "";

            for (String s : fileContent) {
                // Gathers all the text from the file into a String
                allData += s;
            }

            values = allData.split(",");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return values;
    }
}
