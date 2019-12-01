package com.beagle.java.projects.starfucks.repository.temporaryStorage;

import com.beagle.java.projects.starfucks.collection.StarFucksList;
import com.beagle.java.projects.starfucks.domain.User;

public class UserDataList {
    private StarFucksList<User> temporaryStorage;

    public StarFucksList<User> getTemporaryStorage() {
        return this.temporaryStorage;
    }

    public void setTemporaryStorage(StarFucksList<User> temporaryStorage) {
        this.temporaryStorage = temporaryStorage;
    }
}
