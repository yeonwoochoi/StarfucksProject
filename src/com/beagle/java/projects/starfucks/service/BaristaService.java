package com.beagle.java.projects.starfucks.service;


import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.controller.BaristaController;
import com.beagle.java.projects.starfucks.domain.Barista;
import com.beagle.java.projects.starfucks.repository.BaristaRepository;

import static com.beagle.java.projects.starfucks.utils.Utils.*;


/**
 * When an order comes in, if the existing working barista has more than 10 jobs, call the new barista or assign work to the existing barista.
 * When an order is processed, if the working barista is doing more than one job, the number of orders being processed is subtracted, and if the processed order was the only thing that was done, delete barista from BaristaRepository.txt
 * @see com.beagle.java.projects.starfucks.repository.BaristaRepository
 * @author Beagle
 */
public class BaristaService {
    public BaristaService() {}

    private static BaristaService baristaService = new BaristaService();
    public static BaristaService getInstance() {return baristaService;}


    public StarFucksList<Barista> callNewBarista() {
        BaristaController baristaController = BaristaController.getInstance();
        StarFucksList<Barista> newList =  baristaController.getTemporaryStorage();
        if (newList != null) {
            String lastBaristaIndex = newList.get(newList.size()-1).getBaristaIndex();
            String newBaristaIndex = intToString((stringToInt(lastBaristaIndex))+1);
            String newLastOrderCount = "1";
            Barista newBarista = new Barista(newBaristaIndex, newLastOrderCount);
            newList.addLast(newBarista);
            return newList;
        } else {
            return null;
        }
    }


    public StarFucksList<Barista> increaseOrderCount(int index) {
        BaristaController baristaController = BaristaController.getInstance();
        StarFucksList<Barista> newList =  baristaController.getTemporaryStorage();
        if (newList != null) {
            String lastBaristaIndex = newList.get(index).getBaristaIndex();
            String lastOrderCount = newList.get(index).getOrderCount();
            String newLastOrderCount = intToString((stringToInt(lastOrderCount))+1);
            Barista newBarista = new Barista(lastBaristaIndex, newLastOrderCount);
            newList.remove(index);
            newList.add(index, newBarista);
            return newList;
        } else {
            return null;
        }
    }


    public StarFucksList<Barista> decreaseOrderCount(int index) {
        BaristaController baristaController = BaristaController.getInstance();
        StarFucksList<Barista> newList =  baristaController.getTemporaryStorage();
        if (newList != null) {
            String lastBaristaIndex = newList.get(index).getBaristaIndex();
            String lastOrderCount = newList.get(index).getOrderCount();
            String newLastOrderCount = intToString((stringToInt(lastOrderCount))-1);
            Barista newBarista = new Barista(lastBaristaIndex, newLastOrderCount);
            newList.remove(index);
            newList.add(index, newBarista);
            return newList;
        } else {
            return null;
        }
    }


    public StarFucksList<Barista> deleteBaristaService(int index) {
        BaristaController baristaController = BaristaController.getInstance();
        StarFucksList<Barista> newList =  baristaController.getTemporaryStorage();
        if (newList != null) {
            if (index == 0){
                Barista barista = new Barista("1","0");
                newList.removeFirst();
                newList.addFirst(barista);
            } else {
                newList.remove(index);
            }
            return newList;
        } else {
            return null;
        }
    }



    public StarFucksList<Barista> start() {
        BaristaRepository baristaRepository = new BaristaRepository();
        return baristaRepository.readAllBarista();
    }

    public void end() {
        BaristaController baristaController = BaristaController.getInstance();
        StarFucksList<Barista> finalData = baristaController.getTemporaryStorage();
        BaristaRepository baristaRepository = new BaristaRepository();
        baristaRepository.saveToBaristaRepository(finalData);
    }

}
