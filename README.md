# AbsaTestAutomationAssessment
Absa Test Automation Assessment

Automated test cases

Task1 = Automate tests against API: https://dog.ceo/dog-api/

Task2 = Automate tests against website: http://www.way2automation.com/angularjs-protractor/webtables/

Files: See files attached in this project.

Pre-requisites:

TASK1:

Install Eclipse
Create a Maven Project
Add TestNG dependency in POM.xml file
Add Rest Assured dependency in POM.xml file
Add POI dependency in POM.xml file
Add Extent report dependency in POM.xml file
Add Selenium server dependency in POM.xml file


Section A: Tasks

TASK1 Solution: 
For automating the API,I have choosen Mavenised TestNg framework and downloaded the Rest Assured jars. REST Assured brings the simplicity of using these languages into the Java domain. I have passed the test data through an excel and parameters, For passing parameters through an excel I have used POI jars and DataProvider annotations in TestNG. I have attached Absa_API_Test.java file and you can execute the file by using Run as TestNg project through eclipse and generated the reports by using the Extent reports. Please find the attached test report for your reference.

TASK2 Solution:

For automating the web application I have choosen Mavenised TestNg framework and we can easily download all the required Jar files by adding the dependency in the POM.XML file. I have created below two programs:
• AddUserWithExcel.java  ----------- Passing the test data through an Excel.
• AddUserWithParameters.java ------- Passing the test data through parameters.

For passing parameters through an excel I have used POI jars and DataProvider annotations in TestNG. And Generated the reports by using the Extent reports and generated the test report with screeshots as evidence and also please find the attached test report for for reference.


Note:
• We can also execute test cases from command prompt by using the maven commands.
• We can also trigger/schedule the test cases through Jenkins by creating the batch file.

References:
TestNG dependecy link: https://mvnrepository.com/artifact/org.testng/testng/6.14.3
Rest Assured dependency: https://mvnrepository.com/artifact/io.rest-assured/rest-assured/4.3.0
POI dependency: https://mvnrepository.com/artifact/org.apache.poi/poi/4.1.2 and https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml/4.1.2
Extent report dependency: https://mvnrepository.com/artifact/com.relevantcodes/extentreports/2.41.2
Selenium dependency: https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.141.59

