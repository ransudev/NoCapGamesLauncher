package com.mycompany.nocapgameslauncher.gui.resourceManager;

import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;
import java.awt.*;

import com.mycompany.nocapgameslauncher.gui.components.*;
import com.mycompany.nocapgameslauncher.gui.panels.*;
import com.mycompany.nocapgameslauncher.gui.utilities.*;

public class resourceLoader {
    private static final boolean DEBUG = true;
    
    public static ImageIcon loadIcon(String resourcePath) {
        if (DEBUG) System.out.println("loadIcon called with: " + resourcePath);
        URL url = HeaderCreator.class.getResource(resourcePath);
        if (url != null) {
            if (DEBUG) System.out.println(" → Loaded from classpath: " + url);
            return new ImageIcon(url);
        }
        String rp = resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
        File f1 = new File("src/main/resources/" + rp);
        File f2 = new File("src/" + rp);
        File pick = f1.exists() ? f1 : (f2.exists() ? f2 : null);
        if (pick != null) {
            if (DEBUG) System.out.println(" → Loaded from filesystem: " + pick.getAbsolutePath());
            return new ImageIcon(pick.getAbsolutePath());
        }
        if (DEBUG) {
            System.out.println(" ✗ Could not find: " + resourcePath);
            System.out.println("   Tried classpath, then: " + f1.getAbsolutePath() + " and " + f2.getAbsolutePath());
        }
        BufferedImage img = new BufferedImage(320, 180, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(LightModeToggle.getBackgroundColor());
        g.fillRect(0,0,320,180);
        g.setColor(LightModeToggle.getTextColor());
        g.drawString("Image missing: " + resourcePath, 10, 20);
        g.dispose();
        return new ImageIcon(img);
    }

    public static ArrayList<String> loadGamesFromFile(String filename) {
        ArrayList<String> games = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Library.class.getResourceAsStream(filename)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    games.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading game list from " + filename + ": " + e.getMessage());
        }
        return games;
    }

    public static ArrayList<String> loadGameDescriptionsFromFile(String filename) {
        ArrayList<String> descriptions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Library.class.getResourceAsStream(filename)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    descriptions.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading game descriptions from " + filename + ": " + e.getMessage());
        }
        return descriptions;
    }
}
