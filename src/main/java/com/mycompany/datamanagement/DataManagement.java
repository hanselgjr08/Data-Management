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

        CustomerListView vista = new CustomerListView(customerListModel);
        vista.setVisible(true);
    }
    
}
