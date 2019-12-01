package com.beagle.java.projects.starfucks.domain;


/**
 * a frame of information about user data
 * User data is stored in a text file in the form of a string returned by the toString method.
 * @author Beagle
 */
public class User {
    private String orderNumber;
    private String baristaIndex;
    private String waitingTime;
    private String holdingBell;


    public User (String orderNumber, String baristaIndex, String waitingTime, String holdingBell) {
        this.orderNumber = orderNumber;
        this.baristaIndex = baristaIndex;
        this.waitingTime = waitingTime;
        this.holdingBell = holdingBell;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public String getBaristaIndex() { return this.baristaIndex; }

    public String getWaitingTime() {
        return this.waitingTime;
    }

    public String isHoldingBell() {
        return this.holdingBell;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setBaristaIndex(String baristaIndex) {
        this.baristaIndex = baristaIndex;
    }

    public void setWaitingTime(String waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void setHoldingBell(String holdingBell) {
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
