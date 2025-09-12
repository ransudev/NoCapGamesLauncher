package com.mycompany.nocapgameslauncher.gui.components;

import com.mycompany.nocapgameslauncher.gui.utilities.LightModeToggle;
import com.mycompany.nocapgameslauncher.gui.utilities.ThemeManager;
import com.mycompany.nocapgameslauncher.gui.utilities.ThemePanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameCardCreator {

    private static final boolean DEBUG = true;

    public static JPanel createGameCard(String title, String description, ImageIcon icon) {
        ThemePanel card = new ThemePanel(new BorderLayout(10, 10));
        card.setPreferredSize(new Dimension(150, 225)); // Smaller fixed size for the card (2x3 aspect ratio)

        Image scaled = icon.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaled));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(imageLabel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(LightModeToggle.getTextColor()); // Set initial color
        card.add(titleLabel, BorderLayout.NORTH);

        ThemeManager.addComponent(new ThemeManager.Themeable() {
            @Override
            public void updateTheme() {
                card.setBackground(LightModeToggle.getBackgroundColor());
                card.setBorder(BorderFactory.createLineBorder(LightModeToggle.getBackgroundColor().brighter()));
                titleLabel.setForeground(LightModeToggle.getTextColor());
            }
        });
        card.updateTheme(); // Call updateTheme to set initial colors
        
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(LightModeToggle.getAccentColor());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(LightModeToggle.getComponentColor());
            }
        });

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
        BufferedImage img = new BufferedImage(320, 180, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(LightModeToggle.getBackgroundColor());
        g.fillRect(0,0,320,180);
        g.setColor(LightModeToggle.getTextColor());
        g.drawString("Image missing: " + resourcePath, 10, 20);
        g.dispose();
        return new ImageIcon(img);
    }
}
