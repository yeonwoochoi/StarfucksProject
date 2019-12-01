package com.beagle.java.projects.starfucks.service;


import com.beagle.java.projects.starfucks.collection.StarFucksList;
import com.beagle.java.projects.starfucks.controller.UserController;
import com.beagle.java.projects.starfucks.domain.User;
import com.beagle.java.projects.starfucks.repository.OrderNumberRepository;
import com.beagle.java.projects.starfucks.repository.UserRepository;


/**
 * CRUD user data stored in UserRepository.txt
 * @see com.beagle.java.projects.starfucks.repository.UserRepository
 * @see com.beagle.java.projects.starfucks.repository.OrderNumberRepository
 * @author Beagle
 */
public class UserService {

    OrderNumberRepository orderNumberRepository = new OrderNumberRepository();
    public UserService () {}

    private static UserService userService = new UserService();
    public static UserService getInstance() {return userService;}


    public User createUser(String baristaIndex, String waitingTime) {
        String orderNumber = orderNumberRepository.readOrderNumber();
        User outputUser = new User(orderNumber, baristaIndex, waitingTime, "O");
        orderNumberRepository.updateOrderNumber();
        return outputUser;
    }


    public StarFucksList<User> updateUser(String orderIndex) {
        UserController userController = UserController.getInstance();
        StarFucksList<User> userList = userController.getTemporaryStorage();
        for (int i = 0; i < userList.size(); i++) {
            User oldData = userList.get(i);
            if (orderIndex.equals(oldData.getOrderNumber())) {
                userList.remove(i);
                User newData = new User(oldData.getOrderNumber(), oldData.getBaristaIndex(), oldData.getWaitingTime(), "X");
                userList.add(i, newData);
            }
        }
        return userList;
    }

    public StarFucksList<User> deleteUser(String orderIndex) {
        UserController userController = UserController.getInstance();
        StarFucksList<User> userList = userController.getTemporaryStorage();
        for (int i = 0; i < userList.size(); i++) {
            User oldData = userList.get(i);
            if (orderIndex.equals(oldData.getOrderNumber())) {
                userList.remove(i);
            }
        }
        return userList;
    }

    public StarFucksList<User> readUser() {
        UserController userController = UserController.getInstance();
        StarFucksList<User> userList = userController.getTemporaryStorage();
        StarFucksList<User> unprocessedUserList = new StarFucksList<User>();
        for (int i = 0; i < userList.size(); i++) {
            User oldData = userList.get(i);
            if (oldData.isHoldingBell().equals("O")) {
                unprocessedUserList.addLast(oldData);
            }
        }
        return unprocessedUserList;

    }

    public User findUser(String orderNumber) {
        UserController userController = UserController.getInstance();
        StarFucksList<User> userList = userController.getTemporaryStorage();
        User targetData = null;
        for (int i = 0; i < userList.size(); i++) {
            User oldData = userList.get(i);
            if (oldData.getOrderNumber().equals(orderNumber)) {
                targetData = oldData;
            }
        }
        return targetData;
    }



    public StarFucksList<User> start() {
        UserRepository userRepository = new UserRepository();
        return userRepository.readAllUserData();
    }

    public void end() {
        UserController userController = UserController.getInstance();
        StarFucksList<User> finalData = userController.getTemporaryStorage();
        UserRepository userRepository = new UserRepository();
        userRepository.deleteUserData();
        userRepository.saveToUserRegistory(finalData);
    }
}
