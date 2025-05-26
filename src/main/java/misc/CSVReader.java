package misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {
    public static void main(String[] args) {
        String csvFile = System.getProperty("user.dir") + "\\TestData_CSV.csv"; // Update with your CSV file path
        String line;
        String cvsSplitBy = ",";

        Map<String, Map<String, String>> data = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String[] headers = br.readLine().split(cvsSplitBy);
            while ((line = br.readLine()) != null) {
                String[] values = line.split(cvsSplitBy);
                String key = values[0]; // First column as key
                Map<String, String> scenarioMap = new HashMap<>();

                for (int i = 1; i < headers.length; i++) {
                    scenarioMap.put(headers[i], values[i]);
                }
                data.put(key, scenarioMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Example of how to retrieve values
        String firstNameScenario1 = data.get("FirstName").get("SC001");
        String lastNameScenario2 = data.get("LastName").get("SC002");

        System.out.println("First Name in Scenario 1: " + firstNameScenario1);
        System.out.println("Last Name in Scenario 2: " + lastNameScenario2);
    }
}
