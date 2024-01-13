package PageObject_IOS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Gestures.IOSGestures;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AlertPage extends IOSGestures {

	AppiumDriver driver;

	public AlertPage(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@iOSXCUITFindBy(accessibility = "Text Entry")
	private WebElement textEntry;

	public void clickTextEntry() {
		textEntry.click();
	}

}
