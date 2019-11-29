package com.beagle.java.projects.starfucks.repository;

import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Customer;
import sun.awt.image.ImageWatched;

import java.io.*;
import java.util.LinkedList;

public class CustomerRepository {

    String filePath = "C:\\Users\\최연우\\IdeaProjects\\StarfucksProject\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\CustomerRepository.txt";

    public StarFucksList<Customer> stringToLinkedList(String inputStr) {
        StarFucksList<Customer> output = new StarFucksList<Customer>();
        String[] inputArr = inputStr.split(";");
        String[] eachArr;
        Customer eachCustomer = new Customer();
        for (int i = 0; i < inputArr.length; i++) {
            eachArr = inputArr[i].split("/");
            eachCustomer.setId(eachArr[0]);
            eachCustomer.setName(eachArr[1]);
            eachCustomer.setPhoneNumber(eachArr[2]);
            eachCustomer.setEmail(eachArr[3]);
            output.addLast(eachCustomer);
        }
        return output;
    }

    public String linkedListToString (StarFucksList<Customer> inputList) {

    }

    public LinkedList<String> readAllCustomer() {
        File file = new File(filePath);
        String output ="";
        String[] outputArr = new String[100];
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                output += line;
            }
            bufferedReader.close();
            outputArr = output.split(";");
            return outputArr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputArr;
    }
}
