package com.mycompany.datamanagement.model;

import java.util.ArrayList;

/**
 *
 * @author Hansel
 */
public class CustomerListModel {
    private ArrayList<CustomerModel> customerList = new ArrayList<>();

    public void addCustomer(CustomerModel customer){
        if (customer != null) {
            customerList.add(customer);
        }
    }
    
}
