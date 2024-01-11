package Resource;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public abstract class Appium_Reusables {
	
	public void waitTillAttributeHave(AppiumDriver driver, String attributeName, String value, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.attributeContains(element, attributeName, value));
	}

}
