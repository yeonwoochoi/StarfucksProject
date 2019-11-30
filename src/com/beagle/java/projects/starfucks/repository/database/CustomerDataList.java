package com.beagle.java.projects.starfucks.repository.database;

import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Customer;

public class CustomerDataList {
    private StarFucksList<Customer> temporaryStorage;

    public StarFucksList<Customer> getTemporaryStorage() {
        return this.temporaryStorage;
    }

    public void setTemporaryStorage(StarFucksList<Customer> temporaryStorage) {
        this.temporaryStorage = temporaryStorage;
    }
}
