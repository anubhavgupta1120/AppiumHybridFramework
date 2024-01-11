package Gestures;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import Resource.Appium_Reusables;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class AndroidGestures extends Appium_Reusables {
	
	AppiumDriver driver;
	
	public AndroidGestures(AppiumDriver driver) {
		this.driver = driver;
	}
	
	public void longPress(WebElement element) {
		JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
		jsExecutor.executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));
	}

	public void scrollIntoView(String TextToScroll, Boolean performClick) {

		if (performClick) {
			driver.findElement(AppiumBy.androidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + TextToScroll + "\"))")).click();
		} else {
			driver.findElement(AppiumBy.androidUIAutomator(
					"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + TextToScroll + "\"))"));
		}
	}

	public void scrollIntoWebView(WebElement element) {
		JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollUntilEnd() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
		} while (canScrollMore);

	}

	public void swipe(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.75));
	}

	public void dragAndDrop(WebElement element, int x_cordinates, int y_cordinates) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "endX", x_cordinates, "endY", y_cordinates));
	}

	public void addProductToCart(List<String> products) throws InterruptedException {
		for (String product : products) {
			scrollIntoView(product, false);
			int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
			for (int i = 0; i < productCount; i++) {
				String productName = driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productName"))
						.get(i).getText();
				if (productName.equalsIgnoreCase(product)) {
					driver.findElements(AppiumBy.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				}
			}
		}
		Thread.sleep(3000);
	}

}
