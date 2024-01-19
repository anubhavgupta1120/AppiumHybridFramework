package TestFolders;

import org.openqa.selenium.Alert;
import org.testng.annotations.Test;

import BaseTest.IOSTest;
import PageObject_IOS.AlertPage;

public class IOSFlow extends IOSTest {
	@Test(groups = {"Smoke", "Regression"})
	public void AlertTest() {
		AlertPage alertPage = homePage.goToAlertsPage();
		alertPage.clickTextEntry();
		Alert alert = driver.switchTo().alert();
		alert.sendKeys("Hello");
		alert.accept();
	}
}
