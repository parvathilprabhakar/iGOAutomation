package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

public class CSVUtility {
    private Map<String, Map<String, String>> data;

    // Constructor that reads the CSV file
    public CSVUtility(String csvFile) {
        data = new HashMap<>();
        readCSV(csvFile);
    }

    // Method to read the CSV file
    private void readCSV(String csvFile) {
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String[] headers = br.readLine().split(cvsSplitBy);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(cvsSplitBy);
                String key = values[0]; // First column as key
                Map<String, String> scenarioMap = new HashMap<>();

                // Ensure that the values array has the same length as headers
                for (int i = 1; i < headers.length; i++) {
                    if (i < values.length) { // Check if the index exists in values
                        scenarioMap.put(headers[i], values[i]);
                    } else {
                        scenarioMap.put(headers[i], ""); // or handle missing values as needed
                    }
                }
                data.put(key, scenarioMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Method to get value based on header and scenario
    public String getValue(String header, String scenario) {
        if (data.containsKey(header) && data.get(header).containsKey(scenario)) {
            return data.get(header).get(scenario);
        }
        return null; // or throw an exception if preferred

    }
}
