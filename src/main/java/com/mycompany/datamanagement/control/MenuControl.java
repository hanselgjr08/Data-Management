package com.mycompany.datamanagement.control;

import com.mycompany.datamanagement.model.CustomerListModel;
import com.mycompany.datamanagement.view.*;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * Handles the actions triggered from the main menu, such as loading customer
 * data from a CSV file or opening the customer list view.
 *
 * @author Hansel
 */
public class MenuControl {

    private CustomerListControl customerListControl;
    private CustomerListModel customerListModel;

    /**
     * Creates a menu controller linked to the given customer controller and
     * model.
     *
     * @param customerListControl the controller used to load customer data
     * @param customerListModel the model that holds the customer data to
     * display
     */
    public MenuControl(CustomerListControl customerListControl, CustomerListModel customerListModel) {
        this.customerListControl = customerListControl;
        this.customerListModel = customerListModel;
    }

    /**
     * Loads the customers from the default CSV file and shows a confirmation or
     * error message depending on the result.
     */
    public boolean onLoadCSV(String fileName) {
        try {
            customerListControl.loadCustomerFromFile(fileName);
            JOptionPane.showMessageDialog(null, "Customers loaded successfully");
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading file: " + e.getMessage());
            return false;
        }
    }

    public void onOpenLoadCSVForm(MenuView view) {
        LoadCSVFormView form = new LoadCSVFormView(view, this);
        form.setVisible(true);
    }

    /**
     * Opens a new window showing the current list of customers, with the option
     * to search by name and country.
     */
    public void onListCustomers() {
        CustomerListView view = new CustomerListView(customerListModel, customerListControl);
        view.setVisible(true);
    }
}
