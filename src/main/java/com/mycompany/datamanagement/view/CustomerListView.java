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
    private JButton refreshButton;
    private JButton updateButton;
    private JButton deleteButton;

    /**
     * Creates the customer list window linked to the given model and
     * controller, and immediately displays its current data.
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
     * Builds and arranges the window's visual components: the search panel, the
     * Add Customer and Refresh buttons, and the customer table.
     */
    private void initComponents() {
        setTitle("Customer List");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // northPanel =========================================================
        JPanel northPanel = new JPanel(new BorderLayout());

        // searchPanel---------------------------------------------
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

        //refreshPanel----------------------------------------------
        JPanel refreshPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> listCustomers());
        refreshPanel.add(refreshButton);

        northPanel.add(searchPanel, BorderLayout.CENTER);
        northPanel.add(refreshPanel, BorderLayout.EAST);
        add(northPanel, BorderLayout.NORTH);

        // new southPanel _=_==_=_=_=_=_=_=_=__=_=_==_=__=_=
        JPanel southPanel = new JPanel(new BorderLayout());

        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        addButton = new JButton("Add Customer");
        addPanel.add(addButton);
        addButton.addActionListener(e -> customerListControl.onAddCustomer(this));

        JPanel recordActionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        recordActionsPanel.add(updateButton);
        recordActionsPanel.add(deleteButton);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);

        updateButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            CustomerModel selected = customerListModel.getCustomerList().get(row);
            customerListControl.onUpdateCustomer(this, selected);
        });

        southPanel.add(addPanel, BorderLayout.CENTER);
        southPanel.add(recordActionsPanel, BorderLayout.EAST);
        add(southPanel, BorderLayout.SOUTH);

        String[] columns = {
            "Customer ID", "First Name", "Last Name", "Company", "City",
            "Country", "Phone 1", "Phone 2", "Email", "Subscription Date", "Website"
        };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            boolean hasSelection = table.getSelectedRow() != -1;
            updateButton.setEnabled(hasSelection);
            deleteButton.setEnabled(hasSelection);
        });

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
