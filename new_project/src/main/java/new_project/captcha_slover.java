package new_project;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;




import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class captcha_slover {
	

	
	    public static void main(String[] args) throws IOException, InterruptedException {
	        // Set up ChromeDriver
	        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
	    	ChromeOptions option = new ChromeOptions();
			option.addArguments("_disable-notifications", "start-maximized");
			WebDriver driver=new ChromeDriver(option);
			System.out.println("Let's start with login");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	      //  WebDriver driver = new ChromeDriver();

	        // Navigate to the login page
	        driver.get("https://qa.miostack.com");
	        driver.findElement(By.xpath("//button[text()= 'Login']")).click();
			Thread.sleep(10000);
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("krishnaecrystal123@gmail.com");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
			System.out.println("now clicking the captcha");

	        // Switch to the reCAPTCHA iframe
	        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']")));

	        // Click on the reCAPTCHA checkbox
	        driver.findElement(By.xpath("//div[@class='recaptcha-checkbox-border']")).click();

	        // Switch back to the default frame
	        driver.switchTo().defaultContent();

	        // Solve the reCAPTCHA using 2Captcha service
	        String captchaSolution = solveRecaptcha(driver);

	        // Enter the reCAPTCHA solution
	        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("document.getElementById('g-recaptcha-response').value='" + captchaSolution + "';");

	        // Submit the form
	        driver.findElement(By.xpath("//button[@type='submit']")).click();
	        
	        System.out.println("we cleared the captcha");
	        System.out.println("click the login button");
	        // click the login button
	        driver.findElement(By.xpath("//button[text()='Login']")).click();

	        // Close the browser
	       // driver.quit();
	    }

	    public static String solveRecaptcha(WebDriver driver) throws IOException {
	        // Get the reCAPTCHA site key
	        String siteKey = driver.findElement(By.xpath("//div[@class='g-recaptcha']")).getAttribute("data-sitekey");

	        // Solve the reCAPTCHA using 2Captcha service
	        String apiKey = "1cbc316cf7c9ba6638f395db8c3283c8"; // Replace with your 2Captcha API key
	        String captchaSolution = solveRecaptcha(siteKey, apiKey);

	        return captchaSolution;
	    }

	    public static String solveRecaptcha(String siteKey, String apiKey) throws IOException {
	        // Set up the API request
	    	CloseableHttpClient client = HttpClients.createDefault();

	      //  HttpClient client = HttpClientBuilder.create().build();
	        HttpPost request = new HttpPost("https://2captcha.com/res.php");
	        request.setHeader("Accept", "application/json");

	        // Set up the request body
	        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
	        builder.addTextBody("key", apiKey);
	        builder.addTextBody("method", "userrecaptcha");
	        builder.addTextBody("googlekey", siteKey);
	        builder.addTextBody("pageurl", "https://2captcha.com/res.php");
	        request.setEntity(builder.build());

	        // Send the request and get the response

CloseableHttpResponse response = client.execute(request);

	        String responseBody = org.apache.http.util.EntityUtils.toString(((HttpEntityEnclosingRequestBase) response).getEntity());

	        // Get the CAPTCHA ID from the response
	        String captchaId = responseBody.split("\\|")[1];

	        // Get the CAPTCHA solution from the API
	        String captchaSolution = null;
	        while (captchaSolution == null) {
	            request = new HttpPost("https://2captcha.com/res.php" + apiKey + "&action=get&id=" + captchaId);
	            response = client.execute(request);
	            responseBody = org.apache.http.util.EntityUtils.toString(((HttpEntityEnclosingRequestBase) response).getEntity());
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


