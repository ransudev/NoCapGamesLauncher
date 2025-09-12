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
}
