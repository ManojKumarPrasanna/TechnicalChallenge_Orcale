package utilities;

import java.util.*;
import base.DataEntry;


public class ReportGenerator {
	 public static Map<String, Long> uniqueCustomerCountByContract(List<DataEntry> entries) {
	        Map<String, Set<String>> contractIdToCustomers = new HashMap<>();
	        
	        for (DataEntry entry : entries) {
	            String contractId = entry.getContractId();
	            String customerId = entry.getCustomerId();
	            
	            Set<String> customers = contractIdToCustomers.get(contractId);
	            if (customers == null) {
	                customers = new HashSet<>();
	                contractIdToCustomers.put(contractId, customers);
	            }
	            customers.add(customerId);
	        }
	        
	        Map<String, Long> result = new HashMap<>();
	        for (Map.Entry<String, Set<String>> entry : contractIdToCustomers.entrySet()) {
	            result.put(entry.getKey(), (long) entry.getValue().size());
	        }
	        
	        return result;
	    }

	    public static Map<String, Long> uniqueCustomerCountByGeozone(List<DataEntry> entries) {
	        Map<String, Set<String>> geozoneToCustomers = new HashMap<>();
	        
	        for (DataEntry entry : entries) {
	            String geozone = entry.getGeozone();
	            String customerId = entry.getCustomerId();
	            
	            Set<String> customers = geozoneToCustomers.get(geozone);
	            if (customers == null) {
	                customers = new HashSet<>();
	                geozoneToCustomers.put(geozone, customers);
	            }
	            customers.add(customerId);
	        }
	        
	        Map<String, Long> result = new HashMap<>();
	        for (Map.Entry<String, Set<String>> entry : geozoneToCustomers.entrySet()) {
	            result.put(entry.getKey(), (long) entry.getValue().size());
	        }
	        
	        return result;
	    }

	    public static Map<String, Double> averageBuildDurationByGeozone(List<DataEntry> entries) {
	        Map<String, List<Double>> geozoneToDurations = new HashMap<>();
	        
	        for (DataEntry entry : entries) {
	            String geozone = entry.getGeozone();
	            double duration = parseBuildDuration(entry.getBuildduration());
	            
	            List<Double> durations = geozoneToDurations.get(geozone);
	            if (durations == null) {
	                durations = new ArrayList<>();
	                geozoneToDurations.put(geozone, durations);
	            }
	            durations.add(duration);
	        }
	        
	        Map<String, Double> result = new HashMap<>();
	        for (Map.Entry<String, List<Double>> entry : geozoneToDurations.entrySet()) {
	            List<Double> durations = entry.getValue();
	            double sum = 0;
	            for (Double duration : durations) {
	                sum += duration;
	            }
	            double average = durations.size() > 0 ? sum / durations.size() : 0.0;
	            result.put(entry.getKey(), average);
	        }
	        
	        return result;
	    }

	    public static Map<String, Set<String>> uniqueCustomersByGeozone(List<DataEntry> entries) {
	        Map<String, Set<String>> geozoneToCustomers = new HashMap<>();
	        
	        for (DataEntry entry : entries) {
	            String geozone = entry.getGeozone();
	            String customerId = entry.getCustomerId();
	            
	            Set<String> customers = geozoneToCustomers.get(geozone);
	            if (customers == null) {
	                customers = new HashSet<>();
	                geozoneToCustomers.put(geozone, customers);
	            }
	            customers.add(customerId);
	        }
	        
	        return geozoneToCustomers;
	    }

	    private static double parseBuildDuration(String buildDuration) {
	        try {
	            return Double.parseDouble(buildDuration.replaceAll("[^0-9.]", ""));
	        } catch (NumberFormatException e) {
	            return 0.0; // Default to 0 if parsing fails
	        }
	    }
}
