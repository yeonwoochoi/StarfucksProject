package com.beagle.java.projects.starfucks.domain;


/**
 * a frame of information about user data
 * User data is stored in a text file in the form of a string returned by the toString method.
 * @author Beagle
 */
public class User {

    private int orderNumber;
    private int baristaIndex;
    private int waitingTime;
    private boolean holdingBell;


    public User (int orderNumber, int baristaIndex, int waitingTime, boolean holdingBell) {
        this.orderNumber = orderNumber;
        this.baristaIndex = baristaIndex;
        this.waitingTime = waitingTime;
        this.holdingBell = holdingBell;
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    public int getBaristaIndex() { return this.baristaIndex; }

    public int getWaitingTime() {
        return this.waitingTime;
    }

    public boolean isHoldingBell() {
        return this.holdingBell;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setBaristaIndex(int baristaIndex) {
        this.baristaIndex = baristaIndex;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setHoldingBell(boolean holdingBell) {
        this.holdingBell = holdingBell;
    }

    /**
     * Data type to be stored in UserRepository.txt
     * @return (String) user data in form of "order number + "/" + barista index() + "/" + waiting time + "/" + is holding bell + ";""
     */
    @Override
    public String toString() {
        return getOrderNumber() + "/" + getBaristaIndex() + "/" + getWaitingTime() + "/" + isHoldingBell() + ";";
    }
}
