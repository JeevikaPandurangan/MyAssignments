package marathon3;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SalesforceQuestion extends ParentClass {
@Test(dataProvider = "testData")
	public void runSalesforceQuestion(String questions, String details) {
		
		//Click View All
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		
		//Type Content on the Search box
		driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("Content");
		
		//Click the Content Link
		driver.findElement(By.xpath("//a[@class='slds-text-heading_small']")).click();
		
		//Click on Chatter Tab
		WebElement chatter = driver.findElement(By.xpath("//a[@title='Chatter']"));
		driver.executeScript("arguments[0].click();", chatter);
		
		//Verify the Chatter title on the page
		System.out.println("Title of Current page : "+driver.getTitle());
		
		//Click the Question tab
		driver.findElement(By.xpath("//a[@title='Question']")).click();
		
		//Type Question with data (coming from Excel)
		driver.findElement(By.xpath("//textarea[@class='cuf-questionTitleField textarea']")).sendKeys(questions);
		
		//Type Details with data (coming from Excel)
		driver.findElement(By.xpath("//div[@class='ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow']")).sendKeys(details);
		
		//Click Ask
		driver.findElement(By.xpath("//button[text()='Ask']")).click();
		
		//Confirm the question appears
		String question = driver.findElement(By.xpath("//div[@class='cuf-body cuf-questionTitle forceChatterFeedBodyQuestionWithoutAnswer']")).getText();
		String detail = driver.findElement(By.xpath("//div[@class='cuf-body cuf-questionBody forceChatterFeedBodyQuestionWithoutAnswer']")).getText();
		System.out.println("Question : "+question);
		System.out.println("Detail : "+detail);
		
		
	}

@DataProvider
public String[][] testData() throws IOException {
	String[][] readData = ReadExcel.readData("Salesforcequestion");
	return readData;
	
}
}
