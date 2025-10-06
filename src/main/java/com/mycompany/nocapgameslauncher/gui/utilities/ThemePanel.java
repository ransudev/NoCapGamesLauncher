package com.mycompany.nocapgameslauncher.gui.utilities;

import java.awt.LayoutManager;
import javax.swing.JPanel;

public class ThemePanel extends JPanel implements ThemeManager.Themeable {

    public ThemePanel() {
        ThemeManager.addComponent(this);
    }
    
    public ThemePanel(LayoutManager layout) {
        super(layout);
        ThemeManager.addComponent(this);
    }

    @Override
    public void updateTheme() {
        setBackground(LightModeToggle.getComponentColor());
        setForeground(LightModeToggle.getTextColor());
    }
}