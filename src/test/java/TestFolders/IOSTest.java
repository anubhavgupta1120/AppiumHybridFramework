package TestFolders;

import org.openqa.selenium.Alert;
import org.testng.annotations.Test;

import PageObject_IOS.AlertPage;

public class IOSTest extends BaseTest.IOSTest {
	@Test
	public void Test() {
		AlertPage alertPage = homePage.goToAlertsPage();
		alertPage.clickTextEntry();
		Alert alert = driver.switchTo().alert();
		alert.sendKeys("Hello");
		alert.accept();
	}
}
