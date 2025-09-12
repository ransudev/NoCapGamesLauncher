package com.mycompany.nocapgameslauncher.gui.components;

import com.mycompany.nocapgameslauncher.gui.mainFrame;
import com.mycompany.nocapgameslauncher.gui.utilities.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HeaderCreator {
    public static JPanel createHeader(mainFrame frame, JPanel sidebarPanel) {
        ThemePanel headerPanel = new ThemePanel(new BorderLayout()) {
            @Override
            public void updateTheme() {
                super.updateTheme();
                setBackground(LightModeToggle.getAccentColor());
            }
        };
        headerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        ThemePanel headerLinks = new ThemePanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        headerLinks.setOpaque(false);
        
        ThemeButton toggleButton = new ThemeButton("=");
        FontManager.setFont(toggleButton, Font.BOLD, 24);
        toggleButton.setBorder(new EmptyBorder(0, 0, 0, 15));
        toggleButton.setFocusPainted(false);
        toggleButton.setContentAreaFilled(false);
        toggleButton.setForeground(Color.WHITE);
        toggleButton.setManageForeground(false); // Disable theme management for foreground
        toggleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggleButton.addActionListener(_ -> {
            sidebarPanel.setVisible(!sidebarPanel.isVisible());
            frame.revalidate();
            frame.repaint();
        });
        headerLinks.add(toggleButton);

        ThemeButton homeButton = createHeaderLink("Home");
        homeButton.setForeground(Color.WHITE);
        homeButton.setManageForeground(false); // Disable theme management for foreground
        homeButton.addActionListener(_ -> {
            frame.showCard("LIBRARY");
        });
        ThemeButton storeLink = createHeaderLink("Store");
        storeLink.setForeground(Color.WHITE);
        storeLink.setManageForeground(false); // Disable theme management for foreground
        storeLink.addActionListener(_ -> {
            frame.showCard("STORE");
        });
        ThemeButton friendsLink = createHeaderLink("Friends");
        friendsLink.setForeground(Color.WHITE);
        friendsLink.setManageForeground(false); // Disable theme management for foreground
        friendsLink.addActionListener(_ -> {
            frame.showCard("FRIENDS");
        });
        headerLinks.add(homeButton);
        headerLinks.add(storeLink);
        headerLinks.add(friendsLink);
        headerPanel.add(headerLinks, BorderLayout.WEST);

        JTextField searchBar = new JTextField("Search games...") {
            @Override
            public void updateUI() {
                super.updateUI();
                setForeground(LightModeToggle.getTextColor());
                setBackground(LightModeToggle.getComponentColor());
                setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(LightModeToggle.getComponentColor().brighter()),
                    new EmptyBorder(8, 15, 8, 15)
                ));
            }
        };
        ThemeManager.addComponent(searchBar::updateUI);
        searchBar.setPreferredSize(new Dimension(400, 30));
        searchBar.setToolTipText("Search games...");
        searchBar.putClientProperty("JComponent.roundedCorners", true);
        ThemePanel searchPanel = new ThemePanel(new GridBagLayout());
        searchPanel.setOpaque(false);
        searchPanel.add(searchBar);
        headerPanel.add(searchPanel, BorderLayout.CENTER);

        ThemePanel profilePanel = new ThemePanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        profilePanel.setOpaque(false);
        
        ThemeButton themeToggleButton = createHeaderLink("Toggle Theme");
        themeToggleButton.setForeground(Color.WHITE);
        themeToggleButton.setManageForeground(false); // Disable theme management for foreground
        themeToggleButton.addActionListener(_ -> {
            LightModeToggle.toggle();
            ThemeManager.updateTheme();
        });
        profilePanel.add(themeToggleButton);
        
        ThemeButton profileIcon = createHeaderLink("👤");
        profileIcon.setForeground(Color.WHITE);
        profileIcon.setManageForeground(false); // Disable theme management for foreground
        FontManager.setFont(profileIcon, Font.BOLD, 36);
        profileIcon.addActionListener(_ -> {
            JPopupMenu profileMenu = new JPopupMenu();
            profileMenu.setBackground(LightModeToggle.getComponentColor());

            JMenuItem changeAccountItem = new JMenuItem("Change Account");
            changeAccountItem.setForeground(LightModeToggle.getTextColor());
            changeAccountItem.setBackground(LightModeToggle.getComponentColor());
            changeAccountItem.addActionListener(_ -> {
                JOptionPane.showMessageDialog(frame, "Change Account clicked!");
            });
            profileMenu.add(changeAccountItem);

            JMenuItem loginSignoutItem = new JMenuItem("Login/Signout");
            loginSignoutItem.setForeground(LightModeToggle.getTextColor());
            loginSignoutItem.setBackground(LightModeToggle.getComponentColor());
            loginSignoutItem.addActionListener(_ -> {
                JOptionPane.showMessageDialog(frame, "Login/Signout clicked!");
            });
            profileMenu.add(loginSignoutItem);

            profileMenu.show(profileIcon, 0, profileIcon.getHeight());
        });
        profilePanel.add(profileIcon);
        
        headerPanel.add(profilePanel, BorderLayout.EAST);
        return headerPanel;
    }
    
    private static ThemeButton createHeaderLink(String text) {
        ThemeButton link = new ThemeButton(text);
        link.setBorderPainted(false);
        link.setFocusPainted(false);
        link.setContentAreaFilled(false);
        return link;
    }
}