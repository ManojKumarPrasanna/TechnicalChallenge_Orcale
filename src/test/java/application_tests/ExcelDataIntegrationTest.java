package application_tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.DataEntry;
import utilities.CSVReader;
import utilities.ReportGenerator;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ExcelDataIntegrationTest {
	List<DataEntry> entries;
	Map<String, Long> result;
	
	@BeforeMethod
    public void setUp() throws IOException {
        String filePath = "src/test/java/test_data/data.csv";
        entries = CSVReader.readFromCSV(filePath);
        // Optionally limit to the first 10 rows for testing purposes
        if (entries.size() > 10) {
            entries = entries.subList(0, 10);
        }
    }

    @Test
    public void testUniqueCustomerCountByContract() {	
        result = ReportGenerator.uniqueCustomerCountByContract(entries);
        assertEquals(result.get("2345").longValue(), 3);
        assertEquals(result.get("2346").longValue(), 2);
        assertEquals(result.get("2347").longValue(), 2);
        assertEquals(result.get("2348").longValue(), 1);
        assertEquals(result.get("2349").longValue(), 1);
    }

    @Test
    public void testUniqueCustomerCountByGeozone() {
        result = ReportGenerator.uniqueCustomerCountByGeozone(entries);
        assertEquals(result.get("us_east").longValue(), 2);
        assertEquals(result.get("us_west").longValue(), 4);
        assertEquals(result.get("eu_west").longValue(), 3);
    }

    // Additional tests for other methods
    @Test
    public void testAverageBuildDurationByGeozone() {
    	Map<String, Double> result = ReportGenerator.averageBuildDurationByGeozone(entries);
        assertEquals(result.get("us_east"), 3350.0);
        assertEquals(result.get("us_west"), (2211.0 + 2221.0 + 1987.0 + 2089.0) / 4, 0.01);
        assertEquals(result.get("eu_west"), (4322.0 + 4122.0 + 4398.0) / 3, 0.01);
    }

    @Test
    public void testEmptyEntries() {
        entries = List.of();
        result = ReportGenerator.uniqueCustomerCountByContract(entries);
        assertTrue(result.isEmpty());

        result = ReportGenerator.uniqueCustomerCountByGeozone(entries);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleEntry() {
        entries = List.of(new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s"));

        result = ReportGenerator.uniqueCustomerCountByContract(entries);
        assertEquals(result.get("1001").longValue(), 1);

        result = ReportGenerator.uniqueCustomerCountByGeozone(entries);
        assertEquals(result.get("us_west").longValue(), 1);

    }

    @Test
    public void testDuplicateEntries() {
        entries = List.of(
            new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s"),
            new DataEntry("1", "1001", "us_west", "TeamA", "ProjectX", "120s")
        );

        result = ReportGenerator.uniqueCustomerCountByContract(entries);
        assertEquals(result.get("1001").longValue(), 1);

        result = ReportGenerator.uniqueCustomerCountByGeozone(entries);
        assertEquals(result.get("us_west").longValue(), 1);

    }

}
