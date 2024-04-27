package AddFirstItemWithRegisterProcess;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddFirstProduct extends Parameters {
	
	String email = "";
	String password = "Omar_12345";

	@BeforeTest
	public void setup() {
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@Test(priority = 1)
	public void signupProcess() throws InterruptedException {
		
		WebElement firstNameInput = driver.findElement(By.id("register.firstName"));
		WebElement lastNameInput = driver.findElement(By.id("register.lastName"));
		WebElement gender = driver.findElement(By.id("registerGender"));
		WebElement birthDateInput = driver.findElement(By.id("dateOfBirth"));
		WebElement code = driver.findElement(By.id("countryMobileNumber"));
		WebElement title = driver.findElement(By.id("register.title"));
		WebElement phoneNumber = driver.findElement(By.id("mobileNumber"));
		WebElement emailInput = driver.findElement(By.id("register.email"));
		WebElement nationality = driver.findElement(By.id("registerNationality"));
		WebElement passwordInput = driver.findElement(By.id("password"));
		WebElement confirmPassword = driver.findElement(By.id("register.checkPwd"));
		WebElement checkbox = driver.findElement(By.id("termsandconditions"));
		WebElement registerButton = driver.findElement(By.cssSelector("div[class='form-actions clearfix'] button[type='submit']"));
		
		//Select Title randomly
		Select selectTitle = new Select(title);
		int randomNumber = random.nextInt(4) + 1;
		selectTitle.selectByIndex(randomNumber);
		
		firstNameInput.sendKeys("Omar");
		lastNameInput.sendKeys("Alsayyed");
		birthDateInput.sendKeys("06/10/1996");
		phoneNumber.sendKeys("785254004");
		passwordInput.sendKeys(password);
		confirmPassword.sendKeys(password);
		
		//Select Gender
		Select selectGender = new Select(gender);
		selectGender.selectByIndex(1);
		//Select Code Country
		Select selectCode = new Select(code);
		selectCode.selectByVisibleText("Jordan +962");
		
		//generate Randomly Email
		int randomEmailNumber = random.nextInt(5000);
		email = "omar"+randomEmailNumber+"@gmail.com";
		emailInput.sendKeys(email);
		
		//Select Nationality
		Select selectNationality = new Select(nationality);
		selectNationality.selectByVisibleText("Jordanian");
		
		//Click On The Check Box
		checkbox.click();
		
		registerButton.click();
		
		//Assertion
		Thread.sleep(5000);
		boolean actualMsg = driver.findElement(By.cssSelector(".alert.alert-info.alert-dismissable")).getText().contains("Thank you") || 
                driver.findElement(By.cssSelector(".alert.alert-info.alert-dismissable")).getText().contains("شكرا");
		System.out.println(actualMsg);
		myAssert.assertEquals(actualMsg, true);
		
	}
	
	@Test(priority = 2)
	public void signinProcess() {
		
		driver.get(url);
		
		WebElement emailLoginInput = driver.findElement(By.id("j_username"));
		WebElement passwordLoginInput = driver.findElement(By.id("j_password"));
		WebElement loginButton = driver.findElement(By.cssSelector("div[class='btn-block gr2_back'] button[type='submit']"));
		
		emailLoginInput.sendKeys(email);
		passwordLoginInput.sendKeys(password);
		loginButton.click();
		
	}
	
	@Test(priority = 3)
	public void addFirstProduct() throws InterruptedException {
		
		//Fill Search Input Randomly From Phone Type List
		WebElement searchInput = driver.findElement(By.id("js-site-search-input"));
		int randomSearch = random.nextInt(3);
		searchInput.sendKeys(phoneType[randomSearch]);
		searchInput.sendKeys(Keys.ENTER);
		
		//Get List Of Product and Open First Product
		WebElement productContainer = driver.findElement(By.cssSelector(".product__listing.product__grid"));
		List<WebElement> products = productContainer.findElements(By.xpath("//div[@class='product-item item_grid']"));
	
		products.get(0).click();
		
		//Add Product To Cart
		WebElement addToCartButton = driver.findElement(By.id("addToCartButton"));
		addToCartButton.click();
		
		
		//Assertion
		Thread.sleep(2000);
		boolean actualMsg = driver.findElement(By.className("headline-text")).getText().contains("added");
		
		myAssert.assertEquals(actualMsg, true);
		
		//Navigate To Checkout Page
		WebElement checkoutButton = driver.findElement(By.cssSelector(".btn.btn-primary.btn-block.add-to-cart-button"));
		checkoutButton.click();		
		
		//Assertion
		String expectedResult = driver.findElement(By.cssSelector(".item__total.js-item-total.hidden-xs.hidden-sm")).getText();
		String actualResult = driver.findElement(By.cssSelector(".col-xs-6.cart-totals-right.text-right.grand-total")).getText();
		
		myAssert.assertEquals(actualResult, expectedResult);
		
		Thread.sleep(4000);
		driver.quit();
	
	}
	
}
