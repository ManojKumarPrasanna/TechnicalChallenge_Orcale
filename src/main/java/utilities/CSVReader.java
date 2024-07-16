package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import base.DataEntry;

public class CSVReader {
    public static List<DataEntry> readFromCSV(String filePath) throws IOException {
        List<DataEntry> entries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip the header line
                    continue;
                }
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String customerId = parts[0].trim();
                    String contractId = parts[1].trim();
                    String geozone = parts[2].trim().replaceAll(" ", "_");
                    String teamcode = parts[3].trim();
                    String projectcode = parts[4].trim();
                    String buildduration = parts[5].trim();
                    entries.add(new DataEntry(customerId, contractId, geozone, teamcode, projectcode, buildduration));
                }
            }
        }
        return entries;
    }
    }
}
