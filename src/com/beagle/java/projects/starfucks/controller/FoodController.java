package com.beagle.java.projects.starfucks.controller;


import com.beagle.java.projects.starfucks.repository.FoodRepository;
import com.beagle.java.projects.starfucks.service.FoodService;

import java.util.ArrayList;

import static com.beagle.java.projects.starfucks.utils.Utils.stringToInt;

public class FoodController {

    FoodRepository foodRepository = new FoodRepository();
    FoodService foodService = new FoodService();


    private static FoodController foodController = new FoodController();
    private FoodController() {}
    public static FoodController getInstance() {
        return foodController;
    }


    /**
     * Method that returns all data in FoodRepository.txt as string
     * @return String menu
     */
    public String showMenuList() {

        String beverageStr = "\n== beverage ==\n\n";
        String dessertStr = "== dessert ==\n";

        String foodStr = foodRepository.readAllFoodData();
        String[] foodArr = foodStr.split(";");
        String[] beverageArr;
        String[] dessertArr;
        for (int i = 0 ; i < 10; i++) {
            beverageArr = foodArr[i].split("/");
            for (int j = 0 ; j < beverageArr.length; j++) {
                beverageStr += beverageArr[j] + " ";
            }
            beverageStr += "\n";
        }
        beverageStr += "\n\n";

        for (int i = 10; i < foodArr.length; i++) {
            dessertArr = foodArr[i].split("/");
            for (int j = 0 ; j < dessertArr.length; j++) {
                dessertStr += dessertArr[j] + " ";
            }
            dessertStr += "\n";
        }
        dessertStr += "\n\n";


        String output = beverageStr + dessertStr;

        return output;
    }


    /**
     * Method to make receipt of input order
     * @param foodIndexArr
     * @param foodCountArr
     * @return (String) Converting the receipt to a string using the toString method of the Receipt class
     */
    public String makeReceipt(String[] foodIndexArr, String[] foodCountArr) {


        String[] foodDataArr = (foodRepository.readAllFoodData()).split(";");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < foodDataArr.length; i++) {
            String[] eachFoodDataArr = foodDataArr[i].split("/");
            for (int j = 0; j < foodIndexArr.length; j++) {
                if (eachFoodDataArr[0].equals(foodIndexArr[j])){
                    arrayList.add(eachFoodDataArr);
                }
            }
        }

        String priceStr = "";
        String nameStr = "";


        for (int i = 0; i < arrayList.size(); i++) {
            String[] eachArr = (String[]) arrayList.get(i);
            nameStr += eachArr[1] + "/";
            priceStr += eachArr[2] + "/";
        }


        String[] nameArrStr = nameStr.split("/");
        String[] priceArrStr = priceStr.split("/");
        String[] countArrStr = foodCountArr;


        // variable declaration for making receipt
        String output;
        String content = "";


        // Calculate the total price
        int total = 0;
        for (int i = 0; i < priceArrStr.length; i++) {
            total += (stringToInt(priceArrStr[i]) * stringToInt(countArrStr[i]));
        }

        // add receipt content
        for (int i = 0; i < nameArrStr.length; i++) {
            content += nameArrStr[i] + "  " + priceArrStr[i] + "  " + countArrStr[i] + "\n";
        }

        output = "\n\n== receipt ==\n" + content + "\n" + "total :  " + total + "\n\n";


        return output;

    }


    public String[] calculateFoodData(String[] foodIndex, String[] foodCount) {
        String[] outputArr = new String[2];

        if (foodIndex.length > 0 && foodCount.length > 0) {

            String totalWaitingTimeStr = foodService.calculateWaitingTime(foodIndex, foodCount);
            String totalPriceStr = foodService.calculateTotalPrice(foodIndex, foodCount);

            outputArr[0] = totalWaitingTimeStr;
            outputArr[1] = totalPriceStr;
        } else {

            outputArr[0] = "";
            outputArr[1] = "";
        }
        return outputArr;
    }


}
