package com.beagle.java.projects.starfucks.domain;


/**
 * a frame of information about barista data
 * barista data is stored in a text file in the form of a string returned by the toString method.
 * @author Beagle
 */
public class Barista {
    private String  baristaIndex;
    private String orderCount;

    public Barista(String baristaIndex, String orderCount) {
        this.baristaIndex = baristaIndex;
        this.orderCount = orderCount;
    }

    public String getBaristaIndex() {
        return this.baristaIndex;
    }

    public String getOrderCount() {
        return this.orderCount;
    }

    public void setBaristaIndex(String baristaIndex) {
        this.baristaIndex = baristaIndex;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    /**
     * Data type to be stored in BaristaRepository.txt
     * @return (String) user data in form of "barista index + "/" + order count + ";""
     */
    @Override
    public String toString() {
        return getBaristaIndex() + "/" + getOrderCount() + ";";
    }
}
