package com.beagle.java.projects.starfucks.domain;

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
}
