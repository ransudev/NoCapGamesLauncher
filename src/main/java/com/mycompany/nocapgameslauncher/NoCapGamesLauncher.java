
package com.mycompany.nocapgameslauncher;

import com.mycompany.nocapgameslauncher.gui.mainFrame;
import com.mycompany.nocapgameslauncher.gui.panels.LoginForm;
import javax.swing.*;

public class NoCapGamesLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create main frame but keep it hidden until login succeeds
            mainFrame frame = new mainFrame();
            frame.setVisible(false);

            // Show login dialog
            JDialog loginDialog = new JDialog(frame, "No Cap Games - Login", true);
            loginDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            loginDialog.setUndecorated(false); // Keep decorations for easy window management
            loginDialog.getContentPane().add(new LoginForm(frame));
            loginDialog.setMinimumSize(new java.awt.Dimension(500, 600));
            loginDialog.pack();
            loginDialog.setLocationRelativeTo(null);
            loginDialog.setVisible(true);
            // If dialog disposed and frame still hidden, exit
            if (!frame.isVisible()) {
                System.exit(0);
            }
        });
    }
}
