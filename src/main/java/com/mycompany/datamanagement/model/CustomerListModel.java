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
     * Returns a copy of the customer list, so callers cannot modify the
     * internal list directly.
     *
     * @return a new ArrayList containing all registered customers
     */
    public ArrayList<CustomerModel> getCustomerList() {
        return new ArrayList<>(customerList);
    }

    public void clearCustomers() {
        customerList.clear();
    }

    /**
     * Searches for customers matching a name and/or a country. A blank
     * parameter is ignored, so it matches every customer for that criterion.
     *
     * @param name the first name to search for, or blank to ignore this
     * criterion
     * @param country the country to search for, or blank to ignore this
     * criterion
     * @return a list with every customer matching both non-blank criteria
     */
    public ArrayList<CustomerModel> search(String name, String country) {
        ArrayList<CustomerModel> coincidences = new ArrayList<>();
        for (CustomerModel c : customerList) {
            boolean nameOk = name.isBlank() || c.getFirstName().equalsIgnoreCase(name);
            boolean countryOk = country.isBlank() || c.getCountry().equalsIgnoreCase(country);
            if (nameOk && countryOk) {
                coincidences.add(c);
            }
        }
        return coincidences;
    }

    /**
     * Finds the customer with the given ID.
     *
     * @param customerId the ID to search for
     * @return the matching customer, or null if none is found
     */
    public CustomerModel findById(String customerId) {
        for (CustomerModel customer : customerList) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    public void removeCustomer(CustomerModel customer) {
        customerList.remove(customer);
    }
}
