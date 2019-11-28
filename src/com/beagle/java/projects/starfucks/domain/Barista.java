package com.beagle.java.projects.starfucks.domain;


/**
 * a frame of information about barista data
 * barista data is stored in a text file in the form of a string returned by the toString method.
 * @author Beagle
 */
public class Barista {
    private int baristaIndex;
    private int orderCount;

    public Barista(int baristaIndex, int orderCount) {
        this.baristaIndex = baristaIndex;
        this.orderCount = orderCount;
    }

    public int getBaristaIndex() {
        return this.baristaIndex;
    }

    public int getOrderCount() {
        return this.orderCount;
    }

    public void setBaristaIndex(int baristaIndex) {
        this.baristaIndex = baristaIndex;
    }

    public void setOrderCount(int orderCount) {
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
