package com.ebingine;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1030
 * @since 1.7
 */
public abstract class Screen {

    public abstract void create(GameContainer gc);

    // To update game state.
    public abstract void update(GameContainer gc, double deltaTime);

    public abstract void clear(GameContainer gc);

    // Saves objects as binary data.
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

    // Loads binary data into an object.
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

    // Saves string key value pairs to a text file.
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
