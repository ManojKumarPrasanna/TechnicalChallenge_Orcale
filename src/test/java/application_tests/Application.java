package application_tests;

import java.util.List;
import java.util.Map;
import java.util.Set;

import base.DataEntry;
import utilities.DataParser;
import utilities.ReportGenerator;

public class Application {
	 static String input = "2343225, 2345, us_east, RedTeam, ProjectApple, 3445s\n"
             + "1223456, 2345, us_west, BlueTeam, ProjectBanana, 22118s\n"
             + "3244332, 2346, eu_west, YellowTeam3, ProjectCarrot, 43228s\n"
             + "1233456, 2345, us_west, BlueTeam, ProjectDate, 2221s\n"
             + "3244132, 2346, eu_west, YellowTeam3, ProjectEgg, 41228s";


    public static void main(String[] args) {
       
        List<DataEntry> entries = DataParser.parse(input);

        Map<String, Long> uniqueCustomerByContract = ReportGenerator.uniqueCustomerCountByContract(entries);
        Map<String, Long> uniqueCustomerByGeozone = ReportGenerator.uniqueCustomerCountByGeozone(entries);
        Map<String, Double> averageBuildDurationByGeozone = ReportGenerator.averageBuildDurationByGeozone(entries);
        Map<String, Set<String>> uniqueCustomersByGeozone = ReportGenerator.uniqueCustomersByGeozone(entries);

     // Print the number of unique customers by contract
        System.out.println("Unique Customer Count by Contract:");
        for (Map.Entry<String, Long> entry : uniqueCustomerByContract.entrySet()) {
            String contractId = entry.getKey();
            Long count = entry.getValue();
            System.out.println(contractId + ": " + count);
        }

        // Print the number of unique customers by geozone
        System.out.println("\nUnique Customer Count by Geozone:");
        for (Map.Entry<String, Long> entry : uniqueCustomerByGeozone.entrySet()) {
            String geozone = entry.getKey();
            Long count = entry.getValue();
            System.out.println(geozone + ": " + count);
        }

        // Print the average build duration by geozone
        System.out.println("\nAverage Build Duration by Geozone:");
        for (Map.Entry<String, Double> entry : averageBuildDurationByGeozone.entrySet()) {
            String geozone = entry.getKey();
            Double averageDuration = entry.getValue();
            System.out.println(geozone + ": " + averageDuration);
        }

        // Print the list of unique customers by geozone
        System.out.println("\nUnique Customers by Geozone:");
        for (Map.Entry<String, Set<String>> entry : uniqueCustomersByGeozone.entrySet()) {
            String geozone = entry.getKey();
            Set<String> customers = entry.getValue();
            System.out.println(geozone + ": " + customers);
        }

    }
}
