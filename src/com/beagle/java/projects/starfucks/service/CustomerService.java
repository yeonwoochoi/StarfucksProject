package com.beagle.java.projects.starfucks.service;

import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Customer;
import com.beagle.java.projects.starfucks.repository.CustomerRepository;

public class CustomerService {
    
    // Variable that temporarily stores data in StarFucksList form while the program is running.
    CustomerRepository customerRepository = new CustomerRepository();
    private StarFucksList<Customer> temporaryStorage = customerRepository.readAllCustomer();

    public StarFucksList<Customer> getTemporaryStorage() {
        return this.temporaryStorage;
    }

    private void setTemporaryStorage(StarFucksList<Customer> temporaryStorage) {
        this.temporaryStorage = temporaryStorage;
    }

    public void insertCustomerData(Customer inputClass) {
        StarFucksList<Customer> newList = getTemporaryStorage();
        newList.addLast(inputClass);
        setTemporaryStorage(newList);
    }


    public void updateCustomerData(String id, Customer newData) {
        StarFucksList<Customer> newList = getTemporaryStorage();
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i).getId().equals(id)) {
                newList.add(i, newData);
                newList.remove(i+1);
            }
        }
        setTemporaryStorage(newList);
    }


}
