package AddFirstItemWithRegisterProcess;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.Assertion;

public class Parameters {

	String url = "https://smartbuy-me.com/smartbuystore/en/login";
	
	WebDriver driver = new ChromeDriver();
	
	Assertion myAssert = new Assertion();
	
	Random random = new Random();
	
	String[] phoneType = {"iphone", "samsung", "huawei"};
}
