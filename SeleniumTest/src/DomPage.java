import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
	
	private DomElement getElementInternal(String cssSelector) 
	{		
		return new DomElement(Browser.findElement(By.cssSelector(cssSelector)));
	}	
	
	private List<DomElement> getElementsInternal(String cssSelector) 
	{		
		List<DomElement> retElements = new ArrayList<DomElement>();
		List<WebElement> elements = Browser.findElements(By.cssSelector(cssSelector));
		elements.forEach(s -> {
			retElements.add(new DomElement(s));
		});
		return retElements;
	}	
	
	public DomElement getElement(String cssSelector) throws Exception{
		return getElement(cssSelector, 5000);
	}
	
	public List<DomElement> getElements(String cssSelector) throws Exception{
		return getElements(cssSelector, 5000);
	}
	
	public DomElement getElement(String cssSelector, long millisec) throws Exception
	{		
		try
		{
			DomElement element = getElementInternal(cssSelector); 
			return element;
		}
		catch(Exception ex) 
		{
			if(millisec > 0) {
				Thread.sleep(100);			
				millisec -= 100;
				return getElement(cssSelector, millisec);
			}else {
				return new DomNullElement();				
			}
		}
	}
	
	public List<DomElement> getElements(String cssSelector, long millisec) throws Exception{
		try
		{
			List<DomElement> elements = getElementsInternal(cssSelector);
			if(elements.isEmpty()) {
				if(millisec > 0) {
					Thread.sleep(100);			
					millisec -= 100;
					return getElements(cssSelector, millisec);
				}
			}
			return elements;
		}
		catch(Exception ex) 
		{
			if(millisec > 0) {
				Thread.sleep(100);			
				millisec -= 100;
				return getElements(cssSelector, millisec);
			}else {
				return new ArrayList<DomElement>();				
			}
		}
	}
}
