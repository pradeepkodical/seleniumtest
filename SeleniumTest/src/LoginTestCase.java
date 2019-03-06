
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
	
	@BeforeClass
	public static void startTest()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\eclipse\\webdrivers\\chromedriver.exe");
		report = new ExtentReports(System.getProperty("user.dir")+"\\ReportResults.html");		
	}	

	@AfterClass
	public static void endTest()
	{
		report.endTest(test);
		report.flush();
	}	
	
	@Test
	public void testLoginAndClientSelect() throws Exception 
	{
		
		DomPage page = new DomPage(new ChromeDriver());
		
		try {
		
			test = report.startTest("Wheels.com");
			
			test.log(LogStatus.INFO, "Launching Fleetview");
			page.navigateTo("https://www.wheels.com/FleetView/Home/Login.aspx");
			
			test.log(LogStatus.PASS, "Launching Fleetview...done");
			
			test.log(LogStatus.INFO, "Login to Fleetview");
			page.getElementTillVisible("#aspnetForm input[placeholder='User ID']").setValue("*****");
			page.getElementTillVisible("#aspnetForm input[type='password']").setValue("*****");
			page.getElementTillVisible("#aspnetForm input[type='submit']").click();
			test.log(LogStatus.PASS, "Login to Fleetview...done");
			
			test.log(LogStatus.INFO, "Selecting Client");
			page.getElementTillVisible(".wh-client-selection input[name='searchText']").setValue("3BIP");
			
			page.getElementTillVisible("a[data-wh-command='onAccountClicked']").click();			
			
			test.log(LogStatus.PASS, "Selecting Client...done");
		}
		catch(Exception ex) {
			test.log(LogStatus.FAIL, this.getClass().getEnclosingMethod().getName());
		}
		finally {
			page.close();
		}		
	}
	
	@Test
	public void testLoginAndClientSelectAndReport() throws Exception 
	{
		
		DomPage page = new DomPage(new ChromeDriver());
		
		try {
		
			test = report.startTest("Wheels.com with Qlik");
			
			test.log(LogStatus.INFO, "Launching Fleetview");
			page.navigateTo("https://www.wheels.com/FleetView/Home/Login.aspx");
			
			test.log(LogStatus.PASS, "Launching Fleetview...done");
			
			test.log(LogStatus.INFO, "Login to Fleetview");
			page.getElementTillVisible("#aspnetForm input[placeholder='User ID']").setValue("*****");
			page.getElementTillVisible("#aspnetForm input[type='password']").setValue("*****");
			page.getElementTillVisible("#aspnetForm input[type='submit']").click();
			test.log(LogStatus.PASS, "Login to Fleetview...done");
			
			test.log(LogStatus.INFO, "Selecting Client");
			page.getElementTillVisible(".wh-client-selection input[name='searchText']").setValue("3BIP");
			
			page.getElementTillVisible("a[data-wh-command='onAccountClicked']").click();			
			
			test.log(LogStatus.PASS, "Selecting Client...done");
			
			page.getElementTillVisible("div.qv-viz").isVisible();
		}
		catch(Exception ex) {
			test.log(LogStatus.FAIL, this.getClass().getEnclosingMethod().getName());
		}
		finally {
			page.close();
		}		
	}
}
