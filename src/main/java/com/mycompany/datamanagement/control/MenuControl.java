package com.mycompany.datamanagement.control;

import com.mycompany.datamanagement.model.CustomerListModel;
import com.mycompany.datamanagement.view.CustomerListView;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Hansel
 */
public class MenuControl {

    private CustomerListControl customerListControl;
    private CustomerListModel customerListModel;

    public MenuControl(CustomerListControl customerListControl, CustomerListModel customerListModel) {
        this.customerListControl = customerListControl;
        this.customerListModel = customerListModel;
    }

    public void onLoadCSV() {
        try {
            customerListControl.loadCustomerFromFile("customers-1000");
            JOptionPane.showMessageDialog(null, "Customers loaded successfully");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading file: " + e.getMessage());
        }
    }

    public void onListCustomers() {
        CustomerListView view = new CustomerListView(customerListModel);
        view.setVisible(true);
    }
}
