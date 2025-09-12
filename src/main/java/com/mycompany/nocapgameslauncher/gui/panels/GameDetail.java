package com.mycompany.nocapgameslauncher.gui.panels;

import com.mycompany.nocapgameslauncher.gui.mainFrame;
import com.mycompany.nocapgameslauncher.gui.utilities.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.io.*;
import javax.swing.border.EmptyBorder;

public class GameDetail extends ThemePanel {

    private mainFrame frame;
    private JLabel gameTitleLabel;
    private JTextArea gameDescriptionArea;
    private ThemeButton playButton;

    private Map<String, String> gameDescriptions;

    public GameDetail(mainFrame frame) {
        super(new BorderLayout(20, 20));
        this.frame = frame;
        setBorder(new EmptyBorder(20, 20, 20, 20));

        gameTitleLabel = new JLabel("Game Title");
        FontManager.setFont(gameTitleLabel, Font.BOLD, 36);
        gameTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(gameTitleLabel, BorderLayout.NORTH);

        gameDescriptionArea = new JTextArea("Game Description");
        FontManager.setFont(gameDescriptionArea, Font.PLAIN, 18);
        gameDescriptionArea.setLineWrap(true);
        gameDescriptionArea.setWrapStyleWord(true);
        gameDescriptionArea.setEditable(false);
        gameDescriptionArea.setOpaque(true); // Make it opaque
        JScrollPane scrollPane = new JScrollPane(gameDescriptionArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);

        playButton = new ThemeButton("Play Game", false, false, null, true);
        FontManager.setFont(playButton, Font.BOLD, 24);
        playButton.setPreferredSize(new Dimension(200, 50));
        playButton.setBackground(LightModeToggle.GREEN); // Steam Green
        playButton.setForeground(Color.WHITE); // White text for contrast
        playButton.setOpaque(true); // Ensure background is painted
        playButton.setBorderPainted(false); // Remove border
        playButton.addActionListener(_ -> {
            JOptionPane.showMessageDialog(this, "Launching " + gameTitleLabel.getText() + "...");
        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);
        buttonPanel.add(playButton);
        add(buttonPanel, BorderLayout.SOUTH);

        gameDescriptions = loadGameDescriptions();
    }

    public void setGame(String gameTitle) {
        gameTitleLabel.setText(gameTitle);
        String description = gameDescriptions.getOrDefault(gameTitle, "No description available for this game.");
        gameDescriptionArea.setText(description);
    }

    private Map<String, String> loadGameDescriptions() {
        Map<String, String> descriptions = new HashMap<>();
        ArrayList<String> titles = new ArrayList<>();
        ArrayList<String> descs = new ArrayList<>();

        try (BufferedReader titleReader = new BufferedReader(new InputStreamReader(GameDetail.class.getResourceAsStream("/library_games.txt")))) {
            String line;
            while ((line = titleReader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    titles.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading game titles: " + e.getMessage());
        }

        try (BufferedReader descReader = new BufferedReader(new InputStreamReader(GameDetail.class.getResourceAsStream("/gamedesc.txt")))) {
            String line;
            while ((line = descReader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    descs.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading game descriptions: " + e.getMessage());
        }

        for (int i = 0; i < titles.size() && i < descs.size(); i++) {
            descriptions.put(titles.get(i), descs.get(i));
        }
        return descriptions;
    }

    @Override
    public void updateTheme() {
        super.updateTheme();
        gameTitleLabel.setForeground(LightModeToggle.getTextColor());
        gameDescriptionArea.setForeground(LightModeToggle.getTextColor());
        gameDescriptionArea.setBackground(LightModeToggle.getBackgroundColor());
    }
}
