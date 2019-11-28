package com.beagle.java.projects.starfucks.service;


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
    UserRepository userRepository = new UserRepository();



    public String createUser(String baristaIndex, String waitingTime) {
        String orderNumber = orderNumberRepository.readOrderNumber();
        String output = orderNumber + "/" + baristaIndex + "/" + waitingTime + "/O;";
        userRepository.saveToUserRegistory(output);
        orderNumberRepository.updateOrderNumber();
        return orderNumber;
    }




    public boolean deleteUser(String orderNumber) {
        String[] userDataArr = userRepository.readAllUserData();
        String[] eachArr;
        boolean success = true;
        String input = "";

        if (userDataArr.length > 0) {
            for (int i = 0; i < userDataArr.length; i++) {
                eachArr = userDataArr[i].split("/");
                if (eachArr[0].equals(orderNumber)) {
                    input += eachArr[0] + "/" + eachArr[1] + "/" + eachArr[2] + "/";
                }
                if (eachArr[3].equals("O")) {
                    success = false;
                }
            }
            if (success) {
                userRepository.deleteUserData(input);
                return success;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }



    public boolean updateUser(String orderNumber) {

        String[] userDataArr = userRepository.readAllUserData();
        String[] eachArr;
        String baristaIndex = "";
        String waitingTime = "";
        for (int i = 0; i < userDataArr.length; i++) {
            eachArr = userDataArr[i].split("/");
            if (eachArr[0].equals(orderNumber)) {
                baristaIndex = eachArr[1];
                waitingTime = eachArr[2];
            }
        }


        boolean run = true;
        int i = 0;
        String inputData = "";
        while (run) {
            eachArr = userDataArr[i].split("/");
            if (eachArr[0].equals(orderNumber) && eachArr[1].equals(baristaIndex) && eachArr[2].equals(waitingTime) && eachArr[3].equals("O")) {
                inputData += eachArr[0] + "/" + eachArr[1] + "/" + eachArr[2] + "/";
                run = false;
            }
            if (i == (userDataArr.length-1)) {
                run = false;
            }
            i++;
        }
        String oldData = inputData + "O;";
        String newData = inputData + "X;";
        return userRepository.updateUserRepository(oldData, newData);


    }




    public String readUser () {
        String[] userDataArr = userRepository.readAllUserData();
        String[] eachArr;
        String output = "\n";
        for (int i = 0; i < userDataArr.length; i++) {
            eachArr = userDataArr[i].split("/");
            if (eachArr[3].equals("O")) {
                output += "주문 번호 : " + eachArr[0] + "  대기 시간 : " + eachArr[2] + "\n";
            }
        }
        return output;
    }




}
