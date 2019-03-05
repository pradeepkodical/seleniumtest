import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DomPage {
	
	private WebDriver Browser;
	
	public DomPage(WebDriver browser) 
	{
		this.Browser = browser;
	}
	
	public DomPage navigateTo(String url) {
		Browser.get(url);
		return this;
	}
	
	public void close() {
		Browser.close();
	}
	
	public DomElement getElement(String cssSelector) 
	{		
		return new DomElement(Browser.findElement(By.cssSelector(cssSelector)));
	}	
	
	public DomElement getElementTillVisible(String cssSelector) throws Exception{
		return getElementTillVisible(cssSelector, 5000);
	}
	
	public DomElement getElementTillVisible(String cssSelector, long millisec) throws Exception
	{		
		try
		{
			DomElement element = getElement(cssSelector); 
			return element;
		}
		catch(Exception ex) 
		{
			if(millisec > 0) {
				Thread.sleep(100);			
				millisec -= 100;
				return getElementTillVisible(cssSelector, millisec);
			}else {
				return new DomNullElement();				
			}
		}
	}
}
