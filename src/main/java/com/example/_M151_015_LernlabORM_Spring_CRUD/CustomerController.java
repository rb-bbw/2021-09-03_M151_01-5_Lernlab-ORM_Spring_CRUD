package com.example._M151_015_LernlabORM_Spring_CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    // Field - Inject with Dependency Injection the repository
    @Autowired
    CustomerRepository customerRepository;
    // Constructor Injection
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @GetMapping("/signup")
    public String showSignUpForm(Customer customer) {
        return "add-customer";
    }

    @PostMapping("/addcustomer")
    public String addCustomer(Customer customer, BindingResult result, Model model) { // @Valid
        if (result.hasErrors()) {
            return "add-customer";
        }

        customerRepository.save(customer);
        model.addAttribute("customers", customerRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));

        model.addAttribute("customer", customer);
        return "update-customer";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable("id") long id, Customer customer, //@ValidUser
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            //customer.setId(id);
            return "update-customer";
        }

        customerRepository.save(customer);
        model.addAttribute("customers", customerRepository.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") long id, Model model) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
        customerRepository.delete(customer);
        model.addAttribute("customers", customerRepository.findAll());
        return "index";
    }
}