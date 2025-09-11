
package com.mycompany.nocapgameslauncher.gui.components;

import com.mycompany.nocapgameslauncher.gui.mainFrame;
import javax.swing.*;
import java.awt.*;

public class ButtonCreator extends JButton {
    public ButtonCreator(mainFrame frame, String text) {
        this(frame, text, "LANDING"); // Default card
    }

    public ButtonCreator(mainFrame frame, String text, String cardName) {
        setPreferredSize(new Dimension(200, 30));
        setBackground(Color.WHITE);
        setFocusable(false);
        setText(text);
        
        addActionListener(e -> {
            frame.showCard(cardName);
        });
    }

    public static void headerButtons(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0x202020));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }
}
