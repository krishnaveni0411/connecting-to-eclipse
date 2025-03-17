package browsers_launch;

import java.time.Duration;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class Edge_Browser_Launch {
	protected EdgeDriver driver;
	
	 public void setup() {
	        EdgeOptions option = new EdgeOptions();
	        option.addArguments("--disable-notifications", "start-maximized");
	        driver = new EdgeDriver(option);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	    }
}
