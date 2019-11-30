package com.beagle.java.projects.starfucks;

import com.beagle.java.projects.starfucks.controller.BaristaController;
import com.beagle.java.projects.starfucks.controller.CustomerController;
import com.beagle.java.projects.starfucks.controller.FoodController;
import com.beagle.java.projects.starfucks.controller.UserController;
import com.beagle.java.projects.starfucks.domain.Customer;

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
    public void getOrders(String[] foodIndexes, String[] foodCounts) {


        // check input data adequacy
        if (checkInputArray(foodIndexes) && checkInputArray(foodCounts)) {
            String receipt = foodController.makeReceipt(foodIndexes, foodCounts);
            System.out.println("-- receipt --\n" + receipt);

            // get total waiting time and total price
            String[] foodDataArr = foodController.calculateFoodData(foodIndexes, foodCounts);
            String totalWaitingTimeStr = foodDataArr[0];
            int totalWaitingTime = stringToInt(totalWaitingTimeStr);
            String totalPriceStr = foodDataArr[1];


            // give order to barista and get barista index
            String baristaIndex = baristaController.getOrderToBarista();
            System.out.println(baristaIndex);


            // get order from user and store data in UserRepository.txt
            String orderIndex = userController.getOrders(baristaIndex, totalWaitingTimeStr);


            // recover vibration bell and recover order from barista when waiting time is reached
            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("주문번호 " + orderIndex + " 고객님. 주문하신 음료와 디저트 나왔습니다.");
                    baristaController.finishOrderFromBarista(baristaIndex);
                    userController.pickUpFood(orderIndex);
                }
            };
            timer.schedule(timerTask, totalWaitingTime * 1000);

        } else {
            System.out.println("입력된 값들 중에 null 값이 있습니다.");
        }
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

    /**
     * Called when a guest leaves the cafe, all data about the user is deleted.
     * prevents user from leaving the cafe if the user's order has not been processed.
     * @param orderNumber Order number of the user trying to leave
     * @return (String) comment about situation
     */
    public String leavingCafe (String orderNumber) {
        if (checkInputData(orderNumber)) {
            if(userController.exitCafe(orderNumber)) {
                return "안녕히 가십시오.";
            } else {
                return "아직 음료가 나오지 않았습니다. 조금만 기다려 주십시오.";
            }
        } else {
            return "값을 입력해 주십시오.";
        }
    }


    public String signUpCustomer(String id, String name, String phoneNumber, String email) {
        CustomerController customerController = CustomerController.getInstance();
        Customer findCustomer = customerController.readCustomerController(id);
        if (findCustomer == null) {
            Customer input = new Customer(id, name, phoneNumber, email);
            customerController.insertCustomerController(input);
            return "회원가입 성공";
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
            String output = "id  :  " + findCustomer.getId() + "\nname  :  " + findCustomer.getName() + "\nphone number  :  " + findCustomer.getPhoneNumber() + "\nemail  :  " + findCustomer.getEmail();
            return output;
        }
    }

    public String editCustomerData(String id, String name, String phoneNumber, String email) {
        CustomerController customerController = CustomerController.getInstance();
        Customer oldData = customerController.readCustomerController(id);
        Customer newData = new Customer(id, name, phoneNumber, email);

        if (oldData.equals(newData)) {
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
        customerController.start();
    }


    public void endOfProgram() {
        CustomerController customerController = CustomerController.getInstance();
        customerController.end();
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
