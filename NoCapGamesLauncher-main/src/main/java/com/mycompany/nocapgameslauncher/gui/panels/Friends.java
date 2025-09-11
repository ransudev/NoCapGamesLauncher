package com.mycompany.nocapgameslauncher.gui.panels;

import com.mycompany.nocapgameslauncher.gui.mainFrame;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Friends extends JPanel {

    public Friends(mainFrame frame) {
        setLayout(new BorderLayout());
        setBackground(new Color(0x121212));
        ArrayList<String> friendNames = loadFriendsFromFile("/friends.txt");

        DefaultListModel<String> listModel = new DefaultListModel<>();
        if (friendNames.isEmpty()) {
            listModel.addElement("No friends available.");
        } else {
            for (String name : friendNames) {
                listModel.addElement(name);
            }
        }

        // Create the JList and customize it
        JList<String> friendList = new JList<>(listModel);
        friendList.setBackground(new Color(0x1e1e1e));
        friendList.setForeground(Color.WHITE);
        friendList.setSelectionBackground(new Color(0x333333));
        friendList.setSelectionForeground(Color.WHITE);
        friendList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                c.setBackground(isSelected ? new Color(0x333333) : new Color(0x1e1e1e));
                c.setForeground(Color.WHITE);
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return c;
            }
        });

        // Add the list to a scroll pane
        JScrollPane scrollPane = new JScrollPane(friendList);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0x333333)));

        // Add the scroll pane to the panel
        add(scrollPane, BorderLayout.CENTER);
    }

    private ArrayList<String> loadFriendsFromFile(String filename) {
        ArrayList<String> friends = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filename)))) {
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
}