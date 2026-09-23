package com.mycompany.datamanagement.control;

import com.mycompany.datamanagement.model.*;
import java.io.IOException;
import java.util.ArrayList;
import com.mycompany.datamanagement.view.*;

/**
 * Handles the logic for loading customers from a CSV file, searching for
 * customers in the list, and creating or updating customers from the Add and
 * Update Customer forms.
 *
 * @author Hansel
 */
public class CustomerListControl {

    private CustomerListModel customerListModel;

    /**
     * Creates a controller linked to the given customer list model.
     *
     * @param customerListModel the model where loaded customers will be stored
     */
    public CustomerListControl(CustomerListModel customerListModel) {
        this.customerListModel = customerListModel;
    }

    /**
     * Parses a list of CSV lines and creates a Customer for each record, adding
     * it to the customer list. The first line is skipped, since it corresponds
     * to the CSV header.
     *
     * @param lines the CSV lines to parse, including the header as the first
     * element
     */
    public void addCustomerFromCSV(ArrayList<String> lines) {

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            String customerId = data[1];
            String firstName = data[2];
            String lastName = data[3];
            String company = data[4].replace("\"", "");
            String city = data[5];
            String country = data[6];
            String phone1 = data[7];
            String phone2 = data[8];
            String email = data[9];
            String subscriptionDate = data[10];
            String website = data[11];

            CustomerModel customer = new CustomerModel(
                    customerId, firstName, lastName, company,
                    city, country, phone1, phone2, email,
                    subscriptionDate, website
            );

            customerListModel.addCustomer(customer);
        }
    }

    /**
     * Reads a CSV file and loads its records as Customer objects into the
     * model, Combines file reading (readLine) and parsing (addCustomerFromCSV)
     * into a single operation.
     *
     * @param fileName the name of the CSV file (without extension), located in
     * the inputfiles folder
     * @throws IOException if the file cannot be read
     */
    public void loadCustomerFromFile(String fileName) throws IOException {
        customerListModel.clearCustomers();
        ArrayList<String> lines = CSVFileManagerControl.readLine(fileName);
        addCustomerFromCSV(lines);
    }

    /**
     * Searches for customers matching both a name and a country, delegating the
     * actual search logic to the model.
     *
     * @param name the first name to search for
     * @param country the country to search for
     * @return a list with the customers matching both criteria
     */
    public ArrayList<CustomerModel> search(String name, String country) {
        ArrayList<CustomerModel> byName = customerListModel.searchByName(name);
        ArrayList<CustomerModel> byCountry = customerListModel.searchByCountry(country);
        return customerListModel.search(byName, byCountry);
    }

    /**
     * Opens the Add Customer form as a modal dialog over the given customer
     * list window.
     *
     * @param view the customer list window that owns the new form
     */
    public void onAddCustomer(CustomerListView view) {
        CustomerFormView form = new CustomerFormView(view, this);
        form.setVisible(true);
    }

    /**
     * Checks that none of the given values is empty.
     *
     * @param data the values to check, one per form field
     * @return true if every value is non-empty, false if at least one is empty
     */
    public boolean notNull(ArrayList<String> data) {
        for (String field : data) {
            if (field.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates the data collected from the Add Customer form and, if valid,
     * creates a new Customer and adds it to the customer list model.
     *
     * @param data the values entered in the form, in the same order as
     * CustomerModel's constructor parameters
     * @return true if the customer was created and added, false if validation
     * failed
     */
    public boolean addCustomerFromView(ArrayList<String> data) {
        if (notNull(data)) {
            String customerId = data.get(0);
            String firstName = data.get(1);
            String lastName = data.get(2);
            String company = data.get(3);
            String city = data.get(4);
            String country = data.get(5);
            String phone1 = data.get(6);
            String phone2 = data.get(7);
            String email = data.get(8);
            String subscriptionDate = data.get(9);
            String website = data.get(10);

            CustomerModel customer = new CustomerModel(
                    customerId, firstName, lastName, company,
                    city, country, phone1, phone2, email,
                    subscriptionDate, website
            );

            customerListModel.addCustomer(customer);

            return true;
        }
        return false;
    }

    /**
     * Validates the data collected from the Update Customer form and, if valid,
     * updates the matching customer's data.
     *
     * @param data the values entered in the form, in the same order as
     * CustomerModel's constructor parameters
     * @return true if the customer was found and updated, false if validation
     * failed
     */
    public boolean updateCustomerFromView(ArrayList<String> data) {
        if (!notNull(data)) {
            return false;
        }

        CustomerModel customer = customerListModel.findById(data.get(0));

        customer.setFirstName(data.get(1));
        customer.setLastName(data.get(2));
        customer.setCompany(data.get(3));
        customer.setCity(data.get(4));
        customer.setCountry(data.get(5));
        customer.setPhone1(data.get(6));
        customer.setPhone2(data.get(7));
        customer.setEmail(data.get(8));
        customer.setSubscriptionDate(data.get(9));
        customer.setWebsite(data.get(10));

        return true;
    }

    /**
     * Opens the Update Customer form as a modal dialog over the given customer
     * list window, preloaded with the selected customer's data.
     *
     * @param view the customer list window that owns the new form
     * @param customer the customer to edit
     */
    public void onUpdateCustomer(CustomerListView view, CustomerModel customer) {
        UpdateFormView form = new UpdateFormView(view, this, customer);
        form.setVisible(true);
    }

    public void onDeleteCustomer(String customerId) {
        CustomerModel customer = customerListModel.findById(customerId);
        customerListModel.removeCustomer(customer);
    }

}
