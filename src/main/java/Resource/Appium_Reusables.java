package Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;

public abstract class Appium_Reusables {

	public void waitTillAttributeHave(AppiumDriver driver, String attributeName, String value, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.attributeContains((element), attributeName, value));
	}

	public static List<HashMap<String, String>> readDataFromJson(String Path) throws IOException {
		// Step : 1 convert Json file to Json String
		String jsonData = FileUtils.readFileToString(new File(Path), StandardCharsets.UTF_8);
		// Step : 2 converts Json String into HashMap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> jsonList = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return jsonList;
	}
	
	public static String getProperty(String key) throws IOException {
		final String Path = "/Users/anubhavgupta/Documents/eclipse-workspace(Java)/AppTestingFramework/GlobalFiles/GlobalData.properties";
		FileInputStream FIS = new FileInputStream(new File(Path));
		Properties properties = new Properties();
		properties.load(FIS);
		return properties.getProperty(key);
	}

}
