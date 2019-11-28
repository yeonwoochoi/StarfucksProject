package com.beagle.java.projects.starfucks.domain;

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

}
