package BaseTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import PageObject_Android.FormPage;
import Resource.Appium_Reusables;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AndroidTest extends Appium_Reusables{
	
	public AppiumDriver driver;
	private AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass(alwaysRun = true)
	public void StartServer() throws NumberFormatException, IOException {
		//To run the test from maven use : mvn test -P{$Profile} -DIPaddress={$addressValue}
		// Set the ANDROID_HOME Path
		HashMap<String, String> env = new HashMap<>();
		env.put("ANDROID_HOME", "/Users/anubhavgupta/Library/Android/sdk");
		String IPaddress = System.getProperty("IPaddress") != null ? System.getProperty("IPaddress") : getProperty("IPaddress");

		AppiumServiceBuilder builder = new AppiumServiceBuilder();
		builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.usingDriverExecutable(new File("/usr/local/bin/node")).withEnvironment(env).usingPort(Integer.valueOf(getProperty("Port")))
				.withIPAddress(String.valueOf(IPaddress))
				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE);
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

	@BeforeMethod(alwaysRun = true)
	public void initiateDriver() throws IOException {

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(String.valueOf(getProperty("AndroidEmulatorName")));
		options.setChromedriverExecutable("/Users/anubhavgupta/Documents/Drivers/chromedriver_103");
		options.setApp(
				"/Users/anubhavgupta/Documents/eclipse-workspace(Java)/AppTestingFramework/src/main/java/APPS/General-Store.apk");
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	@AfterClass(alwaysRun = true)
	public void stopServer() {
		service.stop();
	}

}
