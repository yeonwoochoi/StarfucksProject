package com.beagle.java.projects.starfucks.repository.temporaryStorage;

import com.beagle.java.projects.starfucks.collection.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Food;

public class FoodDataList {

    private StarFucksList<Food> temporaryStorage;

    public StarFucksList<Food> getTemporaryStorage() {
            return this.temporaryStorage;
        }

    public void setTemporaryStorage(StarFucksList<Food> temporaryStorage) {
        this.temporaryStorage = temporaryStorage;
    }
}
