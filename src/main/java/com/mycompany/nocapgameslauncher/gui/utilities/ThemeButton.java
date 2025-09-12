package com.mycompany.nocapgameslauncher.gui.utilities;

import javax.swing.JButton;
import java.awt.Color;

public class ThemeButton extends JButton implements ThemeManager.Themeable {

    private boolean useAccentColor = false;
    private boolean manageForeground = true;
    private Color initialForeground = null;
    private boolean customColor = false;

    public ThemeButton(String text) {
        this(text, false, true, null, false);
    }

    public ThemeButton(String text, boolean useAccentColor) {
        this(text, useAccentColor, true, null, false);
    }

    public ThemeButton(String text, boolean useAccentColor, boolean manageForeground, Color initialForeground) {
        this(text, useAccentColor, manageForeground, initialForeground, false);
    }

    public ThemeButton(String text, boolean useAccentColor, boolean manageForeground, Color initialForeground, boolean customColor) {
        super(text);
        this.useAccentColor = useAccentColor;
        this.manageForeground = manageForeground;
        this.initialForeground = initialForeground;
        this.customColor = customColor;
        ThemeManager.addComponent(this);
        updateTheme();
    }

    public void setManageForeground(boolean manage) {
        this.manageForeground = manage;
    }

    @Override
    public void updateTheme() {
        if (!customColor) {
            setBackground(LightModeToggle.getComponentColor());
            if (manageForeground) {
                if (useAccentColor) {
                    setForeground(LightModeToggle.getAccentColor());
                } else {
                    setForeground(LightModeToggle.getTextColor());
                }
            } else if (initialForeground != null) {
                setForeground(initialForeground);
            }
        }
    }
}