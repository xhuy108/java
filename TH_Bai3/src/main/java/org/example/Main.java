package org.example;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Text Area Copy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);

        JTextArea textArea1 = new JTextArea(10, 20);
        textArea1.setLineWrap(true);
        JScrollPane scrollPane1 = new JScrollPane(textArea1);

        JTextArea textArea2 = new JTextArea(10, 20);
        textArea2.setLineWrap(true);
        JScrollPane scrollPane2 = new JScrollPane(textArea2);

        JButton copyButton = new JButton("Copy");
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedText = textArea1.getSelectedText();
                if (selectedText != null) {
                    // Get system clipboard
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    // Create a new StringSelection object
                    StringSelection selection = new StringSelection(selectedText);
                    // Set the clipboard content
                    clipboard.setContents(selection, null);

                    // Optionally clear textArea2 before appending
                    textArea2.setText("");


                    // Append the copied text to textArea2
                    textArea2.append(selectedText);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(scrollPane1);
        panel.add(copyButton);
        panel.add(scrollPane2);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}