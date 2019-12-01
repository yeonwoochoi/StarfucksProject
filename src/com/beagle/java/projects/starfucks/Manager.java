package com.beagle.java.projects.starfucks;

import com.beagle.java.projects.starfucks.collection.StarFucksList;
import com.beagle.java.projects.starfucks.controller.BaristaController;
import com.beagle.java.projects.starfucks.controller.CustomerController;
import com.beagle.java.projects.starfucks.controller.FoodController;
import com.beagle.java.projects.starfucks.controller.UserController;
import com.beagle.java.projects.starfucks.domain.Customer;
import com.beagle.java.projects.starfucks.domain.User;

import java.util.Timer;
import java.util.TimerTask;

import static com.beagle.java.projects.starfucks.utils.Utils.stringToInt;


/**
 * combines all the classes in the controller package and the methods in it and handles the input data from the Main class accordingly.
 * @see com.beagle.java.projects.starfucks.controller.BaristaController
 * @see com.beagle.java.projects.starfucks.controller.FoodController
 * @see com.beagle.java.projects.starfucks.controller.UserController
 * @author Beagle
 */
public class Manager {

    BaristaController baristaController = BaristaController.getInstance();
    FoodController foodController = FoodController.getInstance();
    UserController userController = UserController.getInstance();
    CustomerController customerController = CustomerController.getInstance();


    /**
     * process order information when order is placed
     * @param foodIndexes Array of indexes of food ordered by a guest
     * @param foodCounts Array of counts of food ordered by a guest
     */
    public void getOrders(StarFucksList<String> foodIndexes, StarFucksList<String> foodCounts) {


        String receipt = foodController.makeReceipt(foodIndexes, foodCounts);
        System.out.println("-- receipt --\n" + receipt);

        // get total waiting time and total price
        StarFucksList<String> foodDataArr = foodController.calculateFoodData(foodIndexes, foodCounts);
        String totalWaitingTimeStr = foodDataArr.get(1);
        int totalWaitingTime = stringToInt(totalWaitingTimeStr);
        System.out.println(totalWaitingTime);


        // give order to barista and get barista index
        String baristaIndex = baristaController.getOrder();


        // get order from user and store data in UserRepository.txt
        String orderIndex = userController.getOrders(baristaIndex, totalWaitingTimeStr);


        // recover vibration bell and recover order from barista when waiting time is reached
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("주문번호 " + orderIndex + " 고객님. 주문하신 음료와 디저트 나왔습니다.");
                baristaController.finishOrder(baristaIndex);
                userController.pickUpFood(orderIndex);
            }
        };
        timer.schedule(timerTask, totalWaitingTime * 1000);
    }

    public String checkBarista() {
        return baristaController.checkBarsitaState();
    }


    /**
     * showing menu list at once
     * @return (String) menu
     */
    public String showMenu() {
        return foodController.showMenuList();
    }

    /**
     * showing unprocessed orders
     * @return (String) unprocessed orders
     */
    public String showOrderList() {
        return userController.showUnprocessedOrder();
    }

    public String showUserInCafe() {
        return userController.readAllUser();
    }


    /**
     * Called when a guest leaves the cafe, all data about the user is deleted.
     * prevents user from leaving the cafe if the user's order has not been processed.
     * @param orderNumber Order number of the user trying to leave
     * @return (String) comment about situation
     */
    public String leavingCafe (String orderNumber) {
        if (orderNumber != null) {
            return userController.exitCafe(orderNumber);
        } else {
            return "값을 입력해 주십시오.";
        }
    }


    public String signUpCustomer(String id, String name, String phoneNumber, String email) {
        CustomerController customerController = CustomerController.getInstance();
        Customer findCustomer = customerController.readCustomerController(id);
        if (findCustomer == null) {
            if (id.contains("/") || id.contains(";")){
                return "적절하지 않은 id 값입니다.";
            } else if (name.contains("/") || name.contains(";")) {
                return "적절하지 않은 name 값입니다.";
            } else if (phoneNumber.contains("/") || phoneNumber.contains(";")) {
                return "적절하지 않은 phone number 값입니다.";
            } else if (email.contains("/") || email.contains(";")) {
                return "적절하지 않은 email 값입니다.";
            } else {
                Customer input = new Customer(id, name, phoneNumber, email);
                customerController.insertCustomerController(input);
                return "회원가입 성공";
            }
        } else {
            return "이미 존재하는 아이디입니다.";
        }
    }

    public String viewCustomerData(String id) {
        CustomerController customerController = CustomerController.getInstance();
        Customer findCustomer = customerController.readCustomerController(id);
        if (findCustomer == null) {
            return "존재하지 않는 아이디입니다";
        } else {
            if (id.contains("/") || id.contains(";")){
                return "적절하지 않은 id 값입니다.";
            } else if (findCustomer.getName().contains("/") || findCustomer.getName().contains(";")) {
                return "적절하지 않은 name 값입니다.";
            } else if (findCustomer.getPhoneNumber().contains("/") || findCustomer.getPhoneNumber().contains(";")) {
                return "적절하지 않은 phone number 값입니다.";
            } else if (findCustomer.getEmail().contains("/") || findCustomer.getEmail().contains(";")) {
                return "적절하지 않은 email 값입니다.";
            } else {
                String output = "id  :  " + findCustomer.getId() + "\nname  :  " + findCustomer.getName() + "\nphone number  :  " + findCustomer.getPhoneNumber() + "\nemail  :  " + findCustomer.getEmail();
                return output;
            }

        }
    }

    public String editCustomerData(String id, String name, String phoneNumber, String email) {
        CustomerController customerController = CustomerController.getInstance();
        Customer oldData = customerController.readCustomerController(id);
        Customer newData = new Customer(id, name, phoneNumber, email);

        if (oldData.getName().equals(newData.getName()) && oldData.getPhoneNumber().equals(newData.getPhoneNumber()) && oldData.getEmail().equals(newData.getEmail())) {
            return "이전의 정보와 똑같습니다.";
        } else {
            customerController.updateCustomerController(id, newData);
            return "회원 정보 수정 완료";
        }
    }

    public String withdraw(String id) {
        CustomerController customerController = CustomerController.getInstance();
        Customer findData = customerController.readCustomerController(id);
        if (findData != null) {
            customerController.deleteCustomerController(id);
            return "회원 탈퇴 완료";
        } else {
            return "존재하지 않은 회원입니다";
        }
    }


    public void startProgram() {
        CustomerController customerController = CustomerController.getInstance();
        BaristaController baristaController = BaristaController.getInstance();
        FoodController foodController = FoodController.getInstance();
        UserController userController = UserController.getInstance();
        customerController.start();
        baristaController.start();
        foodController.start();
        userController.start();
    }


    public void endOfProgram() {
        CustomerController customerController = CustomerController.getInstance();
        BaristaController baristaController = BaristaController.getInstance();
        UserController userController = UserController.getInstance();
        customerController.end();
        baristaController.end();
        userController.end();
    }





    /**
     * handle if the ordered food index is not in the menu
     * handle whether the order quantity is positive or not
     * @param input1 food index
     * @param input2 food count
     * @return (boolean) success
     */
    public boolean checkInputFoodNumber (int input1, int input2) {
        boolean success1 = false;
        boolean success2 = false;
        if (1<= input1 && input1 <= 20) {
            success1 = true;
        }
        if (input2 > 0) {
            success2 = true;
        }
        if (success1 && success2) {
            return true;
        } else {
            return false;
        }
      }

    /**
     * check if input data is null or not
     * @param inputStr
     * @return (boolean) success
     */
    private static boolean checkInputData(String inputStr) {
        if (inputStr == null) {
            return false;
        } else {
            return true;
        }
    }





    /**
     * check if input array is an array containing null values
     * @param inputArr
     * @return (boolean) success
     */
    private static boolean checkInputArray (String[] inputArr) {
        int check = 0;
        boolean success;
        for (int i =0; i < inputArr.length; i++) {
            if (!checkInputData(inputArr[i])){
                check += 1;
            }
        }
        if (check > 0) {
            success = false;
        } else {
            success = true;
        }
        return success;
    }

}
