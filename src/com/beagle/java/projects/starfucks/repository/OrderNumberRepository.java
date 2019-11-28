package com.beagle.java.projects.starfucks.repository;


import java.io.*;

import static com.beagle.java.projects.starfucks.utils.Utils.stringToInt;
import static com.beagle.java.projects.starfucks.utils.Utils.intToString;


/**
 * The class that loads the order number from OrderNumberRepository.txt which stores only the order number
 * updates it by adding 1 whenever the order number is assigned so that the order numbers do not overlap.
 * @see com.beagle.java.projects.starfucks.repository.database
 * @author Beagle
 */
public class OrderNumberRepository {

    String filePath = "C:\\Users\\최연우\\IdeaProjects\\StarfucksProject\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\OrderIndexRepository.txt";

    /**
     * get order number from OrderNumberRepository.txt and return
     * @return (String) order number.
     */
    public String readOrderNumber() {
        File file = new File(filePath);
        String orderNumber ="";
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                orderNumber += line;
            }
            bufferedReader.close();
            return orderNumber;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            orderNumber += String.valueOf(e);
        }
        return orderNumber;
    }


    /**
     * update order number from OrderNumberRepository.txt and return success
     * @return (boolean) success
     */
    public boolean updateOrderNumber() {
        String orderNumber = readOrderNumber();
        String newOrderNumber = intToString(stringToInt(orderNumber) + 1);

        String pseudoFilePath = "C:\\Users\\최연우\\IdeaProjects\\StarfucksProject\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\PseudoRepository.txt";
        File pseudoFile = new File(pseudoFilePath);
        boolean success = false;

        try {
            if (pseudoFile.createNewFile()) {
                success = true;
            }
        } catch (IOException e) {
            success = false;
        }  catch (Exception e) {
            success = false;
        }

        if (success) {
            BufferedReader br = null;
            BufferedWriter bw = null;

            try {
                br = new BufferedReader(new FileReader(filePath));
                bw = new BufferedWriter(new FileWriter(pseudoFilePath));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains(orderNumber)) {
                        line = line.replace(orderNumber, newOrderNumber);
                    }
                    bw.write(line);
                }
                success = true;

            }  catch (Exception e) {
                success = false;
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                } catch (IOException e) {
                    success = false;
                }
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    success = false;
                }
            }
        }

        File oldFile = new File(filePath);
        File newFile = new File(pseudoFilePath);

        oldFile.delete();
        newFile.renameTo(oldFile);
        return success;
    }
}
