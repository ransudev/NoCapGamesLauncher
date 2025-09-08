
package com.mycompany.nocapgameslauncher.gui.panels;

import com.mycompany.nocapgameslauncher.gui.mainFrame;
import javax.swing.*;
import java.awt.*;

public class Profile extends JPanel {

    public Profile(mainFrame frame) {
        setLayout(new GridLayout(0, 1));
        setBackground(new Color(0x202020));
        setBorder(BorderFactory.createLineBorder(new Color(0x444444)));

        JButton signInButton = createProfileButton("Sign In");
        signInButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Sign In clicked!");
        });

        JButton changeAccountButton = createProfileButton("Change Account");
        changeAccountButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Change Account clicked!");
        });

        add(signInButton);
        add(changeAccountButton);
    }

    private JButton createProfileButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0x333333));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setHorizontalAlignment(SwingConstants.LEFT);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0x444444));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0x333333));
            }
        });

        return button;
    }
}
