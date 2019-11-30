package com.beagle.java.projects.starfucks.controller;

import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Barista;
import com.beagle.java.projects.starfucks.repository.database.BaristaDataList;
import com.beagle.java.projects.starfucks.service.BaristaService;

import static com.beagle.java.projects.starfucks.utils.Utils.*;


/**
 * Call the appropriate BaristaService's method according to the input order data
 * Implemented in singleton pattern and will call in Manager class
 * @see com.beagle.java.projects.starfucks.service.BaristaService
 * @see com.beagle.java.projects.starfucks.repository.BaristaRepository
 * @author Beagle
 */
public class BaristaController {

    private BaristaController() {}

    private static BaristaController baristaController = new BaristaController();
    public static BaristaController getInstance() {
        return baristaController;
    }




    BaristaDataList baristaDataList = new BaristaDataList();
    public StarFucksList<Barista> getTemporaryStorage() {
        return baristaDataList.getTemporaryStorage();
    }
    private void setTemporaryStorage(StarFucksList<Barista> input) {baristaDataList.setTemporaryStorage(input);}







    public String getOrder() {
        BaristaService baristaService = BaristaService.getInstance();
        StarFucksList<Barista> baristaList = getTemporaryStorage();
        StarFucksList<Barista> updatedBaristaList = null;
        String updatedBaristaIndex = "";
        if (baristaList != null) {
            boolean run = true;
            int i = 0;
            while (run) {
                int eachOrderCount = stringToInt(baristaList.get(i).getOrderCount());
                if (eachOrderCount >= 0 && eachOrderCount < 10) {
                    updatedBaristaIndex = intToString(i);
                    updatedBaristaList = baristaService.increaseOrderCount(i);
                    run = false;
                } else if (eachOrderCount == 10) {
                    i++;
                } else {
                    run = false;
                }
                if (i == baristaList.size() && (!run)) {
                    run = false;
                }
                if ((i == baristaList.size()) && (run)) {
                    updatedBaristaIndex = intToString(baristaList.size());
                    updatedBaristaList = baristaService.callNewBarista();
                    run = false;
                }
            }
            setTemporaryStorage(updatedBaristaList);
            return updatedBaristaIndex;
        } else {
            return updatedBaristaIndex;
        }
    }


    public boolean finishOrder(String index) {
        BaristaService baristaService = BaristaService.getInstance();
        StarFucksList<Barista> baristaList = getTemporaryStorage();
        StarFucksList<Barista> updatedBaristaList = null;
        if (baristaList != null) {
            String targetOrderCountStr = baristaList.get(stringToInt(index)).getOrderCount();
            int targetOrderCount = stringToInt(targetOrderCountStr);
            if (targetOrderCount > 1 && targetOrderCount <= 10) {
                updatedBaristaList = baristaService.decreaseOrderCount(stringToInt(index));
            } else if (targetOrderCount <= 1){
                updatedBaristaList = baristaService.deleteBaristaService(stringToInt(index));
            }
            setTemporaryStorage(updatedBaristaList);
            return true;
        } else {
            return false;
        }
    }


    public String checkBarsitaState () {
        StarFucksList<Barista> baristaStarFucksList = getTemporaryStorage();
        String output = "";
        if (baristaStarFucksList != null) {
            for (int i = 0; i < baristaStarFucksList.size(); i++) {
                output += "barista index  :  " + baristaStarFucksList.get(i).getBaristaIndex() + "\t\torder count  :  " + baristaStarFucksList.get(i).getOrderCount() + "\n";
            }
        }
        return output;
    }





    public void start() {
        BaristaService baristaService = BaristaService.getInstance();
        setTemporaryStorage(baristaService.start());
    }

    public void end() {
        BaristaService baristaService = BaristaService.getInstance();
        baristaService.end();
    }
}
