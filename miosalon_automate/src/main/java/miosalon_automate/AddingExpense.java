package miosalon_automate;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AddingExpense {

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
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("vinothpandian555@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("1");
		Thread.sleep(10000);
		//driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[text()='vishnu priya']")).click();
		Thread.sleep(20000);
		driver.findElement(By.xpath("//button[text()='Skip']")).click();
		Thread.sleep(2000);

for (int i = 0; i < 100; i++) {
    // Your code here
	

	driver.findElement(By.xpath("//div[@class='directAdd']")).click();
	driver.findElement(By.xpath("//span[text()='Expense']")).click();
	driver.findElement(By.xpath("//button[@title='Choose expense name']")).click();
	driver.findElement(By.xpath("//span[text()='tea']")).click();
	driver.findElement(By.xpath("(//button[@class='btn dropdown-toggle bs-placeholder btn-default'])[last()]")).click();
	driver.findElement(By.xpath("//span[text()='Cash']")).click();
	Thread.sleep(2000);
	WebElement expenseamount = driver.findElement(By.xpath("//input[@data-ng-model='expense.amount']"));
	expenseamount.click();
	expenseamount.sendKeys("100");
	driver.findElement(By.xpath("//input[@id='exp-date']")).click();
	driver.findElement(By.xpath("(//table[@class='table-condensed']//td[text()='21'])[3]")).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath("(//div[@class='modal-footer']/button[@class='btn pull-right m-l'])[last()]")).click();
	driver.findElement(By.xpath("(//button[text()='×'])[3]")).click();
	Thread.sleep(5000);
	// code ends
    System.out.println("Iteration " + (i + 1));
}

		driver.quit();
		
	}

}
