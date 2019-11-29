package com.beagle.java.projects.starfucks.service;

import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Customer;



public class CustomerService {


    public StarFucksList<Customer> insertCustomerService(StarFucksList<Customer> temporaryStorage, Customer inputClass) {
        StarFucksList<Customer> newList = temporaryStorage;
        newList.addLast(inputClass);
        return newList;
    }


    public StarFucksList<Customer> updateCustomerService(StarFucksList<Customer> temporaryStorage, String id, Customer newData) {
        StarFucksList<Customer> newList = temporaryStorage;
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i).getId().equals(id)) {
                newList.add(i, newData);
                newList.remove(i+1);
            }
        }
        return newList;
    }

    public StarFucksList<Customer> deleteCustomerService(StarFucksList<Customer> temporaryStorage, String id) {
        StarFucksList<Customer> newList = temporaryStorage;
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i).getId().equals(id)) {
                newList.remove(i);
            }
        }
        return newList;
    }

    public StarFucksList<Customer> readCustomerService(StarFucksList<Customer> input) {
        return input;
    }


}
