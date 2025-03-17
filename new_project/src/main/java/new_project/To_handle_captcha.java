package new_project;


	import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;
	public class To_handle_captcha {

	    public static void main(String[] args) throws IOException {
	        WebDriver driver = new ChromeDriver();// Initialize WebDriver

	        // Navigate to the CAPTCHA page
	        driver.get("https://qa.miostack.com/");
	        
	        // to click the captcha field
	        driver.findElement(By.id("recaptcha-anchor"));

	        // Locate CAPTCHA element and take a screenshot
	        WebElement captchaElement = driver.findElement(By.id("rc-imageselect"));
	        File src = captchaElement.getScreenshotAs(OutputType.FILE);
	        Files.copy(src, new File("captcha.png"));

	        // Use CAPTCHA-solving API to send and retrieve solution
	        String captchaSolution = solveCaptchaWithService("captcha.png");

	        // Input solution into CAPTCHA field
	        driver.findElement(By.id("rc-imageselect")).sendKeys(captchaSolution);

	        // Submit form
	        driver.findElement(By.id("recaptcha-verify-button")).click();
	    }

	    private static String solveCaptchaWithService(String filePath) {
	        // Implement API integration with the CAPTCHA-solving service
	        return "solved-captcha";
	    }
}
