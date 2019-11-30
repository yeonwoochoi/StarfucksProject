package com.beagle.java.projects.starfucks.utils;

import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Barista;
import com.beagle.java.projects.starfucks.domain.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * class with methods used throughout the project
 * class with method to convert type or remove null value of array
 * @author Beagle
 */
public class Utils {


    /**
     * Method to remove null value among data in input string array
     * @param inputArr String array
     * @return string array with null values ​​removed
     */
    public static String[] removeNullValue(String[] inputArr) {

        List<String> list = new ArrayList<String>();

        for(String s : inputArr) {
            if(s != null && s.length() > 0) {
                list.add(s);
            }
        }

        String[] outputArr = list.toArray(new String[list.size()]);
        return outputArr;
    }


    /**
     * Convert a String type variable to an int type
     * @param input int type
     * @return Value converted to String type
     */
    public static String intToString(int input)  {
        String output = Integer.toString(input);
        return output;
    }



    /**
     * Convert variable of int type to String type
     * @param input String type
     * @return Value converted to int type
     */
    public static int stringToInt(String input) {
        int output = Integer.parseInt(input);
        return output;
    }


    /**
     * Method to convert String array to int array
     * @param input String array
     * @return (int) array
     */
    public static int[] stringArrayToIntArray(String[] input) {
        int[] newObjects = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            newObjects[i] = stringToInt(input[i]);
        }
        return newObjects;
    }



    public static StarFucksList<Customer> stringToCustomerLinkedList(String inputStr) {
        StarFucksList<Customer> output = new StarFucksList<Customer>();
        if (inputStr == null) {
            return null;
        } else {
            String[] inputArr = inputStr.split(";");
            String[] eachArr;
            Customer eachCustomer;

            for (int i = 0; i < inputArr.length; i++) {
                eachArr = inputArr[i].split("/");
                if (eachArr.length == 4) {
                    eachCustomer = new Customer(eachArr[0], eachArr[1], eachArr[2], eachArr[3]);
                    output.addLast(eachCustomer);
                }
            }
            return output;
        }
    }

    public static String CustomerLinkedListToString(StarFucksList<Customer> inputList) {
        String output = "";
        if (inputList == null) {
            return "";
        } else {
            for (int i = 0; i < inputList.size(); i++) {
                output += inputList.get(i).toString();
            }
            return output;
        }
    }

    public static StarFucksList<Barista> stringToBaristaLinkedList(String inputStr) {
        StarFucksList<Barista> output = new StarFucksList();
        if (inputStr == null) {
            return null;
        } else {
            String[] inputArr = inputStr.split(";");
            String[] eachArr;
            Barista eachBarista;

            for (int i = 0; i < inputArr.length; i++) {
                eachArr = inputArr[i].split("/");
                if (eachArr.length == 2) {
                    eachBarista = new Barista(eachArr[0], eachArr[1]);
                    output.addLast(eachBarista);
                }
            }
            return output;
        }
    }

    public static String BaristaLinkedListToString(StarFucksList<Barista> inputList) {
        String output = "";
        if (inputList == null) {
            return "";
        } else {
            for (int i = 0; i < inputList.size(); i++) {
                output += inputList.get(i).toString();
            }
            return output;
        }
    }


}
