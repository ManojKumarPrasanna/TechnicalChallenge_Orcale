# TechnicalChallenge_Orcale
TechnicalChallenge_Orcale

Steps To Execute TestNG Tests with Maven
Clone repo - 
  git clone <>
  cd TechnicalChallenge_Orcale
Maven Commands - 
  mvn clean
  mvn test 

Project Overview 
1. base
   DataEntry.Java - For handling different data types
2. utilities
   CSVReader.java - For reading excel data from csv files
   DataParser.java - For parsing input string into entries
   ReportGenerator.java - For calculating
                           1. uniqueCustomerCountByContract
                           2. uniqueCustomerCountByGeozone
                           3. averageBuildDurationByGeozone
                           4. uniqueCustomersByGeozone
4. application_tests
   Application.java - Contains Main method with Input String
   ExcelDataIntegrationTest.java - TestNG class with few test methods for CSV file input
   ReportGeneratorTest.java - TestNG class with few test methods for Input String  
     
