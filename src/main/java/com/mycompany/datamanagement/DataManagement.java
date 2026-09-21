package com.mycompany.datamanagement;

import com.mycompany.datamanagement.control.*;
import com.mycompany.datamanagement.model.*;
import com.mycompany.datamanagement.view.*;

/**
 * Entry point of the application. Wires together the Model, View and
 * Control layers and starts the main menu.
 * 
 * @author Hansel
 */
public class DataManagement {

    public static void main(String[] args) {
        //new CustomerFormView(null).setVisible(true);
        
        CustomerListModel customerListModel = new CustomerListModel();
        CustomerListControl customerListControl = new CustomerListControl(customerListModel);
        MenuControl menuControl = new MenuControl(customerListControl, customerListModel);

        MenuView menu = new MenuView(menuControl);
        menu.setVisible(true);
    }

}
