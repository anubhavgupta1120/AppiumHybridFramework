package PageObject_Android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Gestures.AndroidGestures;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidGestures{
	 AppiumDriver driver;
	
	public FormPage(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropDown;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement userName;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
	private WebElement radio_Female;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/radioMale")
	private WebElement radio_Male;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
	private WebElement button_letShop;
	
	private void addDetails(String country, String name, String gender) {
		userName.sendKeys(name);
		if(gender.equalsIgnoreCase("male")) {
			radio_Male.click();
		}else {
			radio_Female.click();
		}
		// country selection
		countryDropDown.click();
		scrollIntoView(country, true);
		button_letShop.click();
	}
	
	public ProductPage fillForm(String country, String userName, String gender) {
		addDetails(country, userName, gender);
		return new ProductPage(driver);
	}
	
	

}
