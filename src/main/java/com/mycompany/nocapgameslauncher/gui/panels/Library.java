package com.mycompany.nocapgameslauncher.gui.panels;

import com.mycompany.nocapgameslauncher.gui.mainFrame;
import com.mycompany.nocapgameslauncher.gui.components.GameCardCreator;
import com.mycompany.nocapgameslauncher.gui.resourceManager.resourceLoader;
import com.mycompany.nocapgameslauncher.gui.utilities.LightModeToggle;
import com.mycompany.nocapgameslauncher.gui.utilities.ThemePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.util.*;

import com.mycompany.nocapgameslauncher.gui.utilities.*;

public class Library extends ThemePanel {
    private mainFrame frame;
    private ThemePanel cardsPanel;
    private ArrayList<JPanel> gameCardsList;
    private JLabel titleLabel;

    public Library(mainFrame frame) {
        super(new BorderLayout());
        this.frame = frame;
        createContentView();
        updateTheme();
    }

    private void createContentView() {
        try {
            setBorder(new EmptyBorder(20, 20, 20, 20));
            titleLabel = new JLabel("My Library");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
            titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
            add(titleLabel, BorderLayout.NORTH);

            cardsPanel = new ThemePanel(new GridLayout(0, 4, 20, 20));
            cardsPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

            gameCardsList = new ArrayList<>();
            
            ArrayList<String> gameTitles = resourceLoader.loadGamesFromFile("/library_games.txt");
            ArrayList<String> gameDescriptions = resourceLoader.loadGameDescriptionsFromFile("/gamedesc.txt");

            if (gameTitles.isEmpty()) {
                JLabel noGamesLabel = new JLabel("No games available.");
                noGamesLabel.setForeground(Color.WHITE);
                FontManager.setFont(noGamesLabel, Font.PLAIN, 20);
                cardsPanel.add(noGamesLabel);
            } else {
                for (int i = 0; i < gameTitles.size(); i++) {
                    String title = gameTitles.get(i);
                    String description = (i < gameDescriptions.size()) ? gameDescriptions.get(i) : "No description available.";
                    // For now, use a generic icon. In a real app, you'd map title to icon.
                    ImageIcon gameIcon = GameCardCreator.loadIcon("/Resources/default_game_icon.jpg"); 
                    gameCardsList.add(GameCardCreator.createGameCard(title, description, gameIcon));
                }
            }

            for (JPanel card : gameCardsList) {
                cardsPanel.add(card);
            }

            ThemePanel cardsWrapper = new ThemePanel(new FlowLayout(FlowLayout.LEFT));
            cardsWrapper.add(cardsPanel);

            add(cardsWrapper, BorderLayout.CENTER);
        } catch (Throwable t) {
            t.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Failed to build content: " + t.getClass().getSimpleName() + " - " + t.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void updateTheme() {
        super.updateTheme();
        if (titleLabel != null) {
            titleLabel.setForeground(LightModeToggle.getTextColor());
        }
        if (cardsPanel != null) {
            cardsPanel.updateTheme();
        }
    }
}
