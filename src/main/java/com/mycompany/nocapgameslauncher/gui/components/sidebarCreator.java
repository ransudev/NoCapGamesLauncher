package com.mycompany.nocapgameslauncher.gui.components;

import java.awt.*;
import java.awt.event.*;
import java.util.function.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class sidebarCreator {
    private static Color DEFAULT_BG = new Color(0x202020);
    private static Color HIGHLIGHTED = new Color(0x333333);
            
    public static JPanel createNavigationSidebar(int width, Consumer<String> onItemClick) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(DEFAULT_BG);
        panel.setPreferredSize(new Dimension(width, Integer.MAX_VALUE));
        panel.setMaximumSize(new Dimension(width, Integer.MAX_VALUE));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));

        addItem(panel, "Owned Games", "📦", onItemClick);
        
        // Add owned games list
        JPanel ownedGamesPanel = new JPanel();
        ownedGamesPanel.setLayout(new BoxLayout(ownedGamesPanel, BoxLayout.Y_AXIS));
        ownedGamesPanel.setBackground(DEFAULT_BG);
        ownedGamesPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); // Indent

        ArrayList<String> gameTitles = loadGamesFromFile("/library_games.txt");

        if (gameTitles.isEmpty()) {
            JLabel noGamesLabel = new JLabel("No games available.");
            noGamesLabel.setForeground(new Color(0xA0A0A0));
            noGamesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            ownedGamesPanel.add(noGamesLabel);
        } else {
            for (String title : gameTitles) {
                addGameItem(ownedGamesPanel, title, onItemClick);
            }
        }

        panel.add(ownedGamesPanel);
        
        return panel;
    }
    
    private static void addItem(JPanel panel, String text, String icon, Consumer<String> onItemClick) {
        JButton button = new JButton(text + " " + icon);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setMinimumSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setHorizontalAlignment(JButton.LEFT);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(HIGHLIGHTED);
                button.setOpaque(true);
            }
            
            public void mouseExited(MouseEvent e) {
                button.setBackground(null);
                button.setOpaque(false);
            }
        });
        
        button.addActionListener(e -> {
            onItemClick.accept(text.toUpperCase());
        });
        
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    private static void addGameItem(JPanel panel, String text, Consumer<String> onItemClick) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setPreferredSize(new Dimension(Integer.MAX_VALUE, 30));
        button.setMinimumSize(new Dimension(Integer.MAX_VALUE, 30));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        button.setHorizontalAlignment(JButton.LEFT);
        button.setBorder(BorderFactory.createEmptyBorder(2, 25, 2, 5)); // Further indent
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setForeground(new Color(0xA0A0A0)); // Lighter color for game items
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Color.WHITE);
            }
            
            public void mouseExited(MouseEvent e) {
                button.setForeground(new Color(0xA0A0A0));
            }
        });
        
        button.addActionListener(e -> {
            onItemClick.accept("LIBRARY"); // Always go to library for game clicks
            // In a more advanced setup, you might pass the game name to highlight it
            JOptionPane.showMessageDialog(null, "Launching " + text + "...");
        });
        
        panel.add(button);
    }

    private static ArrayList<String> loadGamesFromFile(String filename) {
        ArrayList<String> games = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(sidebarCreator.class.getResourceAsStream(filename)))) {
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
}