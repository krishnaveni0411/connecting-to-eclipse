package new_project;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.common.io.Files;

public class Lauch_and_login_into_the_account {
		
		public static void main(String[] args) throws InterruptedException, IOException  {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("_disable-notifications", "start-maximized");
			ChromeDriver driver=new ChromeDriver(option);
			System.out.println("Let's start with login");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get("https://qa.miostack.com/");
			driver.findElement(By.xpath("//button[text()= 'Login']")).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("krishnaecrystal123@gmail.com");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
			System.out.println("now clicking the captcha");
			 // Locate CAPTCHA element and take a screenshot
	       // WebElement captchaElement = driver.findElement(By.id("recaptcha-anchor"));
	     //   File src = captchaElement.getScreenshotAs(OutputType.FILE);
	      //  Files.copy(src, new File("captcha.png"));

	        // Use CAPTCHA-solving API to send and retrieve solution
	     //   String captchaSolution = solveCaptchaWithService("captcha.png");

	        // Input solution into CAPTCHA field
	      //  driver.findElement(By.id("rc-imageselect")).sendKeys(captchaSolution);

	        // Submit form
	     //   driver.findElement(By.id("recaptcha-verify-button")).click();
	    
	    
	    driver.findElement(By.xpath("//button[text()='Login']")).click();

	}

		private static String solveCaptchaWithService(String filePath) {
	        // Implement API integration with the CAPTCHA-solving service
	        return "solved-captcha";
	    }
		
}
