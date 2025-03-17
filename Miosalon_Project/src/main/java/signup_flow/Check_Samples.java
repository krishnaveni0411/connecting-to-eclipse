package signup_flow;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import browsers_launch.Chrome_Browser_Launch;
 
public class Check_Samples extends Chrome_Browser_Launch {
//	String currentPlan = new String();
//	String storeTheSamplePlan = new String();
	New_Signup signup = new New_Signup();
    
    // Get the business_name value using the getter method
    String expectedBusiness = signup.getBusinessName();
    String expectedEmail = signup.getemail();
	
	String expectedPlan = "Premium";
	String expectedStaffCount  = "Unlimited";
	
	@BeforeMethod
	public void launch_browser() {
		setup();
	}
	
	@Test (priority = 1)
	public void go_to_settings_page() throws InterruptedException {
	Thread.sleep(2000);
	driver.findElement(By.xpath("//i[@class='font-icon fal fa-cog']")).click();
	
	}
	@Test (priority = 2)
	public void planing_page() throws InterruptedException {

		go_to_settings_page();
		driver.findElement(By.xpath("//div[contains(text(),'Plans & Billing')]")).click();
		// get store name
		WebElement getStoreName = driver.findElement(By.xpath("//h4[@class='pb-title pb-store-name ng-binding']"));
		String actualStoreName = getStoreName.getText();
		actualStoreName = actualStoreName.trim();
		
		// retrive the business name from signup class
		// Create an instance of New_Signup
        

        // Print or use the value
        System.out.println("Business Name Retrieved: " + expectedBusiness);
        
        if (expectedBusiness.equals(actualStoreName)) {
            System.out.println("The Sample Account is Created in: " + actualStoreName);
        } else {
           System.out.println("The Sample Account should be Created in: " + expectedBusiness + ", The Sample Account is Created in: " + actualStoreName);
        }
	
        // to check the plan.
	WebElement getCurrentplan =	driver.findElement(By.xpath("//h4[@class='pb-title plan-bg']/span"));
	String currentPlan = getCurrentplan.getText();
	currentPlan = currentPlan.trim();
	
	if (expectedPlan.equals(currentPlan)) {
        System.out.println("The Sample Account is Created in: " + currentPlan);
    } else {
       System.out.println("The Sample Account should be Created in: " + expectedPlan + ", The Sample Account is Created in: " + currentPlan);
    }
	
	// to check the staff count
	WebElement getStaffCount =	driver.findElement(By.xpath("//h4[@class='staff-count']/span"));
	String actualStaffCount = getStaffCount.getText();
	
	if (expectedStaffCount .equals(actualStaffCount)) {
        System.out.println("The Sample Account is Created with staff count: " + actualStaffCount);
    } else {
       System.out.println("The Sample Account should be Created with the staff count of: " + expectedStaffCount  + ", The Sample Account is Created with staff count: " + actualStaffCount);
    }
	
	// to check the expiry date.
	WebElement getExpireDate =	driver.findElement(By.xpath("//h4[@class='pb-title text-success ng-scope']/span"));
	 // Expected expiry date (14 days from today)
    LocalDate expectedExpiryDate = LocalDate.now().plusDays(14);
    String myExpectedExpiryDate = expectedExpiryDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")); // e.g., "26 Mar 2025"

    // Get actual expiry date from the webpage
    String actualExpiryDate = getExpireDate.getText().trim(); // Example format: "26 Mar 2025"

    // Validation logic
    if (myExpectedExpiryDate.equals(actualExpiryDate)) {
        System.out.println("The Sample Account is created with the correct expiry date: " + actualExpiryDate);
    } else {
        System.out.println("The Sample Account should expire on: " + myExpectedExpiryDate + ", but it is set to expire on: " + actualExpiryDate);
    }
	
    // to check if the billing summary is showing or not
    WebElement checkBillingSummary = driver.findElement(By.xpath("//div[@class='pb-mhr-blk ng-scope']/h4[@class='modal-title text-center']"));
    String CheckBillSummary = checkBillingSummary.getText();
    
    String billSummary = "Billing Summary";
    if (billSummary.equals(billSummary)) {
    	System.out.println("In the Plan & Billing Page Summary section: " + actualExpiryDate);
    } else {
        System.out.println("The actual Plan & Billing Page Summary section: " + myExpectedExpiryDate + " In the Plan & Billing Page Summary section: " + actualExpiryDate);
    }
    
  
    
	}

	@Test (priority = 3)
	public void login_details() throws InterruptedException {
		go_to_settings_page();
		driver.findElement(By.xpath("//div[contains(text(),'Login Details')]")).click();
		
		//To get The Account Type
		
	     // Locate the label "Account Type :" using its class
        WebElement accountTypeLabel = driver.findElement(By.xpath("//label[contains(text(),'Account Type')]"));

        // Locate the corresponding account type value (should be 'Owner')
        WebElement accountTypeValue = driver.findElement(By.xpath("//label[@data-ng-if=\"role=='Owner'\"]"));

        // Extract text values
        String labelText = accountTypeLabel.getText().trim();
        String accountType = accountTypeValue.getText().trim();

        // Validate if account type is "Owner"
        if (accountType.equals("Owner")) {
            System.out.println(labelText + " " + accountType + " - Validation Passed");
        } else {
            System.out.println("Expected 'Owner' but found: " + accountType);
        }

        //To get the Registered Email 
         
        
     // Locate the Registered Email label (for validation)
        WebElement emailLabel = driver.findElement(By.xpath("//label[contains(text(),'Registered Email')]"));

        // Locate the email value
        WebElement emailValue = driver.findElement(By.xpath("//label[contains(@class,'emaillower ng-binding')]"));

        // Extract text values
        String registeredEmailLabel = emailLabel.getText().trim();
        String actualEmail = emailValue.getText().trim();

        // Validate if the registered email matches the expected email
        if (actualEmail.equals(expectedEmail)) {
            System.out.println(registeredEmailLabel + " " + actualEmail + " - Validation Passed");
        } else {
            System.out.println("Expected: " + expectedEmail + " but found: " + actualEmail);
        }
        
		
	}
	
	@Test (priority = 4)
	public void business_location() {
		
		driver.findElement(By.xpath("//div[contains(text(),'Business Locations')]")).click();
		// to get the store name.
	WebElement getstorename = driver.findElement(By.xpath("//p[@class ='user-card-row-name ng-binding']"));
		String actualStoreName = getstorename.getText();
		actualStoreName = actualStoreName.trim();
		if (actualStoreName.equals(expectedBusiness)) {
            System.out.println("The Actual Store name is: " + actualStoreName);
        } else {
           System.out.println("The Expected Store name is: " + expectedBusiness + ", The Actual Store name is: " + actualStoreName);
        }
		
	}
	
	@Test (priority = 5)
	public void business_details() {
		
	}
}
