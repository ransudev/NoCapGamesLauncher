package com.mycompany.nocapgameslauncher.gui.panels;

import com.mycompany.nocapgameslauncher.gui.mainFrame;
import com.mycompany.nocapgameslauncher.gui.utilities.LightModeToggle;
import com.mycompany.nocapgameslauncher.gui.utilities.ThemePanel;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Friends extends ThemePanel {

    private JList<String> friendList;
    private DefaultListModel<String> listModel;

    public Friends(mainFrame frame) {
        super(new BorderLayout());
        ArrayList<String> friendNames = loadFriendsFromFile("/friends.txt");

        listModel = new DefaultListModel<>();
        if (friendNames.isEmpty()) {
            listModel.addElement("No friends available.");
        } else {
            for (String name : friendNames) {
                listModel.addElement(name);
            }
        }

        // Create the JList and customize it
        friendList = new JList<>(listModel);
        friendList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setBackground(isSelected ? LightModeToggle.getComponentColor().brighter() : LightModeToggle.getComponentColor());
                c.setForeground(LightModeToggle.getTextColor());
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return c;
            }
        });

        // Add the list to a scroll pane
        JScrollPane scrollPane = new JScrollPane(friendList);
        scrollPane.setBorder(BorderFactory.createLineBorder(LightModeToggle.getComponentColor()));

        // Add the scroll pane to the panel
        add(scrollPane, BorderLayout.CENTER);
        updateTheme();
    }

    private ArrayList<String> loadFriendsFromFile(String filename) {
        ArrayList<String> friends = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Friends.class.getResourceAsStream(filename)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    friends.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading friend list from " + filename + ": " + e.getMessage());
        }
        return friends;
    }

    @Override
    public void updateTheme() {
        super.updateTheme();
        if (friendList != null) {
            friendList.setBackground(LightModeToggle.getComponentColor());
            friendList.setForeground(LightModeToggle.getTextColor());
            friendList.setSelectionBackground(LightModeToggle.getComponentColor().brighter());
            friendList.setSelectionForeground(LightModeToggle.getTextColor());
        }
    }
}