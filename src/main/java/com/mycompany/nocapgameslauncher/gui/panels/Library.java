package com.mycompany.nocapgameslauncher.gui.panels;

import com.mycompany.nocapgameslauncher.gui.mainFrame;
import com.mycompany.nocapgameslauncher.gui.components.GameCardCreator;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Library extends JPanel {
    private static final boolean DEBUG = true;

    private mainFrame frame;
    private JPanel cardsPanel;
    private ArrayList<JPanel> gameCardsList;

    public Library(mainFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());
        setBackground(new Color(0x121212));
        createContentView();
    }

    private void createContentView() {
        try {
            setBorder(new EmptyBorder(20, 20, 20, 20));
            JLabel titleLabel = new JLabel("My Library");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
            titleLabel.setForeground(new Color(0xef4444));
            titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
            add(titleLabel, BorderLayout.NORTH);

            cardsPanel = new JPanel(new GridLayout(0, 4, 20, 20));
            cardsPanel.setBackground(new Color(0x121212));
            cardsPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

            gameCardsList = new ArrayList<>();
            
            ArrayList<String> gameTitles = loadGamesFromFile("/library_games.txt");

            if (gameTitles.isEmpty()) {
                JLabel noGamesLabel = new JLabel("No games available.");
                noGamesLabel.setForeground(Color.WHITE);
                noGamesLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                cardsPanel.add(noGamesLabel);
            } else {
                for (String title : gameTitles) {
                    // For now, use a generic icon. In a real app, you'd map title to icon.
                    ImageIcon gameIcon = GameCardCreator.loadIcon("/Resources/default_game_icon.jpg"); 
                    gameCardsList.add(GameCardCreator.createGameCard(title, "Description for " + title, gameIcon));
                }
            }

            for (JPanel card : gameCardsList) {
                cardsPanel.add(card);
            }

            JPanel cardsWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
            cardsWrapper.setBackground(new Color(0x121212));
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

    private ArrayList<String> loadGamesFromFile(String filename) {
        ArrayList<String> games = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename)))) {
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
