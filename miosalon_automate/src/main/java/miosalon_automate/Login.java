package miosalon_automate;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Login {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeOptions option = new ChromeOptions();
		option.addArguments("_disable-notifications", "start-maximized");
		ChromeDriver driver=new ChromeDriver(option);
		System.out.println("Let's start with login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://qa.miostack.com/");
		driver.findElement(By.xpath("//button[text()= 'Login']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("krishnaecrystal123@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
		Thread.sleep(10000);
		//driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='Akash']")).click();
		Thread.sleep(20000);
		driver.findElement(By.xpath("//button[text()='Skip']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//i[@class='font-icon fal fa-cog']")).click();
		Thread.sleep(2000);
		// add Expense type
		driver.findElement(By.xpath("//div[contains(text(),'Expense Management')]")).click();
		driver.findElement(By.xpath("//button[text()='Add Expense Type']")).click();
		WebElement expensetype = driver.findElement(By.xpath("//input[@id='expenseId']"));	
		// 8-character random word
		String typevalue = RandomStringUtils.randomAlphabetic(8); 
		expensetype.sendKeys(typevalue);
		driver.findElement(By.xpath("//button[@class='btn pull-right ng-scope']")).click();
		
		
		 // Locate the toggle button (adjust the locator to match your use case)
        WebElement toggleButton = driver.findElement(By.xpath("//input[@id='expensePreviousDate']"));

        // Check if the toggle button is enabled
        if (toggleButton.isEnabled()) {
            System.out.println("The toggle button is enabled.");
        } else {
            System.out.println("The toggle button is disabled.");
        }
        //to verify the data is saved or not / added or not
        Thread.sleep(2000); 

        // Locate the table or element where the data appears
        WebElement addedData = driver.findElement(By.xpath("(//table[@class='table']//tr[@class='cursor-pointer ng-scope']//td)[1]"));

        // Compare the displayed data with the input
        if (typevalue.equals(addedData.getText())) {
            System.out.println("Data is successfully added: " + addedData.getText());
        } else {
            System.out.println("Data verification failed. Expected: " + typevalue + ", Found: " + addedData.getText());
        }
        
        
	}

}
