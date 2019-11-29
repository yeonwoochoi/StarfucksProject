package com.beagle.java.projects.starfucks.controller;


import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Customer;
import com.beagle.java.projects.starfucks.repository.CustomerRepository;
import com.beagle.java.projects.starfucks.service.CustomerService;

public class CustomerController {

    private static CustomerController customerController = new CustomerController();
    private CustomerController() {}
    public static CustomerController getInstance() {
        return customerController;
    }


    // Variable that temporarily stores data in StarFucksList form while the program is running.
    CustomerRepository customerRepository = new CustomerRepository();
    CustomerService customerService = new CustomerService();
    private StarFucksList<Customer> temporaryStorage = customerRepository.readAllCustomer();



    public StarFucksList<Customer> getTemporaryStorage() {
        return this.temporaryStorage;
    }

    private void setTemporaryStorage(StarFucksList<Customer> temporaryStorage) {
        this.temporaryStorage = temporaryStorage;
    }



    public void insertCustomerController (Customer input) {
        StarFucksList<Customer> newList = customerService.insertCustomerService(input);
        setTemporaryStorage(newList);
    }

    public Customer readCustomerController (String id) {
        Customer findCustomer = customerService.readCustomerService(id);
        return findCustomer;
    }

    public void updateCustomerController (String id, Customer input) {
        StarFucksList<Customer> storage = getTemporaryStorage();
        StarFucksList<Customer> newList = customerService.updateCustomerService(id, input);
        setTemporaryStorage(newList);
    }

    public void deleteCustomerController (String id) {
        StarFucksList<Customer> storage = getTemporaryStorage();
        StarFucksList<Customer> newList = customerService.deleteCustomerService(id);
        setTemporaryStorage(newList);
    }

}
