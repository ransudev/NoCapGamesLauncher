
package com.mycompany.nocapgameslauncher.gui.utilities;

import java.util.ArrayList;
import java.util.List;

public class ThemeManager {

    private static final List<Themeable> components = new ArrayList<>();

    public static void addComponent(Themeable component) {
        components.add(component);
    }

    public static void removeComponent(Themeable component) {
        components.remove(component);
    }

    public static void updateTheme() {
        for (Themeable component : components) {
            component.updateTheme();
        }
    }

    public interface Themeable {
        void updateTheme();
    }
}
