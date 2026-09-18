package com.mycompany.datamanagement.view;

import com.mycompany.datamanagement.control.MenuControl;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Hansel
 */
public class MenuView extends JFrame {

    private MenuControl menuControl;
    private JButton loadButton;
    private JButton listButton;

    public MenuView(MenuControl menuControl) {
        this.menuControl = menuControl;
        initComponents();
    }

    private void initComponents() {
        setTitle("Customers Repo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Customer Registry", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        loadButton = new JButton("Load CSV");
        listButton = new JButton("List Customers");

        loadButton.addActionListener(e -> menuControl.onLoadCSV());
        listButton.addActionListener(e -> menuControl.onListCustomers());

        buttonPanel.add(loadButton);
        buttonPanel.add(listButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
}
