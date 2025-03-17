package reportgeneration;

import java.time.Duration;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddExpense {
	ChromeDriver driver;

	 

	@BeforeTest
    public void setup() {
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-notifications", "start-maximized");
        driver = new ChromeDriver(option);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver.get("https://qa.miostack.com/");
        driver.findElement(By.xpath("//button[text()= 'Login']")).click();
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("krishnavenistaffshowingonline@waffor.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[text()='Login']")).click();
    }

        @Test(priority = 2, dependsOnMethods = "login")
        public void skipTour() throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Skip']")));
            driver.findElement(By.xpath("//button[text()='Skip']")).click();
        }
        @Test(priority = 3, dependsOnMethods = "skipTour")
        public void addExpense() throws InterruptedException {

           // Adding expense in expanse managment
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
            	// to get the print in the report.
            	Reporter.log("The toggle button is enabled.");
                System.out.println("The toggle button is enabled.");
            } else {
            	Reporter.log("The toggle button is disabled.");
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
driver.findElement(By.xpath("//button[text()='×']")).click();
        }

        @Test(priority = 4, dependsOnMethods = "addExpense")
        public void addExpenseForPresentDate() throws InterruptedException {
            // adding expense against the on the date. for present date alone.

        	//driver.findElement(By.xpath("(//button[text()='×'])[3]")).click();
        	//Thread.sleep(5000);
        	//clicking the bell icon for removing the new update flag.
        	//driver.findElement(By.xpath("//a[@id='dd-notification']")).click();

            driver.findElement(By.xpath("//div[@class='directAdd']")).click();
        	driver.findElement(By.xpath("//span[text()='Expense']")).click();
        	driver.findElement(By.xpath("//button[@title='Choose expense name']")).click();
        	driver.findElement(By.xpath("(//div[@id='bs-div']//ul[@class='dropdown-menu inner']/li[@data-original-index='2'])[4]")).click();
        	driver.findElement(By.xpath("(//button[@class='btn dropdown-toggle bs-placeholder btn-default'])[last()]")).click();
        	driver.findElement(By.xpath("//span[text()='Cash']")).click();
        	Thread.sleep(2000);
        	WebElement expenseamount = driver.findElement(By.xpath("//input[@data-ng-model='expense.amount']"));
        	expenseamount.click();
        	expenseamount.sendKeys("100");
        	driver.findElement(By.xpath("//input[@id='exp-date']")).click();
        	driver.findElement(By.xpath("(//table[@class='table-condensed']//td[@class='today active start-date active end-date available'])[last()]")).click();
        	Thread.sleep(5000);
        	driver.findElement(By.xpath("(//div[@class='modal-footer']/button[@class='btn pull-right m-l'])[last()]")).click();
        	//driver.findElement(By.xpath("(//button[text()='×'])[3]")).click();

           driver.quit();

        
        }}
