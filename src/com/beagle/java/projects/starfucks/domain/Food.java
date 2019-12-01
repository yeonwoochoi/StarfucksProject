package com.beagle.java.projects.starfucks.domain;

public class Food {
    private String foodIndex;
    private String foodName;
    private String foodPrice;
    private String consumedTime;

    public Food (String foodIndex, String foodName, String foodPrice, String consumedTime) {
        this.foodIndex = foodIndex;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.consumedTime = consumedTime;
    }


    public String getFoodIndex() {
        return foodIndex;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public String getConsumedTime() {
        return consumedTime;
    }

    public void setFoodIndex(String foodIndex) {
        this.foodIndex = foodIndex;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public void setConsumedTime(String consumedTime) {
        this.consumedTime = consumedTime;
    }

    @Override
    public String toString() {
        return getFoodIndex()+"/"+getFoodName()+"/"+getFoodPrice()+"/"+getConsumedTime()+";";
    }
}
