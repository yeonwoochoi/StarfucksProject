package com.beagle.java.projects.starfucks.service;

import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.controller.CustomerController;
import com.beagle.java.projects.starfucks.domain.Customer;


public class CustomerService {

    CustomerController customerController = CustomerController.getInstance();


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

}
