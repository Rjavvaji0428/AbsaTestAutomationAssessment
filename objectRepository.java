package Absa_Test_Assessment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class objectRepository {
	WebDriver driver;
	static By AddUser= By.xpath("//button[@type='add']");
    static By firstName = By.xpath("//*[@name='FirstName']");
    static By lastName =By.name("LastName");
    static By userName = By.name("UserName");
    static By password = By.name("Password");
    static By radioButtonAAA = By.xpath("//*[@name = 'optionsRadios' and @value='15']");
    static By radioButtonBBB = By.xpath("//*[@name = 'optionsRadios' and @value='16']");
    static By roleid= By.xpath("//select[@name='RoleId']");
    static By email = By.xpath("//*[@name = 'Email']");
    static By mobilePhone = By.xpath("//*[@name = 'Mobilephone']");
    static By SaveButton = By.xpath("//*[@ng-click='save(user)']");

}
