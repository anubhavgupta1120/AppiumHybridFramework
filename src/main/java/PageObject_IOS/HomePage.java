package PageObject_IOS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Gestures.IOSGestures;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage extends IOSGestures{
	
	AppiumDriver driver;
	
	public HomePage(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@iOSXCUITFindBy(accessibility = "Alert Views")
	private WebElement alertButton;
	
	public AlertPage goToAlertsPage() {
		alertButton.click();
		return new AlertPage(driver);
	}

}
