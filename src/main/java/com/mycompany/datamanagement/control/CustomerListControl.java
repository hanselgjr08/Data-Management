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
    
    public void addCustomerFromCSV(String ruta) throws IOException {
    ArrayList<String> lines = CSVFileManagerControl.readLine(ruta);

    for (int i = 1; i < lines.size(); i++) { // empieza en 1, se salta el encabezado (índice 0)
        String line = lines.get(i);
        String[] datos = line.split(",");

        int customerId = Integer.parseInt(datos[1]);
        String firstName = datos[2];
        String lastName = datos[3];
        String company = datos[4];
        String city = datos[5];
        String country = datos[6];
        String phone1 = datos[7];
        String phone2 = datos[8];
        String email = datos[9];
        String subscriptionDate = datos[10];
        String website = datos[11];

        CustomerModel customer = new CustomerModel(
            customerId, firstName, lastName, company,
            city, country, phone1, phone2, email,
            subscriptionDate, website
        );

        customerListModel.addCustomer(customer);
    }
}
    
}
   