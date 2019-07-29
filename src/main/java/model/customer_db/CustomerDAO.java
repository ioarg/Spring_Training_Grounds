package model.customer_db;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getCustomers();
    Customer getCustomer(int id);
    void saveCustomer(Customer customer);
    List<Customer> deleteCustomer(int id);
}
