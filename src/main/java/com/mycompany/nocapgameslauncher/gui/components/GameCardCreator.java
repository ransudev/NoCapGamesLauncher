package com.mycompany.nocapgameslauncher.gui.components;

import com.mycompany.nocapgameslauncher.gui.utilities.LightModeToggle;
import com.mycompany.nocapgameslauncher.gui.utilities.ThemeManager;
import com.mycompany.nocapgameslauncher.gui.utilities.ThemePanel;
import javax.swing.*;
import java.awt.*;

public class GameCardCreator {
    public static JPanel createGameCard(String title, String description, ImageIcon icon) {
        ThemePanel card = new ThemePanel(new BorderLayout(10, 10));
        card.setPreferredSize(new Dimension(150, 225)); // Smaller fixed size for the card (2x3 aspect ratio)

        Image scaled = icon.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaled));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(imageLabel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(LightModeToggle.getTextColor());
        card.add(titleLabel, BorderLayout.NORTH);

        ThemeManager.addComponent(() -> {
            card.setBackground(LightModeToggle.getBackgroundColor());
            card.setBorder(BorderFactory.createLineBorder(LightModeToggle.getBackgroundColor().brighter()));
            titleLabel.setForeground(LightModeToggle.getTextColor());
        });
        card.updateTheme(); // Call updateTheme to set initial colors
        
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(LightModeToggle.getAccentColor());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(LightModeToggle.getComponentColor());
            }
        });

        return card;
    }
}
