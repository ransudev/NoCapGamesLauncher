package com.mycompany.nocapgameslauncher.gui.components;

import com.mycompany.nocapgameslauncher.gui.mainFrame;
import com.mycompany.nocapgameslauncher.gui.utilities.*;

import java.awt.*;
import java.awt.event.*;
import java.util.function.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class sidebarCreator {
            
    public static JPanel createNavigationSidebar(int width, mainFrame frame, Consumer<String> onItemClick) {
        ThemePanel panel = new ThemePanel() {
            @Override
            public void updateTheme() {
                setBackground(LightModeToggle.getSidebarColor());
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(width, Integer.MAX_VALUE));
        panel.setMaximumSize(new Dimension(width, Integer.MAX_VALUE));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 1));

        addItem(panel, "Owned Games", "📦", onItemClick);
        
        // Add owned games list
        ThemePanel ownedGamesPanel = new ThemePanel() {
            @Override
            public void updateTheme() {
                setBackground(LightModeToggle.getSidebarColor());
                setForeground(LightModeToggle.getTextColor());
            }
        };
        ownedGamesPanel.setLayout(new BoxLayout(ownedGamesPanel, BoxLayout.Y_AXIS));
        ownedGamesPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0)); // Indent

        ArrayList<String> gameTitles = loadGamesFromFile("/library_games.txt");

        if (gameTitles.isEmpty()) {
            JLabel noGamesLabel = new JLabel("No games available.");
            noGamesLabel.setForeground(LightModeToggle.getTextColor());
            noGamesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            ownedGamesPanel.add(noGamesLabel);
        } else {
            for (String title : gameTitles) {
                addGameItem(ownedGamesPanel, title, frame, onItemClick);
            }
        }

        panel.add(ownedGamesPanel);
        
        return panel;
    }
    
    private static void addItem(JPanel panel, String text, String icon, Consumer<String> onItemClick) {
        ThemeButton button = new ThemeButton(text + " " + icon);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setMinimumSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setHorizontalAlignment(JButton.LEFT);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(LightModeToggle.getComponentColor());
                button.setOpaque(true);
            }
            
            public void mouseExited(MouseEvent e) {
                button.setBackground(LightModeToggle.getComponentColor());
                button.setOpaque(false);
            }
        });
        
        button.addActionListener(e -> {
            onItemClick.accept(text.toUpperCase());
        });
        
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    private static void addGameItem(JPanel panel, String text, mainFrame frame, Consumer<String> onItemClick) {
        ThemeButton button = new ThemeButton(text, false, true, LightModeToggle.getTextColor()); // Set initial color to text color, manage foreground
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setPreferredSize(new Dimension(Integer.MAX_VALUE, 30));
        button.setMinimumSize(new Dimension(Integer.MAX_VALUE, 30));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        button.setHorizontalAlignment(JButton.LEFT);
        button.setBorder(BorderFactory.createEmptyBorder(2, 25, 2, 5)); // Further indent
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setFocusPainted(false);
        FontManager.setFont(button, Font.PLAIN, 12);
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setForeground(LightModeToggle.getAccentColor()); // Use accent color on hover
            }
            
            public void mouseExited(MouseEvent e) {
                button.setForeground(LightModeToggle.getTextColor()); // Match text color
            }
        });
        
        button.addActionListener(e -> {
            frame.showGameDetail(text);
            // In a more advanced setup, you might pass the game name to highlight it
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