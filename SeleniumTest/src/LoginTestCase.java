
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTestCase{

	static ExtentTest test;
	static ExtentReports report;
	
	String BaseUrl = "https://heliostest.myrenatus.com";
	String userId = "seleniumuser";
	String password = "Helios!123";
	
	@BeforeClass
	public static void startTest()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\eclipse\\webdrivers\\chromedriver.exe");
		report = new ExtentReports(System.getProperty("user.dir")+"\\ReportResults.html");	
		report.assignProject("Helios");
	}	

	@AfterClass
	public static void endTest()
	{
		report.endTest(test);
		report.flush();
	}	
	
	private void doLogin(DomPage page, ExtentTest test) throws Exception {
		test.log(LogStatus.INFO, "Launching Helios");
		page.navigateTo(BaseUrl);
		
		test.log(LogStatus.PASS, "Launching Helios...done");
		
		test.log(LogStatus.INFO, "Login to Helios");
		page.getElement("helios-login input[type=text]").setValue(userId);
		page.getElement("helios-login input[type=password]").setValue(password);
		page.getElement("helios-login button[type=submit]").click();
					
		page.getElement("nav.navbar ul li", 30000);
		
		test.log(LogStatus.PASS, "Login to Helios...done");	
	}
	
	@Test
	public void testLoginToHelios() throws Exception 
	{
		
		DomPage page = new DomPage(new ChromeDriver());
		
		try {
		
			test = report.startTest("Helios Login");
			
			doLogin(page, test);
			
		}
		catch(Exception ex) {
			test.log(LogStatus.FAIL, this.getClass().getEnclosingMethod().getName());
		}
		finally {
			page.close();
		}		
	}
	
	@Test
	public void testLoginToHeliosAndPlaceOrder() throws Exception 
	{
		
		DomPage page = new DomPage(new ChromeDriver());
		
		try {
		
			test = report.startTest("Helios Order");
			
			doLogin(page, test);			
			
			
			test.log(LogStatus.INFO, "Navigate to Orders");
			page.getElement("a[href='/Orders/Orders']").click();
			
			page.getElement("helios-orders-list table.table", 30000);
			
			test.log(LogStatus.PASS, "Navigate to Orders...done");
			
			test.log(LogStatus.INFO, "Create New Order");
			page.getElement("div.helios-main-buttons-container a[href='#/neworder']").click();
			page.getElement("helios-order-entry-customer a").click();
			page.getElement("helios-order-entry-newcustomer input[placeholder='First Name']").setValue("Test");
			page.getElement("helios-order-entry-newcustomer input[placeholder='Last Name']").setValue("User");
			page.getElement("helios-order-entry-newcustomer input[placeholder='Email']").setValue("TestUser@kiprosh.com");
			page.getElement("helios-order-entry-newcustomer input[placeholder='Phone']").setValue("4565655585");
			
			page.getElements("helios-order-entry-newcustomer .chosen-single").get(2).click();
			Thread.sleep(100);
			page.getElement("ul.chosen-results > li.active-result:nth-child(3)").click();
			Thread.sleep(100);
			
			page.getElement("helios-order-entry-newcustomer input[placeholder='Address']").setValue("1234 Street");
			page.getElement("helios-order-entry-newcustomer input[placeholder='City']").setValue("Naperville");
			
			page.getElements("helios-order-entry-newcustomer .chosen-single").get(3).click();
			Thread.sleep(100);
			page.getElements("helios-order-entry-newcustomer .chosen-drop > .chosen-search input").get(3).setValue("ILLINOIS");
			Thread.sleep(100);
			page.getElement("ul.chosen-results > li.active-result:nth-child(0)").click();
			
			page.getElement("helios-order-entry-newcustomer input[placeholder='ZIP Code']").setValue("60564");			
			
			page.getElement("button[name=submitCustomerBtn]").click();
			
			page.getElement("helios-order-entry-selected-customer i.fa-user");
			test.log(LogStatus.PASS, "Create New Order...done");
		}
		catch(Exception ex) {
			test.log(LogStatus.FAIL, this.getClass().getEnclosingMethod().getName());
		}
		finally {
			page.close();
		}		
	}
}
