package com.mycompany.nocapgameslauncher.gui.components;

import com.mycompany.nocapgameslauncher.gui.mainFrame;
import com.mycompany.nocapgameslauncher.gui.utilities.LightModeToggle;
import com.mycompany.nocapgameslauncher.gui.utilities.ThemeButton;
import com.mycompany.nocapgameslauncher.gui.utilities.ThemeManager;
import com.mycompany.nocapgameslauncher.gui.utilities.ThemePanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.File;

public class HeaderCreator {

    private static final boolean DEBUG = true;

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
        toggleButton.setFont(new Font("Arial", Font.BOLD, 24));
        toggleButton.setBorder(new EmptyBorder(0, 0, 0, 15));
        toggleButton.setFocusPainted(false);
        toggleButton.setContentAreaFilled(false);
        toggleButton.setForeground(Color.WHITE);
        toggleButton.setManageForeground(false); // Disable theme management for foreground
        toggleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggleButton.addActionListener(e -> {
            sidebarPanel.setVisible(!sidebarPanel.isVisible());
            frame.revalidate();
            frame.repaint();
        });
        headerLinks.add(toggleButton);

        ThemeButton homeButton = createHeaderLink("Home");
        homeButton.setForeground(Color.WHITE);
        homeButton.setManageForeground(false); // Disable theme management for foreground
        homeButton.addActionListener(e -> {
            frame.showCard("LIBRARY");
        });
        ThemeButton storeLink = createHeaderLink("Store");
        storeLink.setForeground(Color.WHITE);
        storeLink.setManageForeground(false); // Disable theme management for foreground
        storeLink.addActionListener(e -> {
            frame.showCard("STORE");
        });
        ThemeButton friendsLink = createHeaderLink("Friends");
        friendsLink.setForeground(Color.WHITE);
        friendsLink.setManageForeground(false); // Disable theme management for foreground
        friendsLink.addActionListener(e -> {
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
        themeToggleButton.addActionListener(e -> {
            LightModeToggle.toggle();
            ThemeManager.updateTheme();
        });
        profilePanel.add(themeToggleButton);
        
        ThemeButton profileIcon = createHeaderLink("👤");
        profileIcon.setForeground(Color.WHITE);
        profileIcon.setManageForeground(false); // Disable theme management for foreground
        profileIcon.setFont(new Font("Arial", Font.PLAIN, 24));
        profileIcon.addActionListener(e -> {
            JPopupMenu profileMenu = new JPopupMenu();
            profileMenu.setBackground(LightModeToggle.getComponentColor());

            JMenuItem changeAccountItem = new JMenuItem("Change Account");
            changeAccountItem.setForeground(LightModeToggle.getTextColor());
            changeAccountItem.setBackground(LightModeToggle.getComponentColor());
            changeAccountItem.addActionListener(actionEvent -> {
                JOptionPane.showMessageDialog(frame, "Change Account clicked!");
            });
            profileMenu.add(changeAccountItem);

            JMenuItem loginSignoutItem = new JMenuItem("Login/Signout");
            loginSignoutItem.setForeground(LightModeToggle.getTextColor());
            loginSignoutItem.setBackground(LightModeToggle.getComponentColor());
            loginSignoutItem.addActionListener(actionEvent -> {
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

    private static ImageIcon loadIcon(String resourcePath) {
        if (DEBUG) System.out.println("loadIcon called with: " + resourcePath);
        URL url = HeaderCreator.class.getResource(resourcePath);
        if (url != null) {
            if (DEBUG) System.out.println(" → Loaded from classpath: " + url);
            return new ImageIcon(url);
        }
        String rp = resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
        File f1 = new File("src/main/resources/" + rp);
        File f2 = new File("src/" + rp);
        File pick = f1.exists() ? f1 : (f2.exists() ? f2 : null);
        if (pick != null) {
            if (DEBUG) System.out.println(" → Loaded from filesystem: " + pick.getAbsolutePath());
            return new ImageIcon(pick.getAbsolutePath());
        }
        if (DEBUG) {
            System.out.println(" ✗ Could not find: " + resourcePath);
            System.out.println("   Tried classpath, then: " + f1.getAbsolutePath() + " and " + f2.getAbsolutePath());
        }
        BufferedImage img = new BufferedImage(320, 180, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(LightModeToggle.getBackgroundColor());
        g.fillRect(0,0,320,180);
        g.setColor(LightModeToggle.getTextColor());
        g.drawString("Image missing: " + resourcePath, 10, 20);
        g.dispose();
        return new ImageIcon(img);
    }
}