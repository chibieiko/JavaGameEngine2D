package com.ebingine.tiled;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1209
 * @since 1.7
 */
public class ObjectLayer {

    private String name;
    private ArrayList<Object> objects = new ArrayList<>();

    public ObjectLayer(String name, ArrayList<Object> objects) {
        this.name = name;
        this.objects = objects;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }
}
