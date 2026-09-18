package com.mycompany.datamanagement;

import com.mycompany.datamanagement.control.*;
import com.mycompany.datamanagement.model.*;
import com.mycompany.datamanagement.view.*;

/**
 *
 * @author Hansel
 */
public class DataManagement {

    public static void main(String[] args) {
        CustomerListModel customerListModel = new CustomerListModel();
        CustomerListControl customerListControl = new CustomerListControl(customerListModel);
        MenuControl menuControl = new MenuControl(customerListControl, customerListModel);

        MenuView menu = new MenuView(menuControl);
        menu.setVisible(true);
    }

}
