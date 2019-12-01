package com.beagle.java.projects.starfucks.repository;


import com.beagle.java.projects.starfucks.collection.StarFucksList;
import com.beagle.java.projects.starfucks.domain.User;

import java.io.*;

import static com.beagle.java.projects.starfucks.utils.Utils.stringToUserLinkedList;
import static com.beagle.java.projects.starfucks.utils.Utils.userLinkedListToString;


/**
 * Index of barista that was processing the corresponding order
 * @see com.beagle.java.projects.starfucks.repository.database
 * @author Beagle
 */
public class UserRepository {

    String filePath = "C:\\Users\\최연우\\IdeaProjects\\StarfucksProject\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\UserRepository.txt";

    /**
     * Method to store input data in UserRepository
     * @param inputList add the string data in the form of "orderIndex/baristaIndex/waitingTime/isHoldingBell;"
     * @return (boolean) success
     */
    public boolean saveToUserRegistory(StarFucksList<User> inputList) {
        String inputStr = userLinkedListToString(inputList);
        File file = new File(filePath);
        boolean success;

        try {
            FileWriter fWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fWriter);

            if (file.isFile() && file.canWrite()) {
                bufferedWriter.write(inputStr);
                bufferedWriter.close();
                success = true;
            } else {
                success = false;
            }


        } catch (FileNotFoundException e) {
            success = false;
        } catch (IOException e) {
            success = false;
        }
        return success;
    }


    /**
     * Parsing the data stored in UserRepository.txt as a unit and returning it as a string array
     * @return (String[]) string array in the form of {order number, barista index, waiting time, holding bell}
     */
    public StarFucksList<User> readAllUserData() {
        File file = new File(filePath);
        StarFucksList<User> outputList;
        String output = "";
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                output += line;
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputList = stringToUserLinkedList(output);
        return outputList;
    }





    /**
     * Method that delete data which contains inputStr
     * @return (boolean) success
     */
    public void deleteUserData() {
        String newFilePath = "C:\\Users\\최연우\\IdeaProjects\\StarfucksProject\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\PseudoRepository.txt";
        File oldFile = new File(filePath);
        File newFile = new File(newFilePath);
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        oldFile.delete();
        newFile.renameTo(oldFile);
    }


}
