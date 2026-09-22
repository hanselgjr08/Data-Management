package com.mycompany.datamanagement.view;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import com.mycompany.datamanagement.control.*;

/**
 *
 * @author Hansel
 */
public class CustomerFormView extends JDialog {

    private JButton addButton;
    private JButton cancelButton;
    private JTextField customerIDField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField companyField;
    private JTextField cityField;
    private JTextField countryField;
    private JTextField phone1Field;
    private JTextField phone2Field;
    private JTextField emailField;
    private JTextField subscriptionDateField;
    private JTextField websiteField;
    private CustomerListControl customerListControl;

    public CustomerFormView(CustomerListView owner, CustomerListControl customerListControl) {
        super(owner, "New Customer", true);
        initComponents();
        this.customerListControl = customerListControl;
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel textPanel = new JPanel(new GridLayout(11, 2, 10, 10));
        customerIDField = new JTextField(10);
        firstNameField = new JTextField(10);
        lastNameField = new JTextField(10);
        companyField = new JTextField(10);
        cityField = new JTextField(10);
        countryField = new JTextField(10);
        phone1Field = new JTextField(10);
        phone2Field = new JTextField(10);
        emailField = new JTextField(10);
        subscriptionDateField = new JTextField(10);
        websiteField = new JTextField(10);

        textPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        textPanel.add(new JLabel("Customer ID: "));
        textPanel.add(customerIDField);
        textPanel.add(new JLabel("First Name: "));
        textPanel.add(firstNameField);
        textPanel.add(new JLabel("Last Name: "));
        textPanel.add(lastNameField);
        textPanel.add(new JLabel("Company: "));
        textPanel.add(companyField);
        textPanel.add(new JLabel("City: "));
        textPanel.add(cityField);
        textPanel.add(new JLabel("Country: "));
        textPanel.add(countryField);
        textPanel.add(new JLabel("Phone 1: "));
        textPanel.add(phone1Field);
        textPanel.add(new JLabel("Phone 2: "));
        textPanel.add(phone2Field);
        textPanel.add(new JLabel("Email: "));
        textPanel.add(emailField);
        textPanel.add(new JLabel("Subscription Date: "));
        textPanel.add(subscriptionDateField);
        textPanel.add(new JLabel("Website: "));
        textPanel.add(websiteField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addButton = new JButton("Add");
        cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(e -> dispose());

        addButton.addActionListener(e -> {
            addCustomer();
            dispose();
        });

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);
        add(textPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(getOwner());
    }

    // Agregado por Claude: lee lo que el usuario escribió en cada campo, en el mismo
    // orden del constructor de CustomerModel
    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        data.add(customerIDField.getText().trim());
        data.add(firstNameField.getText().trim());
        data.add(lastNameField.getText().trim());
        data.add(companyField.getText().trim());
        data.add(cityField.getText().trim());
        data.add(countryField.getText().trim());
        data.add(phone1Field.getText().trim());
        data.add(phone2Field.getText().trim());
        data.add(emailField.getText().trim());
        data.add(subscriptionDateField.getText().trim());
        data.add(websiteField.getText().trim());
        return data;
    }

    public void addCustomer() {
        ArrayList<String> data = getData();
        customerListControl.addCustomerFromView(data);
    }
}
