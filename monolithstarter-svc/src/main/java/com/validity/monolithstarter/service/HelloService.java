package com.validity.monolithstarter.service;


import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import info.debatty.java.stringsimilarity.Levenshtein;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;

@Service
public class HelloService {
    public JSONObject getCSVDuplicates(){
        ArrayList<String[]> csvrecords = new ArrayList<String[]>();
        ArrayList<String[]> duplicates = new ArrayList<String[]>();
        ArrayList<String[]> nonduplicates = new ArrayList<String[]>();
        try {
            String filepath = "../test-files/normal.csv";
            CSVReader reader = new CSVReader(new FileReader(filepath));
            String [] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                 csvrecords.add(nextLine);// parse the csv to a arraylist for easier processing 
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        Levenshtein l = new Levenshtein();
        
        for(int i=0; i < csvrecords.size(); i++){
            String record1 = Arrays.toString(Arrays.copyOfRange(csvrecords.get(i),1,11));//convert to string while cutting out the id column
            for(int j = i + 1; j < csvrecords.size(); j++){
                String record2 = Arrays.toString(Arrays.copyOfRange(csvrecords.get(j),1,11));//convert to string while cutting out the id column
                if(l.distance(record1, record2) < 48){// outputs the minimum distance/steps it takes to change one word into another, Number was found based on trial and error
                    duplicates.add(csvrecords.get(i));
                    duplicates.add(csvrecords.get(j));
                    break;
                }
            }
            if(!duplicates.contains(csvrecords.get(i))){ //checks at the end to see if the record has been added or already exist in the duplicates. if it hasn't then that means the record is unique.
                nonduplicates.add(csvrecords.get(i)); 
            }         
        }

        //Converts arrays into JSON object outputs
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArray1 = new JSONArray(duplicates);
        JSONArray jsonArray2 = new JSONArray(nonduplicates);
        try {
            jsonObj.accumulate("duplicates", jsonArray1);
            jsonObj.accumulate("nonduplicates", jsonArray2);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jsonObj;
    }
}


