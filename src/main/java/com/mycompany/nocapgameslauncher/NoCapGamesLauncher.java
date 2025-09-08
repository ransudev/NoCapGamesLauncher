
package com.mycompany.nocapgameslauncher;

import com.mycompany.nocapgameslauncher.gui.mainFrame;
import javax.swing.SwingUtilities;

public class NoCapGamesLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            mainFrame frame = new mainFrame();
            frame.setVisible(true);
        });
    }
}
