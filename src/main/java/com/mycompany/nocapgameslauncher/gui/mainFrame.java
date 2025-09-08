
package com.mycompany.nocapgameslauncher.gui;

import com.mycompany.nocapgameslauncher.gui.components.sidebarCreator;
import com.mycompany.nocapgameslauncher.gui.components.HeaderCreator;
import com.mycompany.nocapgameslauncher.gui.panels.*;
import javax.swing.*;
import java.awt.*;

public class mainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel sidebarPanel;

    public mainFrame() {
        initializeFrame();
        setupUI();
    }

    private void initializeFrame() {
        setTitle("No Cap Games Launcher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width * 3/4, screenSize.height * 3/4);
        setLocationRelativeTo(null);
    }

    private void setupUI() {
        sidebarPanel = sidebarCreator.createNavigationSidebar(250, this::showCard);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Create and add panels
        mainPanel.add(new Library(this), "LIBRARY");
        mainPanel.add(new Store(this), "STORE");
        mainPanel.add(new Friends(this), "FRIENDS");
        mainPanel.add(new Profile(this), "PROFILE");

        getContentPane().add(HeaderCreator.createHeader(this, sidebarPanel), BorderLayout.NORTH);
        getContentPane().add(sidebarPanel, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        
        showCard("LIBRARY");
    }

    public void showCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }
}
