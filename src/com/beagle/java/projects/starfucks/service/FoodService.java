package com.beagle.java.projects.starfucks.service;


import com.beagle.java.projects.starfucks.repository.FoodRepository;

import java.util.ArrayList;

import static com.beagle.java.projects.starfucks.utils.Utils.*;

/**
 * class with method to read food data in foodRepository.txt
 */
public class FoodService {
    FoodRepository foodRepository = new FoodRepository();

    private int calculateFoodData (String[] foodIndexArr, String[] foodCountArr, int choice) {
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
        String timeStr = "";

        int[] priceArr;
        int[] timeArr;
        for (int i = 0; i < arrayList.size(); i++) {
            String[] eachArr = (String[]) arrayList.get(i);
            priceStr += eachArr[2] + "/";
            timeStr += eachArr[3] + "/";
        }
        priceArr = stringArrayToIntArray(removeNullValue(priceStr.split("/")));
        timeArr = stringArrayToIntArray((removeNullValue(timeStr.split("/"))));
        int[] countArr = stringArrayToIntArray(foodCountArr);

        int total = 0;
        if (choice == 0) {
            for (int i = 0; i < priceArr.length; i++) {
                total += (priceArr[i] * countArr[i]);
            }
        } else {
            for (int i = 0; i < priceArr.length; i++) {
                total += (timeArr[i] * countArr[i]);
            }
        }
        return total;
    }


    public String calculateWaitingTime(String[] foodIndexArr, String[] foodCountArr) {
        int totalTime = calculateFoodData(foodIndexArr, foodCountArr, 1);
        return intToString(totalTime);
    }

    public String calculateTotalPrice(String[] foodIndexArr, String[] foodCountArr) {
        int totalPrice = calculateFoodData(foodIndexArr, foodCountArr, 0);
        return intToString(totalPrice);
    }

}
