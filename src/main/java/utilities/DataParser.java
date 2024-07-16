package utilities;

import java.util.ArrayList;
import java.util.List;

import base.DataEntry;

public class DataParser {
    public static List<DataEntry> parse(String input) {
        List<DataEntry> entries = new ArrayList<>();
        
        if (input == null || input.trim().isEmpty()) {
            return entries; // Return an empty list if the input is null or empty
        }
        
        String[] lines = input.split("\n");
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                String customerId = parts[0].trim();
                String contractId = parts[1].trim();
                String geozone = parts[2].trim();
                String teamcode = parts[3].trim();
                String projectcode = parts[4].trim();
                String buildduration = parts[5].trim();
                entries.add(new DataEntry(customerId, contractId, geozone, teamcode, projectcode, buildduration));
            }
        }
        return entries;
    }
}
