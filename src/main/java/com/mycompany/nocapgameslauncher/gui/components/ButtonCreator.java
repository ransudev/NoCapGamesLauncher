package com.mycompany.nocapgameslauncher.gui.components;

import com.mycompany.nocapgameslauncher.gui.mainFrame;
import com.mycompany.nocapgameslauncher.gui.utilities.ThemeButton;
import javax.swing.*;
import java.awt.*;

public class ButtonCreator extends ThemeButton {
    public ButtonCreator(mainFrame frame, String text) {
        this(frame, text, "LANDING"); // Default card
    }

    public ButtonCreator(mainFrame frame, String text, String cardName) {
        super(text);
        setPreferredSize(new Dimension(200, 30));
        setFocusable(false);
        
        addActionListener(e -> {
            frame.showCard(cardName);
        });
    }
}