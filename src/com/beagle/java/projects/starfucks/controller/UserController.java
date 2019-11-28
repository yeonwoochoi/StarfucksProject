package com.beagle.java.projects.starfucks.controller;

import com.beagle.java.projects.starfucks.service.UserService;


public class UserController {

    UserService userService = new UserService();

    public String getOrders(String baristaIndex, String waitingTime) {
        String orderIndex = userService.createUser(baristaIndex,waitingTime);
        return orderIndex;
    }

    public boolean pickUpFood(String orderIndex) {
        return userService.updateUser(orderIndex);
    }

    public boolean exitCafe(String orderIndex) {
        return userService.deleteUser(orderIndex);
    }

    public String showUnprocessedOrder () {
        return userService.readUser();
    }
}
