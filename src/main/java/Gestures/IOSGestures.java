package Gestures;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import Resource.Appium_Reusables;
import io.appium.java_client.AppiumDriver;

public class IOSGestures extends Appium_Reusables{
	AppiumDriver driver;
	public IOSGestures(AppiumDriver driver) {
		this.driver = driver;
	}
	
	public void longPress(WebElement element, int duration) {

		JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
		HashMap<String, Object> params = new HashMap<>();
		params.put("duration", duration);
		params.put("element", ((RemoteWebElement) element).getId());
		jsExecutor.executeScript("mobile: touchAndHold", params);

	}

	public void scrollInToView(WebElement element, String direction, boolean click) {

		JavascriptExecutor js = ((JavascriptExecutor) driver);
		Map<String, Object> params = new HashMap<>();
		params.put("direction", direction);
		params.put("elementId", ((RemoteWebElement) element).getId());
		if (click) {
			js.executeScript("mobile: scroll", params);
			element.click();
		} else {
			js.executeScript("mobile: scroll", params);
		}

	}

	public void handlePickers(WebElement element, String value) {
		element.sendKeys(value);
	}

	public void handleSlider(WebElement element, String value) {
		element.sendKeys(value);
	}

	public void Swipe(WebElement element, String directionToSwipe) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Map<String, Object> params = new HashMap<>();
		params.put("direction", directionToSwipe);
		params.put("element", ((RemoteWebElement) element).getId()); // If you want to perform 
																	//swipe on the centre then no need to give element
		js.executeScript("mobile: swipe", params);
	}
}
