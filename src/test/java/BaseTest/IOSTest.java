package BaseTest;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import PageObject_IOS.HomePage;
import Resource.Appium_Reusables;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class IOSTest extends Appium_Reusables{

	public AppiumDriver driver;
	private AppiumDriverLocalService service;
	public HomePage homePage;

	@BeforeClass
	public void StartServer() throws NumberFormatException, IOException {
		// Set the ANDROID_HOME Path
		HashMap<String, String> env = new HashMap<>();
		env.put("ANDROID_HOME", "/Users/anubhavgupta/Library/Android/sdk");

		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.usingDriverExecutable(new File("/usr/local/bin/node")).withEnvironment(env).usingPort(Integer.valueOf(getProperty("Port")))
				.withIPAddress(String.valueOf(getProperty("IPaddress")))
				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE);
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

	@BeforeMethod
	public void initiateDriver() throws MalformedURLException {

		XCUITestOptions options = new XCUITestOptions();
		options.setDeviceName("iPhone 15");
		options.setPlatformVersion("17.2");
		options.setApp("/Users/anubhavgupta/Documents/eclipse-workspace(Java)/AppTestingFramework/src/main/java/APPS/UIKitCatalog.app");
//		It required to download webDriver agent in IOS APP/device and with that webDriver agent we can automate our IOS app.
		options.setWdaLaunchTimeout(Duration.ofSeconds(20));
		driver = new IOSDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		homePage = new HomePage(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@AfterClass
	public void stopServer() {
		service.stop();
	}

}
