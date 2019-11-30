package com.beagle.java.projects.starfucks.repository.database;


import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Barista;

public class BaristaDataList {

    private StarFucksList<Barista> temporaryStorage;

    public StarFucksList<Barista> getTemporaryStorage() {
        return this.temporaryStorage;
    }

    public void setTemporaryStorage(StarFucksList<Barista> temporaryStorage) {
        this.temporaryStorage = temporaryStorage;
    }

}
