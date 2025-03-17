package browsers_launch;

import java.time.Duration;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FireFox_Browser_Launch {
	protected FirefoxDriver driver;
	
	 public void setup() {
		  FirefoxOptions option = new FirefoxOptions();
	        option.addArguments("--disable-notifications", "start-maximized");
	        driver = new FirefoxDriver(option);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    }
}
