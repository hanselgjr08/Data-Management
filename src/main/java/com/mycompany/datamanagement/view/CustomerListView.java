package com.mycompany.datamanagement.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import com.mycompany.datamanagement.model.*;

/**
 * Displays the list of registered customers in a table.
 * The table is populated automatically when the window is created.
 * 
 * @author Hansel
 */
public class CustomerListView extends JFrame {

    private CustomerListModel customerListModel;
    private JTable table;
    private DefaultTableModel tableModel;
    
    /**
     * Creates the customer list window linked to the given model,
     * and immediately displays its current data.
     *
     * @param customerListModel the model containing the customers to display
     */
    public CustomerListView(CustomerListModel customerListModel) {
        this.customerListModel = customerListModel;
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

        String[] columns = {
            "Customer ID", "First Name", "Last Name", "Company", "City",
            "Country", "Phone 1", "Phone 2", "Email", "Subscription Date", "Website"
        };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * Retrieves the current customers from the model and shows them in the table.
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
}
