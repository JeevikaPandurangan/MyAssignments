package marathon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.sukgu.Shadow;

public class SalesForce1 {

	public static void main(String[] args) throws IOException {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
     //	Launch Salesforce application https://login.salesforce.com/
		driver.get("https://login.salesforce.com/");
		
     // Enter the username as "hari.radhakrishnan@qeagle.com"
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 username']")).sendKeys("hari.radhakrishnan@qeagle.com");
		
     // Enter the password as "Leaf@1234"
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 password']")).sendKeys("Leaf@1234");
		
     // Click on the login button
		driver.findElement(By.xpath("//input[@class='button r4 wide primary']")).click();
		
		System.out.println("Title of home page : "+driver.getTitle());
		
    // Click on the learn more option in the Mobile publisher
		driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral navButton newWindow uiButton']")).click();
		
    // Switch to the next window using Windowhandles.
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		WebDriver window = driver.switchTo().window(list.get(1));
		
    // Click on the confirm button on the redirecting page
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		
		System.out.println(window.getTitle());
		
	// Click Learning -- shadow root
		Shadow shadow = new Shadow(driver);
		shadow.setImplicitWait(20);
		shadow.findElementByXPath("//span[text()='Learning']").click();
		
	// And mouse hover on Learning On Trailhead
		WebElement mousehover = shadow.findElementByXPath("//span[text()='Learning on Trailhead']");
		Actions builder=new Actions(driver);
		builder.moveToElement(mousehover).perform();
		
	// Clilck on Salesforce Certifications
		WebElement scrolldown = shadow.findElementByXPath("//a[text()='Salesforce Certification']");
		Actions builder2=new Actions(driver);
		builder2.scrollToElement(scrolldown).perform();
		scrolldown.click();
		
		System.out.println("Title of the sales certification page :"+ driver.getTitle());
		
	// Click on first resulting Ceritificate
		WebElement scrolldown2 = driver.findElement(By.xpath("(//div[@class='slds-size--1-of-1 slds-large-size--1-of-3 slds-p-horizontal--medium slds-p-vertical--medium'])[3]"));
		Actions builder3=new Actions(driver);
		builder3.scrollToElement(scrolldown2).perform();
		
	// Print the Certifications available for Administrator Certifications (List)
		List<WebElement> names = driver.findElements(By.xpath("//div[@class='credentials-card_title']/a"));
		for (WebElement output : names) {
			System.out.println("Administrator Credentials certification list : "+output.getText());
		}
		
		 //Take a snapshot
        File source = driver.getScreenshotAs(OutputType.FILE);        
        //Create folder to save the img file
        File desc=new File("snaps/salesforce1.png");
        //Merge source and destination
        FileUtils.copyFile(source, desc);
        
        driver.quit();
      
		
		
		
		
		

	}

}
