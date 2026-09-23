package com.mycompany.datamanagement.view;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;
import com.mycompany.datamanagement.control.*;
import com.mycompany.datamanagement.model.*;

/**
 *
 * @author Hansel
 */
public class UpdateFormView extends JDialog {

    private CustomerListControl customerListControl;
    private CustomerModel customer;
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
    private JButton updateButton;
    private JButton cancelButton;

    public UpdateFormView(CustomerListView owner, CustomerListControl customerListControl, CustomerModel customer) {
        super(owner, "Update Customer", true);
        this.customerListControl = customerListControl;
        this.customer = customer;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel textPanel = new JPanel(new GridLayout(11, 2, 10, 10));
        customerIDField = new JTextField(10);
        customerIDField.setText(customer.getCustomerId());
        customerIDField.setEnabled(false);

        firstNameField = new JTextField(10);
        firstNameField.setText(customer.getFirstName());

        lastNameField = new JTextField(10);
        lastNameField.setText(customer.getLastName());

        companyField = new JTextField(10);
        companyField.setText(customer.getCompany());

        cityField = new JTextField(10);
        cityField.setText(customer.getCity());

        countryField = new JTextField(10);
        countryField.setText(customer.getCountry());

        phone1Field = new JTextField(10);
        phone1Field.setText(customer.getPhone1());

        phone2Field = new JTextField(10);
        phone2Field.setText(customer.getPhone2());

        emailField = new JTextField(10);
        emailField.setText(customer.getEmail());

        subscriptionDateField = new JTextField(10);
        subscriptionDateField.setText(customer.getSubscriptionDate());

        websiteField = new JTextField(10);
        websiteField.setText(customer.getWebsite());
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
        updateButton = new JButton("Update");
        cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(e -> dispose());

        updateButton.addActionListener(e -> {
            if (!hasChanges()) {
                JOptionPane.showMessageDialog(this, "No changes were made.", "Nothing to Update", JOptionPane.WARNING_MESSAGE);
                return;
            }
            boolean success = customerListControl.updateCustomerFromView(getData());
            if (success) {
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields before updating.", "Missing Fields", JOptionPane.WARNING_MESSAGE);
            }
        });

        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);
        add(textPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(getOwner());
    }

    /**
     * Checks whether any field was changed from its original value.
     *
     * @return true if at least one field differs from the original customer
     */
    private boolean hasChanges() {
        return !firstNameField.getText().trim().equals(customer.getFirstName())
                || !lastNameField.getText().trim().equals(customer.getLastName())
                || !companyField.getText().trim().equals(customer.getCompany())
                || !cityField.getText().trim().equals(customer.getCity())
                || !countryField.getText().trim().equals(customer.getCountry())
                || !phone1Field.getText().trim().equals(customer.getPhone1())
                || !phone2Field.getText().trim().equals(customer.getPhone2())
                || !emailField.getText().trim().equals(customer.getEmail())
                || !subscriptionDateField.getText().trim().equals(customer.getSubscriptionDate())
                || !websiteField.getText().trim().equals(customer.getWebsite());
    }

    /**
     * Reads the text entered in each field, in the same order as
     * CustomerModel's constructor parameters.
     *
     * @return the entered values, with leading and trailing spaces removed
     */
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
}
