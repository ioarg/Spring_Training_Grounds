package components.controllers.db_proj1;

import model.customer_db.Customer;
import model.customer_db.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerDAO customerDAO;

    @GetMapping("")
    public String customerHomePage(Model model){
        List<Customer> customers = customerDAO.getCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }
}
