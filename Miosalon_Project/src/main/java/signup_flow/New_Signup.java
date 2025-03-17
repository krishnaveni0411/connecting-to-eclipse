package signup_flow;

import java.time.Duration;
import java.util.Random;
// import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import browsers_launch.Chrome_Browser_Launch;


public class New_Signup extends Chrome_Browser_Launch {
	
	Random random = new Random();
	
	CharSequence singleDigit = String.valueOf(random.nextInt(1));  
   // for phone number
	CharSequence phone_number = String.valueOf(random.nextInt(6)); 
    
  
            // for email generation
            int randomNumber = random.nextInt(9000) + 1000; // Generates a 4-digit number (1000-9999)
            String email = "krishanveniautomation" + randomNumber + "@waffor.com";
            String business_name = "krishanveniautomation" + randomNumber;
          // to get the business name from this class to other.  
            public String getBusinessName() {
                return business_name;
            }
            public String getemail() {
                return email;
            }
            
          /*  for alphabet + number
            String uniqueID = UUID.randomUUID().toString().substring(0, 6); // Get first 6 characters
            String email = "testuser" + uniqueID + "@example.com";
            System.out.println("Generated Email: " + email);
            ✅ This generates emails like testusera1b2c3@example.com.   */
	
	@BeforeMethod
	public void launch_browser() {
		setup();
		 System.out.println("browser is launched");
		 System.out.println(email);
	}
	
	@Test (priority = 1)
	public void signup_to_account() throws InterruptedException {
		driver.get("https://qa.miostack.com/signup");
		WebElement firstname = driver.findElement(By.xpath("//input[@id='contactNameId']"));
		firstname.click();
		firstname.sendKeys("Krishnaveni Automation");
		WebElement emailid = driver.findElement(By.xpath("//input[@id='emailId']"));
		emailid.click();
		emailid.sendKeys(email);
		WebElement businessname = driver.findElement(By.xpath("//input[@id='businessNameId']"));
		businessname.click();
		businessname.sendKeys(business_name);
		Thread.sleep(2000);
		WebElement phonenumber = driver.findElement(By.xpath("//input[@id='contactNoId']"));
		phonenumber.click();
		phonenumber.sendKeys(phone_number);
		Thread.sleep(2000);
		WebElement cpcode = driver.findElement(By.xpath("//input[@id='cpcodeId']"));
		cpcode.click();
		cpcode.sendKeys("5DXC");
		Thread.sleep(10000);
		driver.findElement(By.xpath("//a[@id='registerBtnId2']")).click();
	}
	
	@SuppressWarnings("deprecation")
	@Test (priority = 2, dependsOnMethods = "signup_to_account")
	public void set_account() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//select[@ng-model='categoryName']")).click();
		driver.findElement(By.xpath("//option[@value='Salon & Spa']")).click();
		WebElement outlet_number= driver.findElement(By.xpath("//input[@class='form-control ng-pristine ng-valid']"));
		outlet_number.click();
		outlet_number.sendKeys(singleDigit);
		WebElement staff_count=driver.findElement(By.xpath("(//input[@class='form-control ng-pristine ng-valid'])[last()]"));
		staff_count.click();
		staff_count.sendKeys(singleDigit);
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		Thread.sleep(5000);
		WebElement get_username=driver.findElement(By.xpath("//div[@class='ld__name']"));
		String displayed_username = get_username.getText();
		System.out.println("print the fitched username"+ displayed_username);
		String username= get_username.getAttribute("textContent");
		System.out.println("print the fitched username"+ username);
		
		
		if (email.equals(displayed_username)) {
            System.out.println("Signup with the given mailid succesfuly: " + displayed_username);
        } else {
           System.out.println("Signup mailid is not the given one: " + email + ", Found: " + displayed_username);
        }
		 
		 
		WebElement get_password=driver.findElement(By.xpath("//div[@class='ld__pass']"));
		String displayed_pasword = get_password.getText();
		System.out.println("print the fitched password"+ displayed_pasword);
		String password= get_password.getAttribute("textContent");
		System.out.println("print the fitched password"+ password);
		
		if (phone_number.equals(displayed_pasword)) {
            System.out.println("Signup with the given phone number is succesfuly converted into password: " + displayed_pasword);
        } else {
           System.out.println("Signup phone number is not the converted as password: " + phone_number + ", Found: " + displayed_pasword);
        }
		
		
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		Thread.sleep(5000);
		WebElement get_header=driver.findElement(By.xpath("//h2[@class='main-title']"));
		String header_of_the_page = get_header.getAttribute("textContent");
		System.out.println("print the header of the paage"+ header_of_the_page);
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		Thread.sleep(5000);
	}
	@Test(priority = 3, dependsOnMethods = "set_account")
    public void skipTour() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Skip']")));
        driver.findElement(By.xpath("//button[text()='Skip']")).click();
	}
	
}


