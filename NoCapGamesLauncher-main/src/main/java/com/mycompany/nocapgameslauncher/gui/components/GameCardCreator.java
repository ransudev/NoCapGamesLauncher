package com.mycompany.nocapgameslauncher.gui.components;

import com.mycompany.nocapgameslauncher.gui.ThemeManager;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.io.File;

public class GameCardCreator {
    private static final boolean DEBUG = true;

    public static JPanel createGameCard(String title, String description, ImageIcon icon) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setPreferredSize(new Dimension(150, 225));
        card.setBackground(ThemeManager.getMainBackground().darker());
        card.setBorder(BorderFactory.createLineBorder(ThemeManager.getSearchBorder(), 1));
        card.setName("gameCard");

        // Create a panel for the image with padding
        JPanel imageContainer = new JPanel(new BorderLayout());
        imageContainer.setBackground(ThemeManager.getMainBackground().darker());
        
        if (icon != null) {
            Image scaled = icon.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaled));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageContainer.add(imageLabel, BorderLayout.CENTER);
        }
        
        card.add(imageContainer, BorderLayout.CENTER);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBackground(ThemeManager.getMainBackground().darker());
        textPanel.setBorder(new EmptyBorder(5, 10, 10, 10));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(ThemeManager.getTextColor());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        textPanel.add(titleLabel, BorderLayout.NORTH);

        if (description != null && !description.isEmpty()) {
            JTextArea descArea = new JTextArea(description);
            descArea.setWrapStyleWord(true);
            descArea.setLineWrap(true);
            descArea.setEditable(false);
            descArea.setBackground(ThemeManager.getMainBackground().darker());
            descArea.setForeground(ThemeManager.getTextColor().brighter());
            descArea.setFont(new Font("Arial", Font.PLAIN, 12));
            textPanel.add(descArea, BorderLayout.CENTER);
        }

        card.add(textPanel, BorderLayout.SOUTH);
        return card;
    }

    public static ImageIcon loadIcon(String resourcePath) {
        if (DEBUG) System.out.println("loadIcon called with: " + resourcePath);
        URL url = GameCardCreator.class.getResource(resourcePath);
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
        return null;
    }
}
