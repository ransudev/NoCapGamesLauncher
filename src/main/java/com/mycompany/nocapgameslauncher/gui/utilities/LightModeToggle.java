package com.mycompany.nocapgameslauncher.gui.utilities;

import java.awt.Color;

public class LightModeToggle {

    private static boolean isLightMode = false;

    // Dark Mode Colors
    public static final Color DARK_BACKGROUND = new Color(28, 28, 28); 
    public static final Color DARK_COMPONENT = new Color(40, 40, 40); 
    public static final Color DARK_SIDEBAR = new Color(20, 20, 20); 
    public static final Color DARK_TEXT = new Color(235, 235, 235); 
    public static final Color ACCENT_COLOR = new Color(209, 54, 57); 
    // Light Mode Colors
    public static final Color LIGHT_BACKGROUND = new Color(255, 255, 255); 
    public static final Color LIGHT_COMPONENT = new Color(240, 240, 240); 
    public static final Color LIGHT_TEXT = new Color(0, 0, 0); 

    //Outside Colors
    public static final Color GREEN = new Color(67, 160, 71);

    public static void toggle() {
        isLightMode = !isLightMode;
    }

    public static boolean isLightMode() {
        return isLightMode;
    }

    public static Color getBackgroundColor() {
        return isLightMode ? LIGHT_BACKGROUND : DARK_BACKGROUND;
    }

    public static Color getComponentColor() {
        return isLightMode ? LIGHT_COMPONENT : DARK_COMPONENT;
    }

    public static Color getTextColor() {
        return isLightMode ? LIGHT_TEXT : DARK_TEXT;
    }

    public static Color getAccentColor() {
        return ACCENT_COLOR;
    }

    public static Color getSidebarColor() {
        return isLightMode ? new Color(230, 230, 230) : DARK_SIDEBAR;
    }
}