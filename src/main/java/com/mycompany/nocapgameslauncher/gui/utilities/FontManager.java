package com.mycompany.nocapgameslauncher.gui.utilities;

import javax.swing.*;
import java.awt.*;

public class FontManager {
    public static void setFont(JButton button, int style, int size) {
        button.setFont(new Font("Arial", style, size));
    }
    public static void setFont(JLabel label, int style, int size) {
        label.setFont(new Font("Arial", style, size));
    }
    public static void setFont(JTextArea textArea, int style, int size) {
        textArea.setFont(new Font("Arial", style, size));
    }
    public static void fixIcon(JComponent component, int width, int height) {
        Dimension buttonSize = new Dimension(width, height);
        component.setPreferredSize(buttonSize);
        component.setMaximumSize(buttonSize);
        component.setMinimumSize(buttonSize);
        
        if (component instanceof AbstractButton button) {
            button.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
            button.setMargin(new Insets(0, 0, 0, 0));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setContentAreaFilled(false);
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setVerticalAlignment(SwingConstants.CENTER);
        }
    }
}
