package com.ebingine.tiled;

import com.ebingine.GameContainer;
import com.ebingine.utils.Drawable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Provides methods to implement a tiled map in a game.
 * <p>
 * Supports tiled maps where tile layer format is CSV and tile render order
 * is Right Down. Tiled map layers are drawn in the following order: bottom to
 * top (from Tiled map layer view perspective).
 *
 * @author Erika Sankari
 * @version 2016.1220
 * @since 1.7
 */
public class TiledMap implements Drawable {

    /**
     * Holds the parsed tiled map.
     */
    private Document doc;

    /**
     * Tiled map's layers.
     */
    private NodeList layerNodes;

    /**
     * Tiled map's object layers.
     */
    private NodeList objectNodes;

    /**
     * Tiled map's tilesets.
     */
    private NodeList tilesetNodes;

    /**
     * The height of the map as tile count.
     */
    private int mapHeight;

    /**
     * The width of the map as tile count.
     */
    private int mapWidth;

    /**
     * The height of one tile.
     */
    private int tileHeight;

    /**
     * The width of one tile.
     */
    private int tileWidth;

    /**
     * The width of the map as pixels.
     */
    private int pixelWidth;

    /**
     * The height of the map as pixels.
     */
    private int pixelHeight;

    /**
     * All the tile images of all the tilesets.
     */
    private ArrayList<BufferedImage> images = new ArrayList<>();

    /**
     * All tilesets as tileset objects.
     */
    private ArrayList<Tileset> tilesets = new ArrayList<>();

    /**
     * All layers as layer objects.
     */
    private ArrayList<Layer> layers = new ArrayList<>();

    /**
     * All object layers as objectLayer objects.
     */
    private ArrayList<ObjectLayer> objectLayers = new ArrayList<>();

    /**
     * The path where to find the tilesets.
     */
    private String pathToTilesets;

    /**
     * Reads the .tmx file and creates all necessary objects.
     *
     * @param pathToMap path where the map file can be found
     * @param pathToTilesets path where the tilesets can be found
     */
    public TiledMap(String pathToMap, String pathToTilesets) {
        this.pathToTilesets = pathToTilesets;
        File tiledMap = new File(pathToMap);

        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(tiledMap);
            doc.getDocumentElement().normalize();
        } catch (SAXException | IOException | ParserConfigurationException e) {
            System.out.println("Could not parse Tiled map tmx");
            e.printStackTrace();
        }

        // Gets basic information about the map.
        Element map = doc.getDocumentElement();
        mapWidth = Integer.parseInt(map.getAttribute("width"));
        mapHeight = Integer.parseInt(map.getAttribute("height"));
        tileWidth = Integer.parseInt(map.getAttribute("tilewidth"));
        tileHeight = Integer.parseInt(map.getAttribute("tileheight"));
        pixelWidth = (int) Math.floor(mapWidth * tileWidth);
        pixelHeight = (int) Math.floor(mapHeight * tileHeight);

        // Gets different tags and creates objects accordingly.
        layerNodes = doc.getElementsByTagName("layer");
        objectNodes = doc.getElementsByTagName("objectgroup");
        tilesetNodes = doc.getElementsByTagName("tileset");
        createTilesets();
        createTilesetImage();
        createLayers();
        createObjectLayers();

        GameContainer.drawables.add(this);
    }

    /**
     * Creates layer objects with tile objects according to content data.
     */
    private void createLayers() {
        for (int i = 0; i < layerNodes.getLength(); i++) {
            int coordinateY = 0;
            int coordinateX = 0;
            int mapIndex = 0;
            ArrayList<Tile> layerData = new ArrayList<>();

            if (layerNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) layerNodes.item(i);
                Element data = (Element) e.getElementsByTagName("tiles").
                        item(0);

                if (e.getElementsByTagName("tiles").item(0) == null) {
                    data = (Element) e.getElementsByTagName("data").item(0);
                }

                String[] values = data.getTextContent().split
                        ("(,[\\s\\r]+)|([\\s\\r]+)|(,)");

                for (int j = 0; j < values.length - 1; j++) {
                    // Saves only tiles with an image.
                    if (!values[j+1].equals("0")) {
                        layerData.add(new Tile(Integer.parseInt(values[j + 1])
                                - 1, coordinateX, coordinateY));
                    }

                    mapIndex++;

                    if (mapIndex < mapWidth) {
                        coordinateX = coordinateX + tileWidth;
                    } else {
                        coordinateY = coordinateY + tileHeight;
                        coordinateX = 0;
                        mapIndex = 0;
                    }
                }

                layers.add(new Layer(e.getAttribute("name"),
                        Integer.parseInt(e.getAttribute("width")),
                        Integer.parseInt(e.getAttribute("height")),
                        layerData));
            }
        }
    }

    /**
     * Creates tileset objects.
     */
    private void createTilesets() {
        for (int i = 0; i < tilesetNodes.getLength(); i++) {
            if (tilesetNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) tilesetNodes.item(i);
                Element image = (Element) e.getElementsByTagName("image")
                        .item(0);
                tilesets.add(new Tileset(
                        Integer.parseInt(e.getAttribute("firstgid")),
                        e.getAttribute("name"),
                        Integer.parseInt(e.getAttribute("tilewidth")),
                        Integer.parseInt(e.getAttribute("tileheight")),
                        image.getAttribute("source"),
                        Integer.parseInt(image.getAttribute("width")),
                        Integer.parseInt(image.getAttribute("height")),
                        pathToTilesets));
            }
        }
    }

    /**
     * Compiles all tilesets' images to one array.
     */
    private void createTilesetImage() {
        for (int i = 0; i < tilesets.size(); i++) {
            for (int j = 0; j < tilesets.get(i).getTileImages().size(); j++) {
                images.add(tilesets.get(i).getTileImages().get(j));
                tilesets.get(i).getTileImages().get(j).flush();
            }
        }
    }

    /**
     * Creates objectLayer objects with corresponding tiledObjects.
     */
    public void createObjectLayers() {
        for (int i = 0; i < objectNodes.getLength(); i++) {
            ArrayList<TiledObject> tiledObjects = new ArrayList<>();

            if (objectNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) objectNodes.item(i);
                NodeList objectNodes = e.getElementsByTagName("object");

                for (int j = 0; j < objectNodes.getLength(); j++) {
                    Element object = (Element) objectNodes.item(j);
                    tiledObjects.add(new TiledObject(
                            Integer.parseInt(object.getAttribute("id")),
                            object.getAttribute("name"),
                            Integer.parseInt(object.getAttribute("x")),
                            Integer.parseInt(object.getAttribute("y")),
                            Integer.parseInt(object.getAttribute("width")),
                            Integer.parseInt(object.getAttribute("height"))
                            ));
                }

                objectLayers.add(new ObjectLayer(e.getAttribute("name"),
                        tiledObjects));
            }
        }
    }

    /**
     * Returns an ObjectLayer from Tiled map.
     *
     * @param name the name of the ObjectLayer
     * @return an object layer
     */
    public ObjectLayer getObjectLayer(String name) {
        ObjectLayer toReturn = null;

        for (int i = 0; i < objectLayers.size(); i++) {
            if (objectLayers.get(i).getName().equals(name)) {
                toReturn = objectLayers.get(i);
            }
        }

        return toReturn;
    }

    /**
     * Returns a TiledObject specified in Tiled map.
     *
     * @param id the id of the TiledObject
     * @return a tiled object
     */
    public TiledObject getObject(int id) {
        TiledObject toReturn = null;

        for (int i = 0; i < objectLayers.size(); i++) {
            for (int j = 0; j < objectLayers.get(i).getTiledObjects().size();
                 j++) {
                if (objectLayers.get(i).getTiledObjects().get(j).getId()
                        == id) {
                    toReturn = objectLayers.get(i).getTiledObjects().get(j);
                }
            }
        }

        return toReturn;
    }

    /**
     * Returns a TiledObject specified in Tiled map.
     *
     * @param name the name of the TiledObject
     * @return a tiled object
     */
    public TiledObject getObject(String name) {
        TiledObject toReturn = null;

        for (int i = 0; i < objectLayers.size(); i++) {
            for (int j = 0; j < objectLayers.get(i).getTiledObjects().size();
                 j++) {
                if (objectLayers.get(i).getTiledObjects().get(j).getName().
                        equals(name)) {
                    toReturn = objectLayers.get(i).getTiledObjects().get(j);
                }
            }
        }

        return toReturn;
    }

    /**
     * Returns a Layer containing information of a tiled map layer.
     *
     * @param name the name of the layer
     * @return a layer object
     */
    public Layer getLayer(String name) {
        Layer toReturn = null;

        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).getName().equals(name)) {
                toReturn = layers.get(i);
            }
        }

        return toReturn;
    }

    /**
     * Draws the tiled map.
     * 
     * The map layers are drawn in the following order: bottom to
     * top (from Tiled map layer view perspective).
     *
     * @param g2d a graphics object for drawing
     */
    @Override
    public void draw(Graphics2D g2d) {
        for (int i = 0; i < layers.size(); i++) {
            for (int j = 0; j < layers.get(i).getTiles().size(); j++) {
                g2d.drawImage(images.get(layers.get(i).getTiles().get(j)
                                .getImageCoordinate()),
                        layers.get(i).getTiles().get(j).getX(),
                        layers.get(i).getTiles().get(j).getY(),
                        tileWidth,
                        tileHeight,
                        null);
            }
        }
    }

    /**
     * Returns map's height.
     *
     * @return the height in tiles
     */
    public int getMapHeight() {
        return mapHeight;
    }

    /**
     * Returns map's width.
     *
     * @return the width in tiles
     */
    public int getMapWidth() {
        return mapWidth;
    }

    /**
     * Returns a tile's height.
     *
     * @return a tile's height
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * Returns a tile's width.
     *
     * @return a tile's width
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * Returns map's width.
     *
     * @return the map's width in pixels
     */
    public int getPixelWidth() {
        return pixelWidth;
    }

    /**
     * Returns map's height.
     *
     * @return the map's height in pixels
     */
    public int getPixelHeight() {
        return pixelHeight;
    }

    /**
     * Returns an array containing all of the map's ObjectLayer object.
     *
     * @return an array of object layers
     */
    public ArrayList<ObjectLayer> getObjectLayers() {
        return objectLayers;
    }

    /**
     * Returns an array containing all of the map's Layer object.
     *
     * @return an array of layers
     */
    public ArrayList<Layer> getLayers() {
        return layers;
    }

    /**
     * Returns an array of all the images in all the tilesets.
     *
     * @return all the images of all the tilesets
     */
    public ArrayList<BufferedImage> getImages() {
        return images;
    }
}
