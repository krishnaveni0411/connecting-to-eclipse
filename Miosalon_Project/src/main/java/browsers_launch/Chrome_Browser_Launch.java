package browsers_launch;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome_Browser_Launch {
	protected ChromeDriver driver;
	
	 public void setup() {
	        ChromeOptions option = new ChromeOptions();
	        option.addArguments("--disable-notifications", "start-maximized");
	        option.addArguments("--disable-popup-blocking");  // Prevent pop-ups
	        option.addArguments("disable-infobars"); // Prevent automation banner
	        driver = new ChromeDriver(option);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    }
}
