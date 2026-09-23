package com.mycompany.datamanagement.view;

import java.awt.*;
import javax.swing.*;
import com.mycompany.datamanagement.control.*;

/**
 *
 * @author Hansel
 */
public class LoadCSVFormView extends JDialog {

    private JTextField fileNameField;
    private JButton loadButton;
    private JButton cancelButton;
    private MenuControl menuControl;

    public LoadCSVFormView(MenuView owner, MenuControl menuControl) {
        super(owner, "Load CSV", true);
        this.menuControl = menuControl;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        fileNameField = new JTextField("customers-1000", 15);
        fieldPanel.add(new JLabel("File name:"));
        fieldPanel.add(fileNameField);
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        loadButton = new JButton("Load");
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        loadButton.addActionListener(e -> {
            boolean success = menuControl.onLoadCSV(fileNameField.getText().trim());
            if (success) {
                dispose();
            }
        });
        buttonPanel.add(loadButton);
        buttonPanel.add(cancelButton);

        add(fieldPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(getOwner());
    }
}
