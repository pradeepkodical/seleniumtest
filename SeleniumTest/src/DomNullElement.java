
public class DomNullElement extends DomElement
{
	public DomNullElement() {
		super(null);	
	}
	
	@Override
	public boolean isVisible() {
		return false;
	}
	
	@Override
	public boolean isEnabled() {
		return false;
	}
	
	@Override
	public String getValue() {		
		return "";
	}
	
	@Override
	public void setValue(String value) {
		
	}
	
	@Override
	public void click() 
	{
		
	}
}
