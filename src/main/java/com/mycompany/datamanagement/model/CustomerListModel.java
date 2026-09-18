package com.mycompany.datamanagement.model;

import java.util.ArrayList;

/**
 *
 * @author Hansel
 */
public class CustomerListModel {

    private ArrayList<CustomerModel> customerList = new ArrayList<>();

    public void addCustomer(CustomerModel customer) {
        if (customer != null) {
            customerList.add(customer);
        }
    }

    public ArrayList<CustomerModel> getCustomerList() {
        return new ArrayList<>(customerList); // devuelve una COPIA, no la referencia original
    }
}
