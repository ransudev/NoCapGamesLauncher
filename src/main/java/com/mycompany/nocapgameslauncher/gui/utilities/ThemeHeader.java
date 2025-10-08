
package com.mycompany.nocapgameslauncher.gui.utilities;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class ThemeHeader extends JPanel implements ThemeManager.Themeable {

    private JLabel titleLabel;

    public ThemeHeader(String title) {
        setLayout(new BorderLayout());
        titleLabel = new JLabel(title);
        add(titleLabel, BorderLayout.CENTER);
        ThemeManager.addComponent(this);
        updateTheme();
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    @Override
    public void updateTheme() {
        setBackground(LightModeToggle.getComponentColor());
        titleLabel.setForeground(LightModeToggle.getTextColor());
    }
}
