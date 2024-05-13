package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuBarExample {

    private JFrame frame;
    private JMenuBar menuBar;
    private JMenu fileMenu, formatMenu;
    private JMenuItem aboutMenuItem, exitMenuItem, colorMenuItem, fontMenuItem;
    private JPanel textPanel;
    private ButtonGroup fontButtonGroup;
    private JRadioButton font1RadioButton, font2RadioButton, font3RadioButton;
    private JCheckBox boldCheckBox, italicCheckBox;

    public MenuBarExample() {
        createGUI();
        frame.setVisible(true);
    }

    private void createGUI() {
        frame = new JFrame("Menu Bar Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // File Menu
        fileMenu = new JMenu("File");
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new AboutActionListener());
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ExitActionListener());
        fileMenu.add(aboutMenuItem);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        // Format Menu
        formatMenu = new JMenu("Format");
        colorMenuItem = new JMenuItem("Color");
        colorMenuItem.addActionListener(new ColorActionListener());
        fontMenuItem = new JMenuItem("Font");
        fontMenuItem.addActionListener(new FontActionListener());
        formatMenu.add(colorMenuItem);
        formatMenu.add(fontMenuItem);
        menuBar.add(formatMenu);

        // Text Panel
        textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Sample Text");
        textPanel.add(label);
        frame.getContentPane().add(textPanel, BorderLayout.CENTER);
    }

    private class AboutActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(frame, "About Menu Item Clicked");
        }
    }

    private class ExitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class ColorActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color selectedColor = JColorChooser.showDialog(frame, "Choose Text Color", textPanel.getForeground());
            if (selectedColor != null) {
                textPanel.setForeground(selectedColor);
            }
        }
    }

    private class FontActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog fontDialog = new JDialog(frame, "Font Settings", true);
            fontDialog.setLayout(new FlowLayout());

            fontButtonGroup = new ButtonGroup();
            font1RadioButton = new JRadioButton("Font 1");
            font2RadioButton = new JRadioButton("Font 2");
            font3RadioButton = new JRadioButton("Font 3");
            fontButtonGroup.add(font1RadioButton);
            fontButtonGroup.add(font2RadioButton);
            fontButtonGroup.add(font3RadioButton); // Select an initial font

            boldCheckBox = new JCheckBox("Bold");
            italicCheckBox = new JCheckBox("Italic");

            fontDialog.add(font1RadioButton);
            fontDialog.add(font2RadioButton);
            fontDialog.add(font3RadioButton);
            fontDialog.add(boldCheckBox);
            fontDialog.add(italicCheckBox);

            JButton applyButton = new JButton("Apply");
            applyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Font font = null; // Initialize font
                    if (font1RadioButton.isSelected()) {
                        // Set font 1
                    } else if (font2RadioButton.isSelected()) {
                        // Set font 2
                    } else if (font3RadioButton.isSelected()) {
                        // Set font 3
                    }

                    int style = Font.PLAIN;
                    if (boldCheckBox.isSelected()) {
                        style |= Font.BOLD;
                    }
                    if (italicCheckBox.isSelected()) {
                        style |= Font.ITALIC;
                    }

                    if (font != null) {
                        font = font.deriveFont(style); // Apply style to the chosen font
                        textPanel.setFont(font);
                    }
                }
            });
        }
    }
    public static void main(String[] args) {
                new MenuBarExample();
            }
        }



