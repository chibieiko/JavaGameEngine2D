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
 * TODO Short Description
 * <p>
 * Supports Tiled maps where tile layer format is CSV and tile render order
 * is Right Down. Tiled maps are drawn in the following order: bottom to top
 * (from Tiled map view perspective).
 * TODO @since
 *
 * @author Erika Sankari
 * @version 2016.1204
 * @since 1.8
 */
public class TiledMap implements Drawable {

    private File tiledMap;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document doc;
    private NodeList layerNodes;
    private NodeList objectNodes;
    private NodeList tilesetNodes;
    private int mapHeight;
    private int mapWidth;
    private int tileHeight;
    private int tileWidth;
    private int pixelWidth;
    private int pixelHeight;
    private ArrayList<BufferedImage> images = new ArrayList<>();
    private ArrayList<Tileset> tilesets = new ArrayList<>();
    private ArrayList<Layer> layers = new ArrayList<>();
    private ArrayList<ObjectLayer> objectLayers = new ArrayList<>();
    private String pathToTilesets;

    public TiledMap(String pathToMap, String pathToTilesets) {
        this.pathToTilesets = pathToTilesets;
        tiledMap = new File(pathToMap);

        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(tiledMap);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        System.out.println("Root Element: " + doc.getDocumentElement()
                .getNodeName());

        Element map = doc.getDocumentElement();
        mapWidth = Integer.parseInt(map.getAttribute("width"));
        mapHeight = Integer.parseInt(map.getAttribute("height"));
        tileWidth = Integer.parseInt(map.getAttribute("tilewidth"));
        tileHeight = Integer.parseInt(map.getAttribute("tileheight"));
        pixelWidth = mapWidth * tileWidth;
        pixelHeight = mapHeight * tileHeight;

        layerNodes = doc.getElementsByTagName("layer");
        objectNodes = doc.getElementsByTagName("objectgroup");
        tilesetNodes = doc.getElementsByTagName("tileset");
        createTilesets();
        createTilesetImage();
        createLayers();
        createObjectLayers();

        GameContainer.drawables.add(this);
    }

    private void createLayers() {

        for (int i = 0; i < layerNodes.getLength(); i++) {
            int coordinateY = 0;
            int coordinateX = 0;
            int mapIndex = 0;
            ArrayList<Tile> layerData = new ArrayList<>();

            if (layerNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) layerNodes.item(i);
                Element data = (Element) e.getElementsByTagName("tiles").item(0);
                if (e.getElementsByTagName("tiles").item(0) == null) {
                    data = (Element) e.getElementsByTagName("data").item(0);
                }

                String[] values = data.getTextContent().split("(,[\\s\\r]+)|([\\s\\r]+)|(,)");


                for (int j = 0; j < values.length - 1; j++) {
                    // Saves only tiles with an image.
                    if (!values[j+1].equals("0")) {
                        layerData.add(new Tile(Integer.parseInt(values[j + 1]) - 1,
                                coordinateX, coordinateY));
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

    private void createTilesetImage() {
        for (int i = 0; i < tilesets.size(); i++) {
            for (int j = 0; j < tilesets.get(i).getTileImages().size(); j++) {
                images.add(tilesets.get(i).getTileImages().get(j));
            }
        }
    }

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

    // Returns an ObjectLayer from Tiled map.
    public ObjectLayer getObjectLayer(String name) {
        ObjectLayer toReturn = null;
        for (int i = 0; i < objectLayers.size(); i++) {
            if (objectLayers.get(i).getName().equals(name)) {
                toReturn = objectLayers.get(i);
            }
        }

        return toReturn;
    }

    // Returns an TiledObject specified in Tiled map.
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

    // Returns an TiledObject specified in Tiled map.
    public TiledObject getObject(String name) {
        TiledObject toReturn = null;
        for (int i = 0; i < objectLayers.size(); i++) {
            for (int j = 0; j < objectLayers.get(i).getTiledObjects().size(); j++) {
                if (objectLayers.get(i).getTiledObjects().get(j).getName().
                        equals(name)) {
                    toReturn = objectLayers.get(i).getTiledObjects().get(j);
                }
            }
        }

        return toReturn;
    }

    public Layer getLayer(String name) {
        Layer toReturn = null;
        for (int i = 0; i < layers.size(); i++) {
            if (layers.get(i).getName().equals(name)) {
                toReturn = layers.get(i);
            }
        }

        return toReturn;
    }

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

    public int getMapHeight() {
        return mapHeight;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getPixelWidth() {
        return pixelWidth;
    }

    public int getPixelHeight() {
        return pixelHeight;
    }

    public ArrayList<ObjectLayer> getObjectLayers() {
        return objectLayers;
    }

    public ArrayList<Layer> getLayers() {
        return layers;
    }

    public ArrayList<BufferedImage> getImages() {
        return images;
    }
}
