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

public class SalesForce2 {

	public static void main(String[] args) throws IOException {

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://login.salesforce.com/");
//		3. Enter the username as "hari.radhakrishnan@qeagle.com"
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 username']")).sendKeys("hari.radhakrishnan@qeagle.com");
		
//		4. Enter the password as "Leaf@1234"
		driver.findElement(By.xpath("//input[@class='input r4 wide mb16 mt8 password']")).sendKeys("Leaf@1234");
		
//		5. Click on the login button
		driver.findElement(By.xpath("//input[@class='button r4 wide primary']")).click();
		
		System.out.println("Title of home page : "+driver.getTitle());
		
//		6. Click on the learn more option in the Mobile publisher
		driver.findElement(By.xpath("//button[@class='slds-button slds-button--neutral navButton newWindow uiButton']")).click();
		
//		7. Switch to the next window using Windowhandles.
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		WebDriver window = driver.switchTo().window(list.get(1));
		
//		8. Click on the confirm button on the redirecting page
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
				
		// Choose Your Role as Salesforce Architect
				driver.findElement(By.xpath("(//a[@class='roleMenu_no-underline'])[2]")).click();
				
				System.out.println("Title of salesforce architect page :"+driver.getTitle());
				
		// Get the Text(Summary) of Salesforce Architect 
				String summary = driver.findElement(By.xpath("//div[@class='cert-site_text slds-text-align--center Lh(1.5) Fz(16px) slds-container--center slds-p-bottom--large']")).getText();
				System.out.println("Salesforce Architect summary : ");
				System.out.println(summary);
				
	   // Get the List of Salesforce Architect Certifications Available
				List<WebElement> certificate = driver.findElements(By.xpath("//div[@class='credentials-card_title']/a"));
				for (WebElement names : certificate) {
					System.out.println("Certification names of Salesforce Architect : "+names.getText());
				}
				
       // Click on Application Architect 
				driver.findElement(By.xpath("//a[text()='Application Architect']")).click();
				System.out.println("Application Architect title :"+driver.getTitle());
				
	 // Take ScreenShot
				 //Take a snapshot
		        File source = driver.getScreenshotAs(OutputType.FILE);        
		        //Create folder to save the img file
		        File desc=new File("snaps/salesforce2.png");
		        //Merge source and destination
		        FileUtils.copyFile(source, desc);
		        
	// Get the List of Certifications available
		        List<WebElement> certificate2 = driver.findElements(By.xpath("//div[@class='credentials-card_title']/a"));
				for (WebElement names2 : certificate2) {
					System.out.println("Certification names of Application Architect : "+names2.getText());
				}
				
	//	Close Browser
				driver.quit();

	}

}





















