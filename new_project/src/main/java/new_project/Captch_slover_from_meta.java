package new_project;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;


public class Captch_slover_from_meta {
	

	@Test (priority = 1)
	    public  void testsample() throws IOException {
	        // Set up ChromeDriver
	        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
	        WebDriver driver = new ChromeDriver();

	        // Navigate to the webpage with the captcha
	        driver.get("https://qa.miostack.com/login");
driver.findElement(By.xpath("//span[@id='recaptcha-anchor']")).click();
	        // Locate the captcha image
	        WebElement captchaImage = driver.findElement(By.xpath("(//img[@class='rc-image-tile-44'])[1]"));

	        // Save the captcha image to a file
	        File screenshot = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
	        org.apache.commons.io.FileUtils.copyFile(screenshot, new File("captcha.png"));

	        // Solve the captcha using 2Captcha service
	        String captchaSolution = solveCaptcha("captcha.png", "1cbc316cf7c9ba6638f395db8c3283c8");

	        // Enter the captcha solution
	       // WebElement captchaInput = driver.findElement(By.xpath("//input[@name='captcha']"));
	        // captchaInput.sendKeys(captchaSolution);
	   // another code.
	        // Get the reCAPTCHA iframe
	        WebElement iframe = driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']"));

	        // Switch to the iframe
	        driver.switchTo().frame(iframe);

	        // Get the reCAPTCHA solution input field
	        WebElement solutionInput = driver.findElement(By.xpath("//input[@id='g-recaptcha-response']"));

	        // Enter the CAPTCHA solution using JavaScript execution
	        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + captchaSolution + "';", solutionInput);

	        // Switch back to the default frame
	        driver.switchTo().defaultContent();
	        // another code.

	        // Submit the form
	        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
	        submitButton.click();

	        // Close the browser
	        driver.quit();
	    }

	    public static String solveCaptcha(String imagePath, String apiKey) throws IOException {
	        // Set up the API request
	        HttpClient client = HttpClientBuilder.create().build();
	        HttpPost request = new HttpPost("https://2captcha.com");
	        request.setHeader("Accept", "application/json");

	        // Set up the request body
	        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	        builder.addBinaryBody("file", new File(imagePath));
	        builder.addTextBody("key", apiKey);
	        builder.addTextBody("method", "post");
	        request.setEntity(builder.build());

	        // Send the request and get the response
	        HttpResponse response = client.execute(request);
	        String responseBody = org.apache.http.util.EntityUtils.toString(response.getEntity());

	        // Get the captcha ID from the response
	        String captchaId = responseBody.split("\\|")[1];

	        // Get the captcha solution from the API
	        String captchaSolution = null;
	        while (captchaSolution == null) {
	            request = new HttpPost("https://2captcha.com" + apiKey + "&action=get&id=" + captchaId);
	            response = client.execute(request);
	            responseBody = org.apache.http.util.EntityUtils.toString(response.getEntity());
	            if (responseBody.startsWith("OK|")) {
	                captchaSolution = responseBody.split("\\|")[1];
	            }
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	        }

	        return captchaSolution;
	    }
	}


