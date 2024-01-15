package TestFolders;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseTest.AndroidTest;
import PageObject_Android.CartPage;
import PageObject_Android.ProductPage;

public class AndroidFlow extends AndroidTest {
	
	
	@Test(dataProvider = "getData", groups = {"Regression"})
	public void fillForm_PositiveFlow(HashMap<String, String> values) throws InterruptedException {
		ProductPage productPage = formPage.fillForm(values.get("Country"), values.get("Name"), values.get("Gender"));
		CartPage cartPage = productPage.addProducts(List.of(values.get("Product_1"), values.get("Product_2")));
		AssertJUnit.assertEquals(cartPage.sumOfAllItem(), cartPage.TotalPrice());
		cartPage.proceedToPlaceOrder();
	}
	
	@Test(groups = {"Smoke", "Regression"})
	public void fillForm_ErrorValidation() {
		formPage.selectCountry("Australia");
		formPage.setGender("Female");
		formPage.login();
		String toastMessage = formPage.getToastMessage();
		AssertJUnit.assertEquals(toastMessage, "Please enter your name");
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> jsonData =readDataFromJson(getProperty("AndroidTestData"));
		Object[][] data = new Object[jsonData.size()][1];
		for(int i = 0; i<jsonData.size(); i++) {
			data[i][0] = jsonData.get(i);
		}
		return data;
	}
}
