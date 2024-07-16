package application_tests;
import org.testng.annotations.Test;
import base.DataEntry;
import utilities.DataParser;
import utilities.ReportGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ReportGeneratorTest {
	List<DataEntry> entries ;
	Map<String, Long> result;

    @Test
    public void testUniqueCustomerCountByContract() {	
    	entries = DataParser.parse(Application.input);
        result = ReportGenerator.uniqueCustomerCountByContract(entries);
        assertEquals(result.get("2345").longValue(), 3);
    }

    @Test
    public void testUniqueCustomerCountByGeozone() {
    	entries = DataParser.parse(Application.input);
        result = ReportGenerator.uniqueCustomerCountByGeozone(entries);
        assertEquals(result.get("us_east").longValue(), 1);
        assertEquals(result.get("us_west").longValue(), 2);
    }

    // Additional tests for other methods
    @Test
    public void testAverageBuildDurationByGeozone() {   
    	entries = DataParser.parse(Application.input);
        Map<String, Double> result = ReportGenerator.averageBuildDurationByGeozone(entries);
        assertEquals(result.get("us_east"), 3445.0);
        assertEquals(result.get("us_west"), (22118.0 + 2221.0) / 2);
        assertEquals(result.get("eu_west"), (43228.0 + 41228.0) / 2);
    }

    @Test
    public void testUniqueCustomersByGeozone() {
    	entries = DataParser.parse(Application.input);
        Map<String, Set<String>> result = ReportGenerator.uniqueCustomersByGeozone(entries);
        assertTrue(result.get("us_east").contains("2343225"));
        assertTrue(result.get("us_west").contains("1223456"));
        assertTrue(result.get("us_west").contains("1233456"));
        assertTrue(result.get("eu_west").contains("3244332"));
        assertTrue(result.get("eu_west").contains("3244132"));
    }

    // Additional test cases to cover edge scenarios

    @Test
    public void testUniqueCustomerCountByContract_emptyEntries() {
        entries = new ArrayList<>();
        result = ReportGenerator.uniqueCustomerCountByContract(entries);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testUniqueCustomerCountByGeozone_emptyEntries() {
        entries = new ArrayList<>();
        result = ReportGenerator.uniqueCustomerCountByGeozone(entries);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAverageBuildDurationByGeozone_emptyEntries() {
        List<DataEntry> entries = new ArrayList<>();
        Map<String, Double> result = ReportGenerator.averageBuildDurationByGeozone(entries);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testUniqueCustomersByGeozone_emptyEntries() {
        List<DataEntry> entries = new ArrayList<>();
        Map<String, Set<String>> result = ReportGenerator.uniqueCustomersByGeozone(entries);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testUniqueCustomerCountByContract_singleEntry() {
        List<DataEntry> entries = Arrays.asList(new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s"));
        Map<String, Long> result = ReportGenerator.uniqueCustomerCountByContract(entries);
        assertEquals(result.get("1001").longValue(), 1);
    }

    @Test
    public void testUniqueCustomerCountByGeozone_singleEntry() {
        List<DataEntry> entries = Arrays.asList(new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s"));
        Map<String, Long> result = ReportGenerator.uniqueCustomerCountByGeozone(entries);
        assertEquals(result.get("us_west").longValue(), 1);
    }

    @Test
    public void testAverageBuildDurationByGeozone_singleEntry() {
        List<DataEntry> entries = Arrays.asList(new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s"));
        Map<String, Double> result = ReportGenerator.averageBuildDurationByGeozone(entries);
        assertEquals(result.get("us_west"), 120.0);
    }

    @Test
    public void testUniqueCustomersByGeozone_singleEntry() {
        List<DataEntry> entries = Arrays.asList(new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s"));
        Map<String, Set<String>> result = ReportGenerator.uniqueCustomersByGeozone(entries);
        assertTrue(result.get("us_west").contains("1"));
    }

    @Test
    public void testUniqueCustomerCountByContract_duplicateEntries() {
        List<DataEntry> entries = Arrays.asList(
            new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s"),
            new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s")
        );
        Map<String, Long> result = ReportGenerator.uniqueCustomerCountByContract(entries);
        assertEquals(result.get("1001").longValue(), 1);
    }

    @Test
    public void testUniqueCustomerCountByGeozone_duplicateEntries() {
        List<DataEntry> entries = Arrays.asList(
            new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s"),
            new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s")
        );
        Map<String, Long> result = ReportGenerator.uniqueCustomerCountByGeozone(entries);
        assertEquals(result.get("us_west").longValue(), 1);
    }

    @Test
    public void testAverageBuildDurationByGeozone_duplicateEntries() {
        List<DataEntry> entries = Arrays.asList(
            new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s"),
            new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s")
        );
        Map<String, Double> result = ReportGenerator.averageBuildDurationByGeozone(entries);
        assertEquals(result.get("us_west"), 120.0);
    }

    @Test
    public void testUniqueCustomersByGeozone_duplicateEntries() {
        List<DataEntry> entries = Arrays.asList(
            new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s"),
            new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s")
        );
        Map<String, Set<String>> result = ReportGenerator.uniqueCustomersByGeozone(entries);
        assertTrue(result.get("us_west").contains("1"));
    }

    @Test
    public void testAverageBuildDurationByGeozone_zeroDuration() {
        List<DataEntry> entries = Arrays.asList(
            new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "0s"),
            new DataEntry("2", "1001", "us_west", "TeamA", "ProjectX", "0s")
        );
        Map<String, Double> result = ReportGenerator.averageBuildDurationByGeozone(entries);
        assertEquals(result.get("us_west"), 0.0);
    }

}
