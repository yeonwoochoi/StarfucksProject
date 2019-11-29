package com.beagle.java.projects.starfucks.repository;

import com.beagle.java.projects.starfucks.StarFucksList;
import com.beagle.java.projects.starfucks.domain.Customer;

import java.io.*;

public class CustomerRepository {

    String filePath = "C:\\Users\\최연우\\IdeaProjects\\StarfucksProject\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\CustomerRepository.txt";

    public StarFucksList<Customer> stringToLinkedList(String inputStr) {
        StarFucksList<Customer> output = new StarFucksList<Customer>();
        String[] inputArr = inputStr.split(";");
        String[] eachArr;
        Customer eachCustomer;
        for (int i = 0; i < inputArr.length; i++) {
            eachArr = inputArr[i].split("/");
            eachCustomer = new Customer(eachArr[0], eachArr[1], eachArr[2], eachArr[3]);
            output.addLast(eachCustomer);
        }
        return output;
    }

    public String linkedListToString (StarFucksList<Customer> inputList) {
        String output = "";
        StarFucksList<Customer>.ListIterator listIterator = inputList.listIterator();
        while (listIterator.hasNext()){
            output += listIterator.next().toString();
        }
        return output;
    }


    
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
        outputList = stringToLinkedList(output);
        return outputList;
    }
}
