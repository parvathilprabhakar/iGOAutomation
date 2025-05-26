package misc;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataMasking {

    private static final String[] FIRST_NAMES = {
            "John", "Jane", "Michael", "Emily", "Chris",
            "Jessica", "David", "Sarah", "Daniel", "Laura",
            "Robert", "Linda", "James", "Patricia", "William",
            "Elizabeth", "Joseph", "Jennifer", "Charles", "Maria",
            "Thomas", "Susan", "Matthew", "Margaret", "Anthony",
            "Dorothy", "Mark", "Rebecca", "Donald", "Helen"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Jones", "Brown",
            "Davis", "Miller", "Wilson", "Moore", "Taylor",
            "Anderson", "Thomas", "Jackson", "White", "Harris",
            "Martin", "Thompson", "Garcia", "Martinez", "Robinson",
            "Clark", "Rodriguez", "Lewis", "Lee", "Walker",
            "Hall", "Allen", "Young", "King", "Wright"
    };

    private static final String[] PROVINCES = {
            "Ontario" // Only Ontario is kept for more cities
    };

    private static final String[][] CITIES = {
            {
                    "Toronto", "Ottawa", "Mississauga", "Brampton", "Hamilton",
                    "London", "Markham", "Kitchener", "Windsor", "Richmond Hill",
                    "Guelph", "Niagara Falls", "Barrie", "Thunder Bay", "Sarnia",
                    "Peterborough", "St. Catharines", "Sudbury", "Cambridge",
                    "Kingston", "Burlington", "Ajax", "Whitby", "Oshawa",
                    "Newmarket", "Orillia", "Welland", "Niagara-on-the-Lake"
            }
    };

    public static void main(String[] args) throws CsvValidationException {
        try {
            // Step 1: Select file (Excel or CSV)
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Excel or CSV File");
            int userSelection = fileChooser.showOpenDialog(null);
            if (userSelection != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File fileToOpen = fileChooser.getSelectedFile();
            String fileName = fileToOpen.getName();
            List<String[]> data = new ArrayList<>();
            List<String> columnNames = new ArrayList<>();

            System.out.println("File selected: "+fileToOpen);

            // Step 2: Read the file based on its extension
            if (fileName.endsWith(".xlsx")) {
                // Read Excel file
                FileInputStream fis = new FileInputStream(fileToOpen);
                Workbook workbook = new XSSFWorkbook(fis);
                Sheet sheet = workbook.getSheetAt(0);
                Row headerRow = sheet.getRow(0);
                for (Cell cell : headerRow) {
                    columnNames.add(cell.getStringCellValue());
                }
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        String[] rowData = new String[columnNames.size()];
                        for (int j = 0; j < columnNames.size(); j++) {
                            Cell cell = row.getCell(j);
                            rowData[j] = cell != null ? cell.getStringCellValue() : "";
                        }
                        data.add(rowData);
                    }
                }
                workbook.close();
                fis.close();
            } else if (fileName.endsWith(".csv")) {
                // Read CSV file
                CSVReader csvReader = new CSVReader(new FileReader(fileToOpen));
                columnNames = List.of(csvReader.readNext()); // Read header
                String[] rowData;
                while ((rowData = csvReader.readNext()) != null) {
                    data.add(rowData);
                }
                csvReader.close();
            } else {
                JOptionPane.showMessageDialog(null, "Unsupported file type. Please select an Excel or CSV file.");
                return;
            }

            // Step 3: Select masking method
            String[] options = {"Mask with '*'", "Replace with random value"};
            int choice = JOptionPane.showOptionDialog(null, "Select masking method:",
                    "Masking Method",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            if (choice == -1) {
                return; // User canceled
            }
            if (choice == 0) {
                System.out.println("Data masking with *");
            } else {
                System.out.println("Data masking with Random value");
            }

            // Step 4: Select columns to mask using checkboxes
            boolean[] selectedColumns = new boolean[columnNames.size()];
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Keep the BoxLayout for vertical stacking

            for (int i = 0; i < columnNames.size(); i++) {
                JCheckBox checkBox = new JCheckBox(columnNames.get(i));
                checkBox.setSelected(false);
                checkBox.setPreferredSize(new Dimension(300, 30)); // Set preferred size for checkboxes
                panel.add(checkBox);
                final int index = i;
                checkBox.addActionListener(e -> selectedColumns[index] = checkBox.isSelected());
            }

            // Add a scroll pane to allow resizing and scrolling
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setPreferredSize(new Dimension(400, 300)); // Set preferred size for the scroll pane
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show vertical scrollbar

            int result = JOptionPane.showConfirmDialog(null, scrollPane, "Select columns to mask", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) {
                return; // User canceled
            }

            //System.out.println("Columns selected: ");

            // Step 5: Mask selected columns
            for (int i = 0; i < selectedColumns.length; i++) {
                if (selectedColumns[i]) {
                  //  System.out.println(selectedColumns[i]);
                    for (String[] row : data) {
                        if (i < row.length) {
                            if (choice == 0) {
                                row[i] = maskDataWithAsterisk(row[i]);
                            } else {
                                row[i] = replaceWithRandomValue(row[i]);
                            }
                        }
                    }
                }
            }

            // Step 6: Save the modified file
            String directoryPath = System.getProperty("user.dir") + "\\Automated data files";
            String outputFilePath = directoryPath + "\\Masked Data\\MaskedData_" + System.currentTimeMillis() + ".csv";

            // Create the directory if it doesn't exist
            new File(directoryPath + "\\Masked Data").mkdirs();

            // Save as CSV file
            FileWriter writer = new FileWriter(outputFilePath);
            writer.append(String.join(",", columnNames)).append("\n");
            for (String[] row : data) {
                writer.append(String.join(",", row)).append("\n");
            }
            writer.flush();
            writer.close();
            System.out.println("Data masking completed! File saved at: " + outputFilePath);
            JOptionPane.showMessageDialog(null, "Data masking completed! File saved at: " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String maskDataWithAsterisk(String data) {
        // Simple masking logic with asterisks
        return data.replaceAll(".", "*");
    }

    private static String replaceWithRandomValue(String data) {
        // Generate a random value based on the format of the original data
        if (data.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            // If the data is an email address
            return generateRandomEmail();
        } else if (data.matches("^\\d{10}$")) {
            // If the data is a phone number (10 digits)
            return generateRandomPhoneNumber();
        } else if (data.matches("^\\d+$")) {
            // If the data is a number (integer)
            return String.valueOf(new Random().nextInt(100)); // Random number between 0 and 99
        } else if (data.matches("^[A-Za-z]+$")) {
            // If the data is a first name or last name
            return generateRandomName();
        } else if (data.matches("^[A-Za-z\\s]+$")) {
            // If the data is a city name
            return generateRandomCity();
        } else {
            // Default case, return a random string
            return generateRandomString(8); // Random string of length 8
        }
    }

    private static String generateRandomEmail() {
        String[] domains = {"example.com", "test.com", "demo.com"};
        String randomName = generateRandomString(5);
        String randomDomain = domains[new Random().nextInt(domains.length)];
        return randomName + "@" + randomDomain;
    }

    private static String generateRandomPhoneNumber() {
        Random random = new Random();
        return String.format("%010d", random.nextInt(1000000000)); // Generate a random 10-digit number
    }

    private static String generateRandomName() {
        Random random = new Random();
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        return firstName + " " + lastName; // Combine first and last name
    }

    private static String generateRandomCity() {
        Random random = new Random();
        return CITIES[0][random.nextInt(CITIES[0].length)]; // Random city from the list
    }

    private static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
