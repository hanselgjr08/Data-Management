package com.mycompany.datamanagement.model;

import java.util.ArrayList;

/**
 * Represents the list of registered customers.
 * 
 * @author Hansel
 */
public class CustomerListModel {

    private ArrayList<CustomerModel> customerList = new ArrayList<>();

     /**
     * Adds a customer to the list, ignoring null values.
     *
     * @param customer the customer to add
     */
    public void addCustomer(CustomerModel customer) {
        if (customer != null) {
            customerList.add(customer);
        }
    }
    
    /**
     * Returns a copy of the customer list, so callers cannot modify
     * the internal list directly.
     *
     * @return a new ArrayList containing all registered customers
     */
    public ArrayList<CustomerModel> getCustomerList() {
        return new ArrayList<>(customerList);
    }
}
