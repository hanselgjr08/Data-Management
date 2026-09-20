package com.mycompany.datamanagement.control;

import com.mycompany.datamanagement.model.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles the logic for loading customers from a CSV file and adding
 * them to the customer list model.
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
     * Parses a list of CSV lines and creates a Customer for each record,
     * adding it to the customer list. The first line is skipped, since it
     * corresponds to the CSV header.
     *
     * @param lines the CSV lines to parse, including the header as the first element
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
     * Reads a CSV file and loads its records as Customer objects into the model,
     * Combines file reading (readLine) and parsing (addCustomerFromCSV) into a
     * single operation.
     *
     * @param fileName the name of the CSV file (without extension), located in the inputfiles folder
     * @throws IOException if the file cannot be read
     */
    public void loadCustomerFromFile(String fileName) throws IOException {
        ArrayList<String> lines = CSVFileManagerControl.readLine(fileName);
        addCustomerFromCSV(lines);
    }
    
}
