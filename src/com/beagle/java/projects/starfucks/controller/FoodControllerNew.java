package com.beagle.java.projects.starfucks.controller;

public class FoodControllerNew {
    private static FoodControllerNew instance = new FoodControllerNew();

    private FoodControllerNew() {}
    public static FoodControllerNew getInstance() {
        if (instance == null) {
            instance = new FoodControllerNew();
        }
        return instance;
    }






}
