package com.beagle.java.projects.starfucks.controller;

import com.beagle.java.projects.starfucks.repository.BaristaRepository;
import com.beagle.java.projects.starfucks.service.BaristaService;

import static com.beagle.java.projects.starfucks.utils.Utils.stringArrayToIntArray;
import static com.beagle.java.projects.starfucks.utils.Utils.stringToInt;

public class BaristaController {

    BaristaRepository baristaRepository = new BaristaRepository();
    BaristaService baristaService = new BaristaService();
    String[] baristaDataArr = baristaRepository.readAllBaristaData();


    public String getOrderToBarista() {
        int i = 0;
        boolean run = true;
        String baristaIndex = "100000000000";
        int check = 0;
        while (run) {
            int[] eachArr = stringArrayToIntArray(baristaDataArr[i].split("/"));
            if (eachArr[1] >= 0 && eachArr[1] <= 9) {
                baristaIndex = baristaService.increaseOrderCount();
                run = false;
            } else if (eachArr[1] == 10) {
                check += 1;
            }

            if ((i == baristaDataArr.length - 1) && (check == baristaDataArr.length)) {
                baristaIndex = baristaService.createNewBarista();
                run = false;
            }
            i++;
            if (i == baristaDataArr.length) {
                run = false;
            }
        }
        return baristaIndex;
    }

    public boolean finishOrderFromBarista(String baristaIndexStr) {
        int i = 0;
        boolean run = true;
        boolean success = false;
        int eachOrderCount = 0;
        while (run) {
            int[] eachArr = stringArrayToIntArray(baristaDataArr[i].split("/"));
            if (eachArr[0] == stringToInt(baristaIndexStr)) {
                eachOrderCount = eachArr[1];
                if (eachOrderCount >= 1 && eachOrderCount <= 10) {
                    success = baristaService.reduceOrderCount(baristaIndexStr);
                    run = false;
                } else if (eachOrderCount == 0) {
                    success = baristaService.deleteBarista(baristaIndexStr);
                    run = false;
                }
            }
            if ((i == baristaDataArr.length - 1) && (run = true)) {
                run = false;
                success = false;
            }
            i++;
            if (i == baristaDataArr.length) {
                run = false;
            }
        }
        return success;

    }
}
