package com.beagle.java.projects.starfucks.service;

import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.controller.CustomerController;
import com.beagle.java.projects.starfucks.domain.Customer;
import com.beagle.java.projects.starfucks.repository.CustomerRepository;


public class CustomerService {

    CustomerController customerController = CustomerController.getInstance();
    CustomerRepository customerRepository = new CustomerRepository();


    public StarFucksList<Customer> insertCustomerService(Customer inputClass) {
        StarFucksList<Customer> newList =  customerController.getTemporaryStorage();
        newList.addLast(inputClass);
        return newList;
    }


    public StarFucksList<Customer> updateCustomerService(String id, Customer newData) {
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
        StarFucksList<Customer> newList =  customerController.getTemporaryStorage();
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i).getId().equals(id)) {
                output = newList.get(i);
            }
        }
        return output;
    }


    public void end() {
        StarFucksList<Customer> finalData = customerController.getTemporaryStorage();
        customerRepository.saveToCustomerRepository(finalData);
    }

}
