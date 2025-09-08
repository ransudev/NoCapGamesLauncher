
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
}
