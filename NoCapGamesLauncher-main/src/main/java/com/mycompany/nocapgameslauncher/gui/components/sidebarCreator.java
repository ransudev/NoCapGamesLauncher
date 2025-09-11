package com.mycompany.nocapgameslauncher.gui.components;

import com.mycompany.nocapgameslauncher.gui.ThemeManager;

import java.awt.*;
import java.awt.event.*;
import java.util.function.*;

import javax.swing.*;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import javax.swing.border.EmptyBorder;

public class sidebarCreator {           
    public static JPanel createNavigationSidebar(int width, Consumer<String> onItemClick) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(ThemeManager.getSidebarBackground());
        panel.setPreferredSize(new Dimension(width, Integer.MAX_VALUE));
        panel.setMaximumSize(new Dimension(width, Integer.MAX_VALUE));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));
        panel.setName("sidebarPanel");

        addItem(panel, "Owned Games", "", onItemClick);
        
        // Add owned games list
        JPanel ownedGamesPanel = new JPanel();
        ownedGamesPanel.setLayout(new BoxLayout(ownedGamesPanel, BoxLayout.Y_AXIS));
        ownedGamesPanel.setBackground(ThemeManager.getSidebarBackground());
        ownedGamesPanel.setBorder(new EmptyBorder(0, 15, 0, 0));
        ownedGamesPanel.setName("ownedGamesPanel");

        ArrayList<String> gameTitles = loadGamesFromFile("/library_games.txt");

        if (gameTitles.isEmpty()) {
            JLabel noGamesLabel = new JLabel("No games available.");
            noGamesLabel.setForeground(ThemeManager.getTextColor().darker());
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
        button.setForeground(ThemeManager.getTextColor());
        button.setFont(new Font("Arial", Font.BOLD, 14));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(ThemeManager.getSidebarBackground().brighter());
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
        button.setBorder(BorderFactory.createEmptyBorder(2, 25, 2, 5));
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setForeground(ThemeManager.getTextColor().darker());
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setForeground(ThemeManager.getTextColor());
                button.setOpaque(true);
                button.setBackground(ThemeManager.getSidebarBackground().brighter());
            }
            
            public void mouseExited(MouseEvent e) {
                button.setForeground(ThemeManager.getTextColor().darker());
                button.setOpaque(false);
                button.setBackground(null);
            }
        });
        
        button.addActionListener(e -> {
            onItemClick.accept("GAME_" + text.toUpperCase().replace(" ", "_"));
        });
        
        panel.add(button);
    }

    private static ArrayList<String> loadGamesFromFile(String filename) {
        ArrayList<String> games = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(sidebarCreator.class.getResourceAsStream(filename)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    games.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading game list: " + e.getMessage());
            e.printStackTrace();
        }
        return games;
    }
}