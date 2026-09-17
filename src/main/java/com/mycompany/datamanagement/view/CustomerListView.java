package com.mycompany.datamanagement.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import com.mycompany.datamanagement.model.*;
/**
 *
 * @author Hansel
 */
public class CustomerListView extends JFrame {

    private CustomerListModel customerListModel;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton listButton;

    public CustomerListView(CustomerListModel customerListModel) {
        this.customerListModel = customerListModel;
        initComponents();
    }

    private void initComponents() {
        setTitle("Customer List");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columns = {
            "Customer ID", "First Name", "Last Name", "Company", "City",
            "Country", "Phone 1", "Phone 2", "Email", "Subscription Date", "Website"
        };
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);

        listButton = new JButton("List Customers");
        listButton.addActionListener(e -> listCustomers());

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(listButton, BorderLayout.SOUTH);
    }

    private void listCustomers() {
        ArrayList<CustomerModel> customers = customerListModel.getCustomerList();
        showCustomers(customers);
    }

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
