package marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.sukgu.Shadow;

public class ServiceNow {

	public static void main(String[] args) throws IOException {
	

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://dev164753.service-now.com");
   // Enter the username
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
		
  // Enter the password
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("e-7JVd^vwFU2");
		
  // Click on the login button
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		
		System.out.println("Title of Home page :"+driver.getTitle());
		
  // Click-All Enter Service catalog in filter navigator and press enter or Click the ServiceCatalog
		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(30);
		shadow.findElementByXPath("//div[@class='sn-polaris-tab can-animate polaris-enabled']").click();
		
		WebElement filter = shadow.findElementByXPath("//div[@class='sn-polaris-nav-header-filter can-animate']//input");
		filter.sendKeys("Service catalog");
		filter.sendKeys(Keys.ENTER);
		
		System.out.println("Title of service catalogue page :"+driver.getTitle());
		
  // Click on  mobiles
		WebElement mobile1 = shadow.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(mobile1);
		WebElement mobile = driver.findElement(By.xpath("//a[text()='Mobiles']"));
		
		mobile.click();
		
		System.out.println("Title of mobile page :"+driver.getTitle());
  // Select Apple iphone6s
		driver.findElement(By.xpath("(//a[@class='service_catalog'])[1]")).click();
		
		System.out.println("Title of Apple iphone page :"+driver.getTitle());
		
  // Update All Mandatory Options
		driver.findElement(By.xpath("(//span[@class='input-group-radio']//label)[2]")).click();
		
		WebElement select = driver.findElement(By.xpath("//select[@class='form-control cat_item_option ']"));
		Select dropdown = new Select(select);
		dropdown.selectByValue("500MB");
		
		driver.findElement(By.xpath("(//span[@class='input-group-radio']//label)[4]")).click();
		
		driver.findElement(By.xpath("//label[text()='256 GB [add $100.00]']")).click();
			
  // Click Order now option
		
		driver.findElement(By.xpath("//button[@class='request_catalog_button_with_icon btn cat-btn btn-primary']")).click();
		
		System.out.println("Order title :"+driver.getTitle());
  // Take ScreenShot
		 //Take a snapshot
        File source = driver.getScreenshotAs(OutputType.FILE);        
        //Create folder to save the img file
        File desc=new File("snaps/servicenow.png");
        //Merge source and destination
        FileUtils.copyFile(source, desc);
		
  // Verify order is placed and get the request number"
        String success = driver.findElement(By.xpath("//div[@class='notification notification-success']")).getText();
        if(success.contains("Thank you")) {
        	System.out.println("Order placed successfully");
        }
        else {
        	System.out.println("Order not placed");
        }
        
        String reqnum = driver.findElement(By.xpath("//a[@class='linked requestItemUrl']")).getText();
        System.out.println("Request Number :"+ reqnum);
        
  // Close Browser
        driver.close();
	}

}
























