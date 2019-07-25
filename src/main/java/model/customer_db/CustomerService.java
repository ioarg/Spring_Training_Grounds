package model.customer_db;

import java.util.List;


public interface CustomerService {
    public List<Customer> getCustomers();
    public void saveCustomer(Customer customer);
}
