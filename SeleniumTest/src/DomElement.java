import org.openqa.selenium.WebElement;

public class DomElement {
	protected WebElement Element;
	
	public DomElement(WebElement element) {		
		Element = element;
	}
	
	public boolean isVisible() {
		return Element.isDisplayed();
	}
	
	public boolean isEnabled() {
		return Element.isEnabled();
	}
	
	public String getValue() {		
		return Element.getText();
	}
	
	public void setValue(String value) {
		Element.sendKeys(value);
	}
	
	public void click() 
	{
		Element.click();
	}
}
