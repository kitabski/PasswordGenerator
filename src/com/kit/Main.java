package com.kit;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static java.lang.Integer.parseInt;

public class Main {
    private Integer passLength = 8;
    private JTextField passwordArea;
    private JTextField passwordLength;
    private JButton generateButton;
    private JButton copyButton;

    private JCheckBox includeCapitalBox;
    private boolean includeCapital = false;

    private JCheckBox includeNumbersBox;
    private boolean includeNumbers = false;

    private JCheckBox includeSpecialChBox;
    private boolean includeSpecialCh = false;

    private JCheckBox includeLowerCaseBox;
    private boolean includeLowerCase = true;

    public static void main(String[] args) {
	Main password = new Main();
    password.generate();
    }

    private void generate() {
        JFrame frame = new JFrame("RPG@kit");
        JPanel mainPanel = new JPanel();


        includeLowerCaseBox = new JCheckBox("Include lowercase letters       |");
        includeLowerCaseBox.setSelected(true);
        includeLowerCaseBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==1)
                    includeLowerCase = true;
                else
                    includeLowerCase = false;
            }
        });
        mainPanel.add(includeLowerCaseBox);

        includeCapitalBox = new JCheckBox("Include capital letters              |");
        includeCapitalBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==1)
                    includeCapital = true;
                else
                    includeCapital = false;
            }
        });
        mainPanel.add(includeCapitalBox);

        includeNumbersBox = new JCheckBox("Include Numbers                       |");
        includeNumbersBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==1)
                    includeNumbers = true;
                else
                    includeNumbers = false;
            }
        });
        mainPanel.add(includeNumbersBox);

        includeSpecialChBox = new JCheckBox("Include Special characters     |");
        includeSpecialChBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==1)
                    includeSpecialCh = true;
                else
                    includeSpecialCh = false;
            }
        });
        mainPanel.add(includeSpecialChBox);




        JLabel setPL = new JLabel("Set password length:");
        passwordLength = new JTextField("8", 2);
        passwordLength.addActionListener(new PasswordLengthListener());

        passwordArea = new JTextField(20);
        generateButton = new JButton("Generate");
        generateButton.addActionListener(new GeneratePasswordListener());

        mainPanel.add(setPL);
        mainPanel.add(passwordLength);

        mainPanel.add(passwordArea);
        mainPanel.add(generateButton);

        copyButton = new JButton("Copy password");
        copyButton.addActionListener(new CopyButton());
        mainPanel.add(copyButton);


        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setDefaultCloseOperation(3);
        frame.setSize(250, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private class GeneratePasswordListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            passwordArea.setText(PasswordGenerator.password
                    (passLength, includeLowerCase, includeCapital, includeNumbers, includeSpecialCh));
            PasswordGenerator.ranges.clear();
        }
    }

    private class PasswordLengthListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                passLength = parseInt(passwordLength.getText());
            } catch (NumberFormatException ex) {
                passwordArea.setText("Password length should be an integer");
            }

        }
    }

    private class CopyButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String password = passwordArea.getText();
            StringSelection ss = new StringSelection(password);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(ss, null);
        }
    }
}
