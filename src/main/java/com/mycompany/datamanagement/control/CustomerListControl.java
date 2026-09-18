package com.mycompany.datamanagement.control;

import com.mycompany.datamanagement.model.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Hansel
 */
public class CustomerListControl {

    private CustomerListModel customerListModel;

    public CustomerListControl(CustomerListModel customerListModel) {
        this.customerListModel = customerListModel;
    }

    public void addCustomerFromCSV(ArrayList<String> lines) {

        for (int i = 1; i < lines.size(); i++) { // empieza en 1, se salta el encabezado (índice 0)
            String line = lines.get(i);
            String[] data = line.split(",");

            String customerId = data[1];
            String firstName = data[2];
            String lastName = data[3];
            String company = data[4];
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

    public void loadCustomerFromFile(String fileName) throws IOException {
        ArrayList<String> lines = CSVFileManagerControl.readLine(fileName);
        addCustomerFromCSV(lines);
    }

}
