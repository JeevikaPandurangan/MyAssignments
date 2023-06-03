package marathon3;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SalesforceNewOpp extends ParentClass {
@Test(dataProvider = "testData")
	public void runSalesforceNewOpp(String oppName, String amount) {
		
		//Click View All and click Sales from App Launcher
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("Sales");
		driver.findElement(By.xpath("(//a[@class='slds-text-heading_small'])[3]")).click();
		
		//Click View All Key Deals in Key Deals 
		WebElement drop = driver.findElement(By.xpath("//a[@class='filterIcon slds-button slds-button--icon-more ']"));
		driver.executeScript("arguments[0].click();", drop);
		driver.findElement(By.xpath("//a[text()='All Opportunities']")).click();
		WebElement viewallKeyDeal = driver.findElement(By.xpath("//a[@aria-label='View All Key Deals']"));
		driver.executeScript("arguments[0].click();", viewallKeyDeal);
		
		//Click on New
		driver.findElement(By.xpath("//a[@title='New']")).click();
		
		//Give Opportunity Name  (From Excel)
		driver.findElement(By.xpath("(//input[@class='slds-input'])[4]")).sendKeys(oppName);
		
		//Select Type as New Customer and Lead Source as Partner Referral
		WebElement type = driver.findElement(By.xpath("(//button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value'])[2]"));
		driver.executeScript("arguments[0].click();", type);
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='New Customer']")).click();
		
		WebElement leadsource = driver.findElement(By.xpath("(//button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value'])[3]"));
		driver.executeScript("arguments[0].click();", leadsource);
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Partner Referral']")).click();
		
		//Give the Amount as 75000 (from Excel)
		driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys(amount);
		
		//Select Close Date as tomorrow
		driver.findElement(By.xpath("(//input[@class='slds-input'])[3]")).sendKeys("03/06/2023");
		
		//Select Stage as Needs Analysis
		WebElement stage = driver.findElement(By.xpath("(//button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value'])[1]"));
		driver.executeScript("arguments[0].click();", stage);
		driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='Needs Analysis']")).click();
		
		//Click on Primary Campaign  Source and Select the first option
		WebElement primarycampaign = driver.findElement(By.xpath("(//input[@class='slds-combobox__input slds-input'])[2]"));
		driver.executeScript("arguments[0].click();", primarycampaign);
		driver.findElement(By.xpath("(//li[@class='slds-listbox__item'])[2]")).click();
		
		//Click Save and Verify the opportunity is created"
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		
	}

@DataProvider
public String[][] testData() throws IOException {
	String[][] readData = ReadExcel.readData("SalesforceOpportunity");
	return readData;
	
}
}
