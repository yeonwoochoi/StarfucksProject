package com.beagle.java.projects.starfucks.controller;


import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Customer;
import com.beagle.java.projects.starfucks.repository.database.CustomerDataList;
import com.beagle.java.projects.starfucks.service.CustomerService;

public class CustomerController {

    // Variable that temporarily stores data in StarFucksList form while the program is running.


    private CustomerController() {}

    private static CustomerController customerController = new CustomerController();
    public static CustomerController getInstance() {
        return customerController;
    }




    CustomerDataList customerDataList = new CustomerDataList();
    public StarFucksList<Customer> getTemporaryStorage() {
        return customerDataList.getTemporaryStorage();
    }
    private void setTemporaryStorage(StarFucksList<Customer> input) {customerDataList.setTemporaryStorage(input);}







    public void insertCustomerController (Customer input) {
        CustomerService customerService = CustomerService.getInstance();
        StarFucksList<Customer> newList = customerService.insertCustomerService(input);
        setTemporaryStorage(newList);
    }

    public Customer readCustomerController (String id) {
        CustomerService customerService = CustomerService.getInstance();
        Customer findCustomer = customerService.readCustomerService(id);
        return findCustomer;
    }

    public void updateCustomerController (String id, Customer input) {
        CustomerService customerService = CustomerService.getInstance();
        StarFucksList<Customer> newList = customerService.updateCustomerService(id, input);
        setTemporaryStorage(newList);
    }

    public void deleteCustomerController (String id) {
        CustomerService customerService = CustomerService.getInstance();
        StarFucksList<Customer> newList = customerService.deleteCustomerService(id);
        setTemporaryStorage(newList);
    }


    public void start() {
        CustomerService customerService = CustomerService.getInstance();
        setTemporaryStorage(customerService.start());
    }

    public void end() {
        CustomerService customerService = CustomerService.getInstance();
        customerService.end();
    }

}
