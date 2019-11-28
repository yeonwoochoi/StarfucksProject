package com.beagle.java.projects.starfucks.controller;

import com.beagle.java.projects.starfucks.service.UserService;



/**
 * create and process user data by receiving order number created when order is placed and barista index assigned to order
 * Implemented in singleton pattern and will call in Manager class
 * @see com.beagle.java.projects.starfucks.service.UserService
 * @author Beagle
 */
public class UserController {

    UserService userService = new UserService();


    private static UserController userController = new UserController();
    private UserController() {}
    public static UserController getInstance() {
        return userController;
    }

    /**
     * create user data based on order information when order is placed and save in UserRepository.txt
     * @param baristaIndex
     * @param waitingTime
     * @return (String) Created order number, data retrieved from OrderNumberRepository.txt
     */
    public String getOrders(String baristaIndex, String waitingTime) {
        String orderIndex = userService.createUser(baristaIndex,waitingTime);
        return orderIndex;
    }

    /**
     * when input order number has been processed
     * @param orderIndex
     * @return (boolean) success
     */
    public boolean pickUpFood(String orderIndex) {
        return userService.updateUser(orderIndex);
    }


    /**
     * delete the corresponding user information from UserRepository.txt when the customer leaves the cafe
     * This method is not called from Manager class if the corresponding order has not been processed.
     * @param orderIndex  Order number of outgoing guest
     * @return (boolean) success
     */
    public boolean exitCafe(String orderIndex) {
        return userService.deleteUser(orderIndex);
    }

    /**
     * Returns a String that represents the list of unprocessed orders.
     * @return (String) represents the list of unprocessed orders.
     */
    public String showUnprocessedOrder () {
        return userService.readUser();
    }
}
