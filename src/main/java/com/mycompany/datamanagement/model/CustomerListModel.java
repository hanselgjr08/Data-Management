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

    /**
     * Searches for customers whose first name matches the given name
     * (case-insensitive).
     *
     * @param name the first name to search for
     * @return a list with every matching customer (empty if none is found)
     */
    public ArrayList<CustomerModel> searchByName(String name) {
        ArrayList<CustomerModel> coincidences = new ArrayList<>();
        for (CustomerModel c : customerList) {
            if (c.getFirstName().equalsIgnoreCase(name)) {
                coincidences.add(c);
            }
        }
        return coincidences;
    }

    /**
     * Searches for all customers belonging to a specific country
     * (case-insensitive).
     *
     * @param country the country to search for
     * @return a list with every matching customer (empty if none is found)
     */
    public ArrayList<CustomerModel> searchByCountry(String country) {
        ArrayList<CustomerModel> coincidences = new ArrayList<>();
        for (CustomerModel c : customerList) {
            if (c.getCountry().equalsIgnoreCase(country)) {
                coincidences.add(c);
            }
        }
        return coincidences;
    }

    /**
     * Compares the results of a name search and a country search, keeping only
     * the customers that appear in both lists.
     *
     * @param byName the results from searchByName
     * @param byCountry the results from searchByCountry
     * @return a list with only the customers that satisfy both conditions
     */
    public ArrayList<CustomerModel> search(
            ArrayList<CustomerModel> byName,
            ArrayList<CustomerModel> byCountry) {

        ArrayList<CustomerModel> coincidences = new ArrayList<>();
        for (CustomerModel c : byName) {
            if (byCountry.contains(c)) {
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
