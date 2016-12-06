package com.ebingine.tiled;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * TODO Short Description
 * <p>
 * TODO description and @since
 *
 * @author Erika Sankari
 * @version 2016.1204
 * @since 1.8
 */
public class TiledMap {

    File tiledMap;
    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    Document doc;
    NodeList layerNodes;
    NodeList objects;
    NodeList tilesetNodes;
    int mapHeight;
    int mapWidth;
    int tileHeight;
    int tileWidth;
    ArrayList<BufferedImage> images = new ArrayList<>();
    ArrayList<Tileset> tilesets = new ArrayList<>();
    ArrayList<Layer> layers = new ArrayList<>();

    public TiledMap(String path) {
        tiledMap = new File(path);

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

        Element map = (Element) doc.getDocumentElement();
        mapWidth = Integer.parseInt(map.getAttribute("width"));
        mapHeight = Integer.parseInt(map.getAttribute("height"));
        tileWidth = Integer.parseInt(map.getAttribute("tilewidth"));
        tileHeight = Integer.parseInt(map.getAttribute("tileheight"));

        layerNodes = doc.getElementsByTagName("layer");
        objects = doc.getElementsByTagName("objectgroup");
        tilesetNodes = doc.getElementsByTagName("tileset");
        createTilesets();
        createTilesetImage();
        createLayers();
    }

    private void createLayers() {
        HashMap<Integer, Integer> layerData = new HashMap<>();
        int mapIndex = 0;
        //  for (int i = 0; i < layerNodes.getLength(); i++) {
        //    if (layerNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
        Element e = (Element) layerNodes.item(0);
        Element data = (Element) e.getElementsByTagName("data").item(0);
        String[] values = data.getTextContent().split("(,[\\s\\r]+)|([\\s\\r]+)|(,)");
        String[] temp = new String[mapWidth * tileWidth + mapHeight *
                tileHeight];
        //    int count = 0;
        for (int i = 0; i < temp.length; i++) {
            temp[i] = values[i + 1];
            //     System.out.print(temp[i] + " ");
            layerData.put(Integer.parseInt(values[i + 1]), mapIndex);
            mapIndex++;

            // todo Create Layer object

         /*   count++;
            if (count == 100) {
                System.out.println("");
                count = 0;
            }*/
        }
    //}
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
                        Integer.parseInt(image.getAttribute("height"))));
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

    // Returns object rectangle.
    public void getObjectRectangle(String object) {

    }
}
