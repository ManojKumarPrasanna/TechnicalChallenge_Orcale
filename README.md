# TechnicalChallenge_Orcale
TechnicalChallenge_Orcale

Steps To Execute TestNG Tests with Maven
1. git clone https://github.com/ManojKumarPrasanna/TechnicalChallenge_Orcale.git
2. cd TechnicalChallenge_Orcale
3. mvn clean
4. mvn test 
  
Project Overview 
1. base
DataEntry.Java - For handling different data types
2. utilities
CSVReader.java - For reading excel data from csv files
DataParser.java - For parsing input string into entries
ReportGenerator.java - For calculating uniqueCustomerCountByContract, uniqueCustomerCountByGeozone, averageBuildDurationByGeozone, uniqueCustomersByGeozone
3. application_tests
Application.java - Contains Main method with Input String
ExcelDataIntegrationTest.java - TestNG class with few test methods for CSV file input
ReportGeneratorTest.java - TestNG class with few test methods for Input String
