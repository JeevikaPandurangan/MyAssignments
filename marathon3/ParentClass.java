package marathon3;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
public class ParentClass {
    public RemoteWebDriver driver;
    public String filename;
    @Parameters({"browser","url","username","password"})
	@BeforeMethod
	public void preCondition(String browser, String url, String uname, String password) {
		
    	if(browser.equalsIgnoreCase("Chrome")) {
    		ChromeOptions option = new ChromeOptions();
    		option.addArguments("--disable-notifications");
			driver = new ChromeDriver(option);
	    	}
	    	else if(browser.equalsIgnoreCase("Edge")) {
	    		EdgeOptions option = new EdgeOptions();
	    		option.addArguments("--disable-notifications");
	    		driver = new EdgeDriver(option);
	    	}
    	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        //Launch Salesforce application https://login.salesforce.com/
		driver.get(url);
		
		//Login to Salesforce with your username and password
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 username']")).sendKeys(uname);
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@class='button r4 wide primary']")).click();
		System.out.println("Title of home page : "+driver.getTitle());
		
		//Click on the App Launcher (dots)
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
	}
    
    @DataProvider
    public String[][] testData() throws IOException {
    	String[][] readData = ReadExcel.readData(filename);
    	return readData;
    	
    }
    
	
	@AfterMethod
	public void postCondition() {
		driver.close();
	}
	

}
