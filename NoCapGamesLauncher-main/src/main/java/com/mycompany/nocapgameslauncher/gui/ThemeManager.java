package com.mycompany.nocapgameslauncher.gui;

import javax.swing.*;
import java.awt.*;

public class ThemeManager {
    private static boolean isDarkMode = true;
    
    // Original dark theme colors
    private static final Color DARK_HEADER_BG = new Color(0x202020);
    private static final Color DARK_MAIN_BG = new Color(0x1E1E1E);  // Main background color
    private static final Color DARK_SIDEBAR_BG = new Color(0x252525); // Slightly lighter than main
    private static final Color DARK_SEARCH_BG = new Color(0x333333);
    private static final Color DARK_SEARCH_BORDER = new Color(0x444444);
    private static final Color DARK_SEARCH_TEXT = new Color(0x888888);
    private static final Color DARK_TEXT = new Color(0xFFFFFF);
    private static final Color DARK_TITLE = new Color(0xef4444);
    
    // Light theme colors
    private static final Color LIGHT_HEADER_BG = new Color(240, 240, 240);
    private static final Color LIGHT_MAIN_BG = new Color(250, 250, 250);  // Main background color
    private static final Color LIGHT_SIDEBAR_BG = new Color(245, 245, 245); // Slightly darker than main
    private static final Color LIGHT_SEARCH_BG = new Color(250, 250, 250);
    private static final Color LIGHT_SEARCH_BORDER = new Color(200, 200, 200);
    private static final Color LIGHT_SEARCH_TEXT = new Color(100, 100, 100);
    private static final Color LIGHT_TEXT = new Color(40, 40, 40);
    private static final Color LIGHT_TITLE = new Color(0xdc2626);
    
    public static void toggleTheme() {
        isDarkMode = !isDarkMode;
    }
    
    public static boolean isDarkMode() {
        return isDarkMode;
    }
    
    public static Color getHeaderBackground() {
        return isDarkMode ? DARK_HEADER_BG : LIGHT_HEADER_BG;
    }
    
    public static Color getMainBackground() {
        return isDarkMode ? DARK_MAIN_BG : LIGHT_MAIN_BG;
    }
    
    public static Color getSidebarBackground() {
        return isDarkMode ? DARK_SIDEBAR_BG : LIGHT_SIDEBAR_BG;
    }
    
    public static Color getSearchBackground() {
        return isDarkMode ? DARK_SEARCH_BG : LIGHT_SEARCH_BG;
    }
    
    public static Color getSearchBorder() {
        return isDarkMode ? DARK_SEARCH_BORDER : LIGHT_SEARCH_BORDER;
    }
    
    public static Color getSearchText() {
        return isDarkMode ? DARK_SEARCH_TEXT : LIGHT_SEARCH_TEXT;
    }
    
    public static Color getTextColor() {
        return isDarkMode ? DARK_TEXT : LIGHT_TEXT;
    }
    
    public static Color getTitleColor() {
        return isDarkMode ? DARK_TITLE : LIGHT_TITLE;
    }
    
    public static void applyTheme(Component component) {
        if (component instanceof JComponent) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(component);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            // Set default background for all components first
            if (component instanceof JPanel || component instanceof JLabel || 
                component instanceof JButton || component instanceof JTextArea) {
                component.setBackground(getMainBackground());
                component.setForeground(getTextColor());
            }
            
            // Apply specific theming
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                if (panel.getName() != null) {
                    if (panel.getName().equals("headerPanel")) {
                        panel.setBackground(getHeaderBackground());
                    } else if (panel.getName().equals("sidebarPanel") || 
                              panel.getName().equals("ownedGamesPanel")) {
                        panel.setBackground(getSidebarBackground());
                    } else if (panel.getName().equals("libraryPanel") || 
                             panel.getName().startsWith("gameCard")) {
                        panel.setBackground(getMainBackground());
                    }
                }
            } else if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textField.setBackground(getSearchBackground());
                textField.setForeground(getSearchText());
                textField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(getSearchBorder()),
                    new javax.swing.border.EmptyBorder(8, 15, 8, 15)
                ));
            } else if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getFont() != null && label.getFont().getSize() >= 20) {
                    label.setForeground(getTitleColor());
                } else {
                    label.setForeground(getTextColor());
                }
            } else if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setForeground(getTextColor());
                if (!button.isOpaque()) {
                    button.setBackground(null);
                }
            }
        }
        
        // Apply to children
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                applyTheme(child);
            }
        }
    }
}
