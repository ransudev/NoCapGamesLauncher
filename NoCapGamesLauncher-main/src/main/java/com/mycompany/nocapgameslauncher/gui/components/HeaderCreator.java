package com.mycompany.nocapgameslauncher.gui.components;

import com.mycompany.nocapgameslauncher.gui.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.File;

public class HeaderCreator {

    private static final boolean DEBUG = true;

    public static JPanel createHeader(mainFrame frame, JPanel sidebarPanel) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setName("headerPanel");
        headerPanel.setBackground(ThemeManager.getHeaderBackground());
        headerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JPanel headerLinks = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        headerLinks.setOpaque(false);
        
        JButton toggleButton = new JButton("=");
        ButtonCreator.headerButtons(toggleButton);
        toggleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        toggleButton.addActionListener(_ -> {
            sidebarPanel.setVisible(!sidebarPanel.isVisible());
            frame.revalidate();
            frame.repaint();
        });
        headerLinks.add(toggleButton);

        JButton homeButton = createHeaderLink("Home");
        homeButton.addActionListener(_ -> {
            frame.showCard("LIBRARY");
        });
        JButton storeLink = createHeaderLink("Store");
        storeLink.addActionListener(_ -> {
            frame.showCard("STORE");
        });
        JButton friendsLink = createHeaderLink("Friends");
        friendsLink.addActionListener(_ -> {
            frame.showCard("FRIENDS");
        });
        headerLinks.add(homeButton);
        headerLinks.add(storeLink);
        headerLinks.add(friendsLink);
        headerPanel.add(headerLinks, BorderLayout.WEST);

        JTextField searchBar = new JTextField("Search games...");
        searchBar.setForeground(ThemeManager.getSearchText());
        searchBar.setBackground(ThemeManager.getSearchBackground());
        searchBar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(ThemeManager.getSearchBorder()),
            new EmptyBorder(8, 15, 8, 15)
        ));
        searchBar.setPreferredSize(new Dimension(400, 30));
        searchBar.setToolTipText("Search games...");
        searchBar.putClientProperty("JComponent.roundedCorners", true);
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setOpaque(false);
        searchPanel.add(searchBar);
        headerPanel.add(searchPanel, BorderLayout.CENTER);

        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        profilePanel.setOpaque(false);
        
        JButton profileIcon = new JButton("👤"); 
        ButtonCreator.headerButtons(profileIcon);
        profileIcon.addActionListener(_ -> {
            JPopupMenu profileMenu = new JPopupMenu();
            profileMenu.setBackground(new Color(0x333333));

            JMenuItem changeAccountItem = new JMenuItem("Change Account");
            changeAccountItem.setForeground(Color.WHITE);
            changeAccountItem.setBackground(new Color(0x333333));
            changeAccountItem.addActionListener(_ -> {
                JOptionPane.showMessageDialog(frame, "Change Account clicked!");
            });
            profileMenu.add(changeAccountItem);

            JMenuItem loginSignoutItem = new JMenuItem("Login/Signout");
            loginSignoutItem.setForeground(Color.WHITE);
            loginSignoutItem.setBackground(new Color(0x333333));
            loginSignoutItem.addActionListener(_ -> {
                JOptionPane.showMessageDialog(frame, "Login/Signout clicked!");
            });
            profileMenu.add(loginSignoutItem);
            profileMenu.show(profileIcon, 0, profileIcon.getHeight());
        });
        JButton themeToggle = new JButton(ThemeManager.isDarkMode() ? "☀️" : "🌙");
        ButtonCreator.headerButtons(themeToggle);
        themeToggle.addActionListener(_ -> {
            ThemeManager.toggleTheme();
            themeToggle.setText(ThemeManager.isDarkMode() ? "☀️" : "🌙");
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(themeToggle);
            if (topFrame != null) {
                ThemeManager.applyTheme(topFrame);
                topFrame.revalidate();
                topFrame.repaint();
            }
        });
        
        profilePanel.add(themeToggle);
        profilePanel.add(profileIcon);
        headerPanel.add(profilePanel, BorderLayout.EAST);
        return headerPanel;
    }
    
    private static JButton createHeaderLink(String text) {
        JButton link = new JButton(text);
        ButtonCreator.headerButtons(link);
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
        g.setColor(new Color(0x2a2a2a));
        g.fillRect(0,0,320,180);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Image missing: " + resourcePath, 10, 20);
        g.dispose();
        return new ImageIcon(img);
    }
}
