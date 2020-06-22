package Absa_Test_Assessment;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Absa_API_Test {
	public static XSSFWorkbook workbook;
    public static XSSFSheet worksheet;
    public static DataFormatter formatter= new DataFormatter();
    public static String file_location = System.getProperty("user.dir")+"//AddUser.xlsx";
    static String SheetName= "Sheet2";
    public  String Res;
    public int DataSet=-1;
    ExtentTest test;
    ExtentReports report;
	
    @BeforeClass
    public void startTest()
    {
    report = new ExtentReports(System.getProperty("user.dir")+"//ExtentReportResults.html");
    test = report.startTest("AddUser");
    }
	@Test (priority=1)
    public void listOfDogBreed() {
		
	test=report.startTest("listOfDogBreed","Displays the List of Dogs breed");
 
    RestAssured.baseURI = "https://dog.ceo/api/breeds/list/all";
	
	RequestSpecification httpRequest = RestAssured.given();
	
	Response response = httpRequest.request(Method.GET);
	
	String responseBody = response.getBody().asString();
	
	test.log(LogStatus.PASS, "List of all dog breeds: "+responseBody);
	
	System.out.println("Response Body"+responseBody);
	int statusCode = response.statusCode();
	System.out.println("Status Code is: "+statusCode);
	Assert.assertEquals(statusCode, 200);
	test.log(LogStatus.PASS, "Status Code is: "+statusCode);
	
	String StatusLine = response.getStatusLine();
	System.out.println("StatusLine: "+StatusLine);
	Assert.assertEquals(StatusLine, "HTTP/1.1 200 OK");

}
	
	@Test(priority=2,dataProvider="ReadBreed")
	void verifyBreedRetriever(String BreedName) 
	{
		test=report.startTest("verifyBreedRetriever","It verifies whether Retriever available in the list");
		
		RestAssured.baseURI = "https://dog.ceo/api/breeds/list/all";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response = httpRequest.request(Method.GET);
		
		String responseBody = response.getBody().asString();
		
		System.out.println("Response Body"+responseBody);
						
		Assert.assertTrue(responseBody.contains(BreedName));
		
		test.log(LogStatus.PASS, "List contains the dog breed: "+BreedName);
		
	}
	
	@DataProvider
	public static Object[][] ReadBreed() throws IOException
	{
	FileInputStream fileInputStream= new FileInputStream(file_location); //Excel sheet file location get mentioned here
	    workbook = new XSSFWorkbook (fileInputStream); //get my workbook 
	    worksheet=workbook.getSheet(SheetName);// get my sheet from workbook
	    XSSFRow Row=worksheet.getRow(0);     //get my Row which start from 0   
	 
	    int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
	    int ColNum= Row.getLastCellNum(); // get last ColNum 
	     
	    Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
	     
	        for(int i=0; i<RowNum-1; i++) //Loop work for Rows
	        {  
	            XSSFRow row= worksheet.getRow(i+1);
	             
	            for (int j=0; j<ColNum; j++) //Loop work for colNum
	            {
	                if(row==null)
	                    Data[i][j]= "";
	                else
	                {
	                    XSSFCell cell= row.getCell(j);
	                    if(cell==null)
	                        Data[i][j]= ""; //if it get Null value it pass no data 
	                    else
	                    {
	                        String value=formatter.formatCellValue(cell);
	                        Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
	                    }
	                }
	            }
	        }

	    return Data;
	}
	
	@Test(priority=3)
	public void Sub_breedList() 
	{
		test=report.startTest("Sub_breedList","It only SubBreed list for the particular dogs breed");
		
		RestAssured.baseURI = "https://dog.ceo/api/breed";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		String SubBreed = "/retriever/list";
		
		Response response = httpRequest.request(Method.GET,SubBreed);
		
		String responseBody = response.getBody().asString();
		
		System.out.println("Response Body"+responseBody);
		
		test.log(LogStatus.PASS, "Sub Breed List of "+SubBreed+" : "+responseBody);
		
	}
	
	@Test(priority=4,dataProvider = "subBreed")
	
	void getRandomImage(String BreedName) 
	{
		test=report.startTest("getRandomImage","It displays the random image of the dog golden");
		
		RestAssured.baseURI = "https://dog.ceo/api/breed/";
		
		RequestSpecification httpRequest = RestAssured.given();
				
		Response response = httpRequest.request(Method.GET,BreedName);
		
		String responseBody = response.getBody().asString();
		
		System.out.println("Response Body"+responseBody);
		
		test.log(LogStatus.PASS, "Fetches random image of Sub breed Golden: "+responseBody);
	}
	
	@DataProvider(name ="subBreed")
	  String [][] subBreed()
	  {
		String subBreed[][]={ {"retriever/golden/images/random"}};
		 return(subBreed); 
	  }
@AfterClass
	public void endTest()
	{
	report.endTest(test);
	report.flush();
	}
	
}
