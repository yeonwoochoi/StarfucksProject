package com.beagle.java.projects.starfucks.controller;

import com.beagle.java.projects.starfucks.collection.StarFucksList;
import com.beagle.java.projects.starfucks.domain.User;
import com.beagle.java.projects.starfucks.repository.temporaryStorage.UserDataList;
import com.beagle.java.projects.starfucks.service.UserService;


/**
 * create and process user data by receiving order number created when order is placed and barista index assigned to order
 * Implemented in singleton pattern and will call in Manager class
 * @see com.beagle.java.projects.starfucks.service.UserService
 * @author Beagle
 */
public class UserController {

    private UserController() {}

    private static UserController userController = new UserController();
    public static UserController getInstance() {
        return userController;
    }



    UserDataList userDataList = new UserDataList();
    public StarFucksList<User> getTemporaryStorage() {
        return userDataList.getTemporaryStorage();
    }
    private void setTemporaryStorage(StarFucksList<User> input) {
        userDataList.setTemporaryStorage(input);
    }


    /**
     * create user data based on order information when order is placed and save in UserRepository.txt
     * @param baristaIndex
     * @param waitingTime
     * @return (String) Created order number, data retrieved from OrderNumberRepository.txt
     */
    public String getOrders(String baristaIndex, String waitingTime) {
        UserService userService = UserService.getInstance();
        User newUser = userService.createUser(baristaIndex,waitingTime);
        String orderIndex = newUser.getOrderNumber();
        StarFucksList<User> userList = getTemporaryStorage();
        userList.addLast(newUser);
        setTemporaryStorage(userList);
        return orderIndex;
    }

    /**
     * when input order number has been processed
     * @param orderIndex
     */
    public void pickUpFood(String orderIndex) {
        UserService userService = UserService.getInstance();
        setTemporaryStorage(userService.updateUser(orderIndex));
    }


    /**
     * delete the corresponding user information from UserRepository.txt when the customer leaves the cafe
     * This method is not called from Manager class if the corresponding order has not been processed.
     * @param orderIndex  Order number of outgoing guest
     * @return (boolean) success
     */
    public String exitCafe(String orderIndex) {
        UserService userService = UserService.getInstance();
        User userList1 = userService.findUser(orderIndex);
        if (userList1 == null) {
            return "이미 카페를 떠나셨습니다.";
        } else {
            if(userList1.isHoldingBell().equals("O")) {
                return "아직 음료가 나오지 않았습니다. 조금만 기다려 주십시오.";
            } else {
                setTemporaryStorage(userService.deleteUser(orderIndex));
                return "안녕히 가십시오.";
            }
        }
    }

    /**
     * Returns a String that represents the list of unprocessed orders.
     * @return (String) represents the list of unprocessed orders.
     */
    public String showUnprocessedOrder () {
        UserService userService = UserService.getInstance();
        StarFucksList<User> unprocessedUserList = userService.readUser();
        String output = "";
        for (int i = 0; i < unprocessedUserList.size(); i++) {
            User eachUser = unprocessedUserList.get(i);
            output += "주문 번호 : " + eachUser.getOrderNumber() + "   바리스타 : " + eachUser.getBaristaIndex() + "   대기 시간 : " + eachUser.getWaitingTime() + "   진동벨 소유 : " + eachUser.isHoldingBell() + "\n";
        }
        return output;
    }


    public String  readAllUser() {
        StarFucksList<User> userListInCafe =  getTemporaryStorage();
        String output = "";
        for (int i = 0; i < userListInCafe.size(); i++) {
            User eachUser = userListInCafe.get(i);
            output += "주문 번호 : " + eachUser.getOrderNumber() + "   바리스타 : " + eachUser.getBaristaIndex() + "   대기 시간 : " + eachUser.getWaitingTime() + "   진동벨 소유 : " + eachUser.isHoldingBell() + "\n";
        }
        return output;
    }



    public void start() {
        UserService userService = UserService.getInstance();
        setTemporaryStorage(userService.start());
    }

    public void end() {
        UserService userService = UserService.getInstance();
        userService.end();
    }
}
