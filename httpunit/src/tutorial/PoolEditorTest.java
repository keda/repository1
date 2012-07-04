package tutorial;

import org.junit.Before;
import org.junit.Test;
import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class PoolEditorTest {
	
	@Before
    public void prepare() {
        setBaseUrl("http://localhost:9090/mysuper");
    }
	
	@Test
	public void test() {
//		setScriptingEnabled(false);
		
		beginAt("/home.jsp"); //Open the browser on http://localhost:9090/mysuper
        
		System.out.println("INFO: [home] serverResponse -->> "+getServerResponse() + "\r\n" +"<<------------------------------");
		
		assertButtonPresentWithText("Add+");
		
		clickButtonWithText("Add+");
		
		setExpectedJavaScriptAlert("127.0.0.1");
		
		assertElementPresentByXPath("//div[@id='wrapper']");
		
		clickElementByXPath("//div[@id='wrapper']/div[@class='sub_nav']/ol[@class='sub_nav_ol']/li[2]");
		
		System.out.println("INFO: [View2] serverResponse -->> "+getServerResponse() + "\r\n" +"<<------------------------------");
	}

}
