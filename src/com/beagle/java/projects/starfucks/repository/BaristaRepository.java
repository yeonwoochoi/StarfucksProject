package com.beagle.java.projects.starfucks.repository;

import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Barista;

import java.io.*;

import static com.beagle.java.projects.starfucks.utils.Utils.BaristaLinkedListToString;
import static com.beagle.java.projects.starfucks.utils.Utils.stringToBaristaLinkedList;


/**
 * Class consisting of methods that CRUD data in baristaRepository.txt based on data from Barista Service
 * @see com.beagle.java.projects.starfucks.repository.database
 * @author Beagle
 */
public class BaristaRepository {

    String filePath = "C:\\Users\\최연우\\IdeaProjects\\StarfucksProject\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\BaristaRepository.txt";



    public StarFucksList<Barista> readAllBarista() {
        File file = new File(filePath);
        StarFucksList<Barista> outputList;
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
        outputList = stringToBaristaLinkedList(output);
        return outputList;
    }



    public void deleteAllBarista() {
        String newFilePath = "C:\\Users\\최연우\\IdeaProjects\\starfucksPractice\\src\\baristaPackage\\practice1.txt";
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




    public void saveToBaristaRepository(StarFucksList<Barista> inputList) {
        String inputStr = BaristaLinkedListToString(inputList);
        File file = new File(filePath);

        try {
            deleteAllBarista();
            FileWriter fWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fWriter);

            if (file.isFile() && file.canWrite()) {
                bufferedWriter.write(inputStr);
                bufferedWriter.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
