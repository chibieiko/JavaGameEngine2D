package com.ebingine.tiled;

import java.util.ArrayList;

/**
 * Tiled map's object layer.
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class ObjectLayer {

    /**
     * The name of the object layer.
     */
    private String name;

    /**
     * Contains all the TiledObjects on this object layer.
     */
    private ArrayList<TiledObject> tiledObjects = new ArrayList<>();

    /**
     * Creates the object layer.
     *
     * @param name the name of the object layer
     * @param tiledObjects the objects on this object layer
     */
    public ObjectLayer(String name, ArrayList<TiledObject> tiledObjects) {
        this.name = name;
        this.tiledObjects = tiledObjects;
    }

    /**
     * Returns the name of this object layer.
     *
     * @return the name of the object layer
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the objects of this object layer.
     *
     * @return the object of this object layer
     */
    public ArrayList<TiledObject> getTiledObjects() {
        return tiledObjects;
    }
}
