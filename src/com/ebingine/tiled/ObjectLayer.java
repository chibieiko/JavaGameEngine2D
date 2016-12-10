package com.ebingine.tiled;

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
    private ArrayList<TiledObject> tiledObjects = new ArrayList<>();

    public ObjectLayer(String name, ArrayList<TiledObject> tiledObjects) {
        this.name = name;
        this.tiledObjects = tiledObjects;
    }

    public String getName() {
        return name;
    }

    public ArrayList<TiledObject> getTiledObjects() {
        return tiledObjects;
    }
}
