package com.beagle.java.projects.starfucks.service;

import com.beagle.java.projects.starfucks.collection.StarFucksList;
import com.beagle.java.projects.starfucks.controller.CustomerController;
import com.beagle.java.projects.starfucks.domain.Customer;
import com.beagle.java.projects.starfucks.repository.CustomerRepository;


public class CustomerService {

    public CustomerService () {}

    private static CustomerService customerService = new CustomerService();
    public static CustomerService getInstance() {return customerService;}



    public StarFucksList<Customer> insertCustomerService(Customer inputClass) {
        CustomerController customerController = CustomerController.getInstance();
        StarFucksList<Customer> newList =  customerController.getTemporaryStorage();
        if (newList == null) {
            StarFucksList<Customer> starFucksList = new StarFucksList();
            starFucksList.addFirst(inputClass);
            return starFucksList;
        } else {
            newList.addLast(inputClass);
            return newList;
        }
    }


    public StarFucksList<Customer> updateCustomerService(String id, Customer newData) {
        CustomerController customerController = CustomerController.getInstance();
        StarFucksList<Customer> newList =  customerController.getTemporaryStorage();
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i).getId().equals(id)) {
                newList.add(i, newData);
                newList.remove(i+1);
            }
        }
        return newList;
    }

    public StarFucksList<Customer> deleteCustomerService(String id) {
        CustomerController customerController = CustomerController.getInstance();
        StarFucksList<Customer> newList =  customerController.getTemporaryStorage();
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i).getId().equals(id)) {
                newList.remove(i);
            }
        }
        return newList;
    }

    public Customer readCustomerService(String id) {
        Customer output = null;
        try {
            CustomerController customerController = CustomerController.getInstance();
            StarFucksList<Customer> newList = customerController.getTemporaryStorage();
            if (customerController.getTemporaryStorage() == null) {
                output = null;
            } else {
                for (int i = 0; i < newList.size(); i++) {
                    if (newList.get(i).getId().equals(id)) {
                        output = newList.get(i);
                    }
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return output;
    }

    public StarFucksList<Customer> start() {
        CustomerRepository customerRepository = new CustomerRepository();
        return customerRepository.readAllCustomer();
    }

    public void end() {
        CustomerController customerController = CustomerController.getInstance();
        StarFucksList<Customer> finalData = customerController.getTemporaryStorage();
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.saveToCustomerRepository(finalData);
    }


}
