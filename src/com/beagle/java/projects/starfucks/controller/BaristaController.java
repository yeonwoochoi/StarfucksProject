package com.beagle.java.projects.starfucks.controller;

import com.beagle.java.projects.starfucks.repository.BaristaRepository;
import com.beagle.java.projects.starfucks.service.BaristaService;

import static com.beagle.java.projects.starfucks.utils.Utils.stringArrayToIntArray;
import static com.beagle.java.projects.starfucks.utils.Utils.stringToInt;


/**
 * Call the appropriate BaristaService's method according to the input order data
 * Implemented in singleton pattern and will call in Manager class
 * @see com.beagle.java.projects.starfucks.service.BaristaService
 * @see com.beagle.java.projects.starfucks.repository.BaristaRepository
 * @author Beagle
 */
public class BaristaController {

    BaristaRepository baristaRepository = new BaristaRepository();
    BaristaService baristaService = new BaristaService();


    private static BaristaController baristaController = new BaristaController();
    private BaristaController() {}
    public static BaristaController getInstance() {
        return baristaController;
    }


    public String getOrderToBarista() {
        String[] baristaDataArr = baristaRepository.readAllBaristaData();

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

            if ((i == baristaDataArr.length - 1) && (check == baristaDataArr.length) && (run)) {
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
        String[] baristaDataArr = baristaRepository.readAllBaristaData();
        int i = 0;
        boolean run = true;
        boolean success = false;
        int eachOrderCount = 0;
        while (run) {
            int[] eachArr = stringArrayToIntArray(baristaDataArr[i].split("/"));
            if (eachArr[0] == stringToInt(baristaIndexStr)) {
                eachOrderCount = eachArr[1];
                if (eachOrderCount >= 2 && eachOrderCount <= 10) {
                    success = baristaService.reduceOrderCount(baristaIndexStr);
                    run = false;
                } else if (eachOrderCount == 1) {
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
