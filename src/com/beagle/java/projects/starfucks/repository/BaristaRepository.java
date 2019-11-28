package com.beagle.java.projects.starfucks.repository;

import java.io.*;

import static com.beagle.java.projects.starfucks.utils.Utils.removeNullValue;


/**
 * Class consisting of methods that CRUD data in baristaRepository.txt based on data from Barista Service
 */
public class BaristaRepository {

    String filePath = "C:\\Users\\최연우\\IdeaProjects\\StarfucksProject\\src\\com\\beagle\\java\\projects\\starfucks\\repository\\database\\BaristaRepository.txt";

    /**
     * Method to store input data in BaristaRepository
     * @param inputStr
     * @return (boolean) success
     */
    public boolean saveToBaristaRepository(String inputStr) {
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
     * Method that retrieves data stored in text file and returns as String
     * @return (String) a String of the content stored in the text file.
     */
    public String[] readAllBaristaData() {
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
        return removeNullValue(outputArr);
    }





    /**
     * Method to modify file data corresponding to file path
     * @param oldData
     * @param newData
     * @return (boolean) success
     */
    public boolean updateBaristaRepository(String oldData, String newData) {
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
                    if (line.contains(oldData)) {
                        line = line.replace(oldData, newData);
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
