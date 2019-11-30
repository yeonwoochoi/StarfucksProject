package com.beagle.java.projects.starfucks.repository;

import com.beagle.java.projects.starfucks.collection.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Customer;

import java.io.*;

import static com.beagle.java.projects.starfucks.utils.Utils.CustomerLinkedListToString;
import static com.beagle.java.projects.starfucks.utils.Utils.stringToCustomerLinkedList;

public class CustomerRepository {

    String filePath = "C:\\Users\\최연우\\IdeaProjects\\StarfucksProject\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\CustomerRepository.txt";



    /**
     * When this program is started, readAllCustomer method is called.
     * @return (StarFucksList) StarFucksList type of Customer datas in CustomerRegistory.txt
     */
    public StarFucksList<Customer> readAllCustomer() {
        File file = new File(filePath);
        StarFucksList<Customer> outputList;
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
        outputList = stringToCustomerLinkedList(output);
        return outputList;
    }


    /**
     * Method to delete all data in CustomerRepository.txt
     * When this program is started, readAllCustomer method is called and after that, this method is called.
     */
    public void deleteAllCustomer() {
        String newFilePath = "C:\\Users\\최연우\\IdeaProjects\\StarfucksProject\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\CustomerRepository.txt";
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



    /**
     * Method to store input data in CustomerRegistory.txt
     * Holding updated data in form of StarFucksList and saving it to text file at once when this program ends.
     * @param inputList add the string data in the form of "id/name/phoneNumber/email;"
     */
    public void saveToCustomerRepository(StarFucksList<Customer> inputList) {
        String inputStr = CustomerLinkedListToString(inputList);
        File file = new File(filePath);

        try {
            deleteAllCustomer();
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
