package Absa_Test_Assessment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class AddUserWithExcel {
	public WebDriver driver;
    public static XSSFWorkbook workbook;
    public static XSSFSheet worksheet;
    public static DataFormatter formatter= new DataFormatter();
    public static String file_location = System.getProperty("user.dir")+"//AddUser.xlsx";
    static String SheetName= "Sheet1";
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

@BeforeTest  
   public void testCase() throws InterruptedException 
  {
	System.setProperty("webdriver.chrome.driver", ".\\Drivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
	driver.manage().window().maximize();
    driver.get("http://www.way2automation.com/angularjs-protractor/webtables/");
    Thread.sleep(3000);
    tableValidation();
   
  }


@Test(priority=1,dataProvider="ReadFromExcel")
   public void userWithExcel(String Firstname, String LastName, String User, String Password, String rolename, String email, String phone, String Company) throws InterruptedException, IOException
  {
	test.log(LogStatus.PASS, "You are on the user list table");
    driver.findElement(objectRepository.AddUser).click();
    test.log(LogStatus.PASS, "Enter the user details");
    driver.findElement(objectRepository.firstName).clear();
    driver.findElement(objectRepository.firstName).sendKeys(Firstname);
    test.log(LogStatus.PASS, "Enter Firstname: "+Firstname);
    driver.findElement(objectRepository.lastName).clear();
    driver.findElement(objectRepository.lastName).sendKeys(LastName);
    test.log(LogStatus.PASS, "Enter LastName: "+LastName);
    driver.findElement(objectRepository.userName).clear();
    driver.findElement(objectRepository.userName).sendKeys(User);
    test.log(LogStatus.PASS, "Enter UserName: "+User);
    driver.findElement(objectRepository.password).clear();
    driver.findElement(objectRepository.password).sendKeys(Password);
    test.log(LogStatus.PASS, "Enter Password: "+Password);
    driver.findElement(By.xpath("//*[@name = 'optionsRadios' and @value="+Company+"]")).click();
    WebElement role = driver.findElement(objectRepository.roleid);
    Select select =new Select(role);
    select.selectByVisibleText(rolename);
    test.log(LogStatus.PASS, "Enter rolename: "+rolename);
    driver.findElement(objectRepository.email).clear();
    driver.findElement(objectRepository.email).sendKeys(email);
    test.log(LogStatus.PASS, "Enter email: "+email);
    driver.findElement(objectRepository.mobilePhone).clear();
    driver.findElement(objectRepository.mobilePhone).sendKeys(phone);
    test.log(LogStatus.PASS, "Enter phone number: "+phone);
    Thread.sleep(2000);
    test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "Data Entered");
    driver.findElement(objectRepository.SaveButton).click();
    Thread.sleep(2000);
    test.log(LogStatus.PASS,test.addScreenCapture(capture(driver))+ "User Added successfully");
  }



@AfterTest
 public void closeBrowser() {
	driver.quit();
}



@AfterClass
public void endTest()
{
report.endTest(test);
report.flush();
}


public void tableValidation()  {
	 
	 WebElement mytable = driver.findElement(By.xpath("html/body/table/tbody"));
	 boolean table = mytable.isDisplayed();
	 Assert.assertEquals(table, true);
	 System.out.println("You are on the User List Table");
	 
	 
}


public String capture(WebDriver driver) throws IOException {
File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
File Dest = new File(".//" + System.currentTimeMillis() + ".png");
String errflpath = Dest.getAbsolutePath();
FileUtils.copyFile(scrFile, Dest);
return errflpath;
}


@DataProvider
public static Object[][] ReadFromExcel() throws IOException
{
FileInputStream fileInputStream= new FileInputStream(file_location);
    workbook = new XSSFWorkbook (fileInputStream); 
    worksheet=workbook.getSheet(SheetName);
    XSSFRow Row=worksheet.getRow(0);   
 
    int RowNum = worksheet.getPhysicalNumberOfRows();
    int ColNum= Row.getLastCellNum(); 
     
    Object Data[][]= new Object[RowNum-1][ColNum];
     
        for(int i=0; i<RowNum-1; i++)
        {  
            XSSFRow row= worksheet.getRow(i+1);
             
            for (int j=0; j<ColNum; j++)
            {
                if(row==null)
                    Data[i][j]= "";
                else
                {
                    XSSFCell cell= row.getCell(j);
                    if(cell==null)
                        Data[i][j]= "";
                    else
                    {
                        String value=formatter.formatCellValue(cell);
                        Data[i][j]=value;
                    }
                }
            }
        }

    return Data;
}
} 
   
