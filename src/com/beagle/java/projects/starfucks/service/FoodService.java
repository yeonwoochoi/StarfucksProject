package com.beagle.java.projects.starfucks.service;


import com.beagle.java.projects.starfucks.collection.StarFucksList;
import com.beagle.java.projects.starfucks.controller.FoodController;
import com.beagle.java.projects.starfucks.domain.Food;
import com.beagle.java.projects.starfucks.repository.FoodRepository;

import java.util.ArrayList;

import static com.beagle.java.projects.starfucks.utils.Utils.*;

/**
 * read food data in foodRepository.txt
 * @see com.beagle.java.projects.starfucks.repository.FoodRepository
 * @author Beagle
 */
public class FoodService {


    private int calculateFoodData (StarFucksList<String> foodIndexArr, StarFucksList<String> foodCountArr, int choice) {
        FoodController foodController = FoodController.getInstance();
        StarFucksList<Food> recordList = foodController.getTemporaryStorage();

        int price = 0;
        int time = 0;

        for (int i = 0; i < recordList.size(); i++) {
            for (int j = 0; j < foodIndexArr.size(); j++) {
                if (recordList.get(i).getFoodIndex().equals(foodIndexArr.get(j))) {
                    price += stringToInt(recordList.get(i).getFoodPrice()) * stringToInt(foodCountArr.get(j));
                    time += stringToInt(recordList.get(i).getConsumedTime()) * stringToInt(foodCountArr.get(j));
                }
            }
        }

        if (choice == 0) {
            return price;
        } else {
            return time;
        }
    }


    public String calculateTotalPrice(StarFucksList<String> foodIndexArr, StarFucksList<String> foodCountArr) {
        int totalPrice = calculateFoodData(foodIndexArr, foodCountArr, 0);
        return intToString(totalPrice);
    }

    public String calculateWaitingTime(StarFucksList<String> foodIndexArr, StarFucksList<String> foodCountArr) {
        int totalTime = calculateFoodData(foodIndexArr, foodCountArr, 1);
        return intToString(totalTime);
    }


    public StarFucksList<Food> start() {
        FoodRepository foodRepository = new FoodRepository();
        return stringToFoodLinkedList(foodRepository.readAllFoodData());
    }


}
