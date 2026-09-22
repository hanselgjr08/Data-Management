package com.mycompany.datamanagement.view;

import com.mycompany.datamanagement.control.CustomerListControl;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import com.mycompany.datamanagement.model.*;

/**
 * Displays the list of registered customers in a table, and lets the user
 * search for customers by name and country.
 *
 * @author Hansel
 */
public class CustomerListView extends JFrame {

    private CustomerListModel customerListModel;
    private JTable table;
    private DefaultTableModel tableModel;
    private CustomerListControl customerListControl;
    private JTextField nameField;
    private JTextField countryField;
    private JButton searchButton;
    private JButton addButton;

    /**
     * Creates the customer list window linked to the given model and controller,
     * and immediately displays its current data.
     *
     * @param customerListModel the model containing the customers to display
     * @param customerListControl the controller used to perform searches
     */
    public CustomerListView(CustomerListModel customerListModel, CustomerListControl customerListControl) {
        this.customerListModel = customerListModel;
        this.customerListControl = customerListControl;
        initComponents();
        listCustomers();
    }

    /**
     * Builds and arranges the window's visual components (table and layout).
     */
    private void initComponents() {
        setTitle("Customer List");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10 ));
        addButton = new JButton("Add Customer");
        
        addButton.addActionListener(e -> customerListControl.onAddCustomer(this));
        
        addPanel.add(addButton);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        nameField = new JTextField(10);
        countryField = new JTextField(10);
        searchButton = new JButton("Search");

        searchButton.addActionListener(e -> search());

        searchPanel.add(new JLabel("Name:"));
        searchPanel.add(nameField);
        searchPanel.add(new JLabel("Country:"));
        searchPanel.add(countryField);
        searchPanel.add(searchButton);

        String[] columns = {
            "Customer ID", "First Name", "Last Name", "Company", "City",
            "Country", "Phone 1", "Phone 2", "Email", "Subscription Date", "Website"
        };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        add(addPanel, BorderLayout.SOUTH);
        add(searchPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * Retrieves the current customers from the model and shows them in the
     * table.
     */
    private void listCustomers() {
        ArrayList<CustomerModel> customers = customerListModel.getCustomerList();
        showCustomers(customers);
    }

    /**
     * Clears the table and fills it with the given list of customers.
     *
     * @param customers the customers to display
     */
    private void showCustomers(ArrayList<CustomerModel> customers) {
        tableModel.setRowCount(0);
        for (CustomerModel c : customers) {
            Object[] row = {
                c.getCustomerId(), c.getFirstName(), c.getLastName(), c.getCompany(),
                c.getCity(), c.getCountry(), c.getPhone1(), c.getPhone2(),
                c.getEmail(), c.getSubscriptionDate(), c.getWebsite()
            };
            tableModel.addRow(row);
        }
    }
    
    /**
     * Reads the name and country entered by the user, searches for matching
     * customers through the controller, and displays the results in the table.
     */
    private void search() {
        String name = nameField.getText();
        String country = countryField.getText();
        ArrayList<CustomerModel> results = customerListControl.search(name, country);
        showCustomers(results);
    }
}
