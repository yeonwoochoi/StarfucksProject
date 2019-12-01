package com.beagle.java.projects.starfucks.controller;


import com.beagle.java.projects.starfucks.collection.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Food;
import com.beagle.java.projects.starfucks.repository.FoodRepository;
import com.beagle.java.projects.starfucks.repository.temporaryStorage.FoodDataList;
import com.beagle.java.projects.starfucks.service.FoodService;

import java.util.ArrayList;

import static com.beagle.java.projects.starfucks.utils.Utils.stringToInt;



/**
 * Returns an appropriate data, such as a list of menus or a receipt based on input order data
 * Implemented in singleton pattern and will call in Manager class
 * @see com.beagle.java.projects.starfucks.service.FoodService
 * @see com.beagle.java.projects.starfucks.repository.FoodRepository
 * @author Beagle
 */
public class FoodController {

    FoodService foodService = new FoodService();


    private static FoodController foodController = new FoodController();
    private FoodController() {}
    public static FoodController getInstance() {
        return foodController;
    }


    FoodDataList foodDataList = new FoodDataList();
    public StarFucksList<Food> getTemporaryStorage() {
        return foodDataList.getTemporaryStorage();
    }
    private void setTemporaryStorage(StarFucksList<Food> input) {foodDataList.setTemporaryStorage(input);}




    /**
     * Method that returns all data in FoodRepository.txt as string
     * @return String menu
     */
    public String showMenuList() {

        String beverageStr = "\n== beverage ==\n\n";
        String dessertStr = "== dessert ==\n";

        StarFucksList<Food> foodList = getTemporaryStorage();

        for (int i = 0; i < 10; i++) {
            beverageStr += foodList.get(i).getFoodIndex() + ".  " + foodList.get(i).getFoodName() + ".  " + foodList.get(i).getFoodPrice() + "\n";
        }
        for (int i = 10; i < 20; i++) {
            dessertStr += foodList.get(i).getFoodIndex() + ".  "  + foodList.get(i).getFoodName() + "  "  + foodList.get(i).getFoodPrice() + "\n";
        }

        String output = beverageStr + dessertStr;

        return output;
    }


    /**
     * Method to make receipt of input order
     * @param foodIndexList
     * @param foodCountList
     * @return (String) Converting the receipt to a string using the toString method of the Receipt class
     */
    public String makeReceipt(StarFucksList<String> foodIndexList, StarFucksList<String> foodCountList) {

        StarFucksList<Food> foodStorage = getTemporaryStorage();
        StarFucksList<Food> foodData = new StarFucksList<Food>();
        for (int i = 0; i < foodIndexList.size(); i++) {
            for (int j = 0; j < foodStorage.size(); j++) {
                if (foodIndexList.get(i).equals(foodStorage.get(j).getFoodIndex())) {
                    foodData.addLast(foodStorage.get(j));
                }
            }
        }

        String total = foodService.calculateTotalPrice(foodIndexList, foodCountList);
        String content = "";
        for (int i = 0; i < foodData.size(); i++) {
            Food eachFood = foodData.get(i);
            content += eachFood.getFoodName() + "  " + eachFood.getFoodPrice() + "   " + foodCountList.get(i) + "\n";
        }
        String output = "\n== receipt ==\n" + content + "\n" + "total :  " + total + "\n\n";


        return output;

    }


    /**
     * take the food number and the food quantity string array created when ordering, calculates the total wait time and the total price, and returns them in a string array.
     * @param foodIndex
     * @param foodCount
     * @return (String[]) {totalWaitingTime, totalPrice}
     */
    public StarFucksList<String> calculateFoodData(StarFucksList<String> foodIndex, StarFucksList<String> foodCount) {
        StarFucksList<String>  output = new StarFucksList<String>();

        if (foodIndex != null && foodCount != null) {

            String totalPriceStr = foodService.calculateTotalPrice(foodIndex, foodCount);
            String totalWaitingTimeStr = foodService.calculateWaitingTime(foodIndex, foodCount);

            output.addFirst(totalPriceStr);
            output.addLast(totalWaitingTimeStr);
        }
        return output;
    }




    public void start() {
        FoodService foodService = new FoodService();
        setTemporaryStorage(foodService.start());
    }

}
