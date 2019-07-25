package components.controllers.db_proj1;

import model.customer_db.Customer;
import model.customer_db.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/async")
public class CustomerAjax {
    private static Logger console = LoggerFactory.getLogger(CustomerAjax.class);
    @Autowired
    private CustomerService customerService;

    /*
    //Old way
    @PostMapping("/add")
    public List<Customer> addCustomer(@RequestParam String first_name, @RequestParam String last_name,
                                      @RequestParam String email){
        console.info(first_name+" "+last_name+" "+email);
        return null;
    }*/
    @PostMapping("/add")
    public List<Customer> addCustomer(@RequestBody Customer customer){
        console.info(customer.toString());
        customerService.saveCustomer(customer);
        return customerService.getCustomers();
    }
}
