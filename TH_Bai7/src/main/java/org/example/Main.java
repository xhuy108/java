package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    private JFrame frame;
    private JPanel colorPanel;
    private ButtonGroup buttonGroup;
    private JRadioButton redButton, greenButton, blueButton, customButton;
    private JButton chooseColorButton;
    private Color currentColor = Color.BLACK;

    public Main() {
        createGUI();
        frame.setVisible(true);
    }

    private void createGUI() {
        frame = new JFrame("Color Chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        colorPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        buttonGroup = new ButtonGroup();

        redButton = new JRadioButton("Red");
        redButton.setActionCommand("Red");
        redButton.addActionListener(new ColorChangeListener());
        buttonGroup.add(redButton);
        colorPanel.add(redButton);

        greenButton = new JRadioButton("Green");
        greenButton.setActionCommand("Green");
        greenButton.addActionListener(new ColorChangeListener());
        buttonGroup.add(greenButton);
        colorPanel.add(greenButton);

        blueButton = new JRadioButton("Blue");
        blueButton.setActionCommand("Blue");
        blueButton.addActionListener(new ColorChangeListener());
        buttonGroup.add(blueButton);
        colorPanel.add(blueButton);

        customButton = new JRadioButton("Custom");
        customButton.setActionCommand("Custom");
        customButton.addActionListener(new ColorChangeListener());
        buttonGroup.add(customButton);
        colorPanel.add(customButton);

        chooseColorButton = new JButton("Choose Color");
        chooseColorButton.addActionListener(new ChooseColorListener());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(chooseColorButton);

        frame.getContentPane().add(colorPanel, BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private class ColorChangeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedColor = e.getActionCommand();
            switch (selectedColor) {
                case "Red":
                    currentColor = Color.RED;
                    break;
                case "Green":
                    currentColor = Color.GREEN;
                    break;
                case "Blue":
                    currentColor = Color.BLUE;
                    break;
            }
            frame.getContentPane().setBackground(currentColor);
        }
    }

    private class ChooseColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color selectedColor = JColorChooser.showDialog(frame, "Choose Window Color", currentColor);
            if (selectedColor != null) {
                currentColor = selectedColor;
                frame.getContentPane().setBackground(currentColor);
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
