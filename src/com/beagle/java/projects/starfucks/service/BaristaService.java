package com.beagle.java.projects.starfucks.service;


import com.beagle.java.projects.starfucks.repository.BaristaRepository;

import static com.beagle.java.projects.starfucks.utils.Utils.*;


/**
 * When an order comes in, if the existing working barista has more than 10 jobs, call the new barista or assign work to the existing barista.
 * When an order is processed, if the working barista is doing more than one job, the number of orders being processed is subtracted, and if the processed order was the only thing that was done, delete barista from BaristaRepository.txt
 * @see com.beagle.java.projects.starfucks.repository.BaristaRepository
 * @see BaristaRepository#readAllBaristaData()
 * @author Beagle
 */
public class BaristaService {
    BaristaRepository baristaRepository = new BaristaRepository();
    String[] baristaDataArr = baristaRepository.readAllBaristaData();



    /**
     * calls a new barista when all baristas are performing 10 orders.
     * @return (String) Barista index assigned new order.
     */
    public String createNewBarista() {


        // Get index of last barista
        String lastStr = baristaDataArr[baristaDataArr.length - 1];
        String[] newLastArrStr = lastStr.split("/");
        int[] newLastArr = stringArrayToIntArray(newLastArrStr);

        // The index of the barista to be created
        int addIndexInt = newLastArr[0] + 1;
        String addIndexStr = intToString(addIndexInt);


        // return the index of the barista to be created
        String input = addIndexStr + "/1;";
        if (baristaRepository.saveToBaristaRepository(input)) {
            return intToString(addIndexInt);
        } else {
            return "";
        }
    }


    /**
     * assigns an order to that barista when there is a free barista
     * @return (String) Barista index assigned new order
     */
    public String increaseOrderCount() {

        // variables declaration
        String[] eachArrStr;
        int[] eachArr;

        int newCount = 0;
        int oldCount = 0;
        String oldCountStr = "";
        String newCountStr = "";

        String updatedBaristaIndex = "";


        int i = 0;
        boolean run = true;
        while (i < (baristaRepository.readAllBaristaData()).length && run) {

            // Declare each barista info as an array of strings
            eachArrStr = (baristaRepository.readAllBaristaData())[i].split("/");
            eachArr = stringArrayToIntArray(eachArrStr);

            // Each barista has less than 10 orders
            if (eachArr[1] < 10 && eachArr[1] >= 0) {

                // Update the number of things doing
                oldCount = eachArr[1];
                newCount = eachArr[1] + 1;
                oldCountStr = intToString(oldCount);
                newCountStr = intToString(newCount);



                // terminate while statement to update
                run = false;

                // index of updated barista
                updatedBaristaIndex = intToString(eachArr[0]);
            } else {
                i++;
            }
        }

        // Create new data and old data
        String oldData = updatedBaristaIndex + "/" + oldCountStr + ";";
        String newData = updatedBaristaIndex + "/" + newCountStr + ";";

        System.out.println(oldData);
        System.out.println(newData);

        // update data in BaristaRepository.txt
        baristaRepository.updateBaristaRepository(oldData, newData);

        // return the index of the barista to be updated
        return updatedBaristaIndex;
    }


    /**
     * substrate order count of barista in BaristaRepository.txt
     * @param baristaIndexStr Index of barista that was processing the corresponding order
     * @return (boolean) success
     */
    public boolean reduceOrderCount(String baristaIndexStr) {

        String[] eachArr;
        String[] eachArr2;
        String eachStr = "";
        String indexStr = "";
        String countStr = "";


        // find barista data that should be updated by baristaIndex
        int baristaIndex;
        for (int i = 0; i < baristaDataArr.length; i++) {
            eachArr = baristaDataArr[i].split("/");
            baristaIndex = stringToInt(baristaIndexStr);
            if (eachArr[0].equals(intToString(baristaIndex))) {
                eachStr = baristaDataArr[i];
            }
        }
        boolean success;
        if (eachStr != null) {
            eachArr2 = eachStr.split("/");
            indexStr = eachArr2[0];
            countStr = eachArr2[1];



            // update orderCount
            int index = stringToInt(indexStr);
            int count = stringToInt(countStr);

            String inputIndex = "";
            String inputCount = "";
            if (count > 1) {
                inputIndex = intToString(index);
                inputCount = intToString(count-1);
            } else if (count == 1) {
                inputIndex = intToString(index);
                inputCount = intToString(count-1);
            }

            // update BaristaRepository.txt
            String oldStr3 = indexStr + "/" + countStr + ";";
            String newStr3 = inputIndex + "/" + inputCount + ";";
            success = baristaRepository.updateBaristaRepository(oldStr3, newStr3);
        } else {
            success = false;
        }

        return success;
    }

    /**
     * deletes barista from BaristaRepository.txt if the order processed by barista is the only remaining order
     * @param baristaIndexStr Index of barista that was processing the corresponding order
     * @return (boolean) success
     */
    public boolean deleteBarista (String baristaIndexStr) {

        String[] eachArr;
        String[] eachArr2;
        String eachStr = "";
        String indexStr = "";
        String countStr = "";


        // find barista data that should be updated by baristaIndex
        int baristaIndex;
        for (int i = 0; i < baristaDataArr.length; i++) {
            eachArr = baristaDataArr[i].split("/");
            baristaIndex = stringToInt(baristaIndexStr);
            if (eachArr[0].equals(intToString(baristaIndex))) {
                eachStr = baristaDataArr[i];
            }
        }
        boolean success;
        if (eachStr != null) {
            eachArr2 = eachStr.split("/");
            indexStr = eachArr2[0];
            countStr = eachArr2[1];

            // update BaristaRepository.txt
            String oldStr3 = indexStr + "/" + countStr + ";";
            String newStr3 = "";
            success = baristaRepository.updateBaristaRepository(oldStr3, newStr3);
        } else {
            success = false;
        }

        return success;
    }


}
