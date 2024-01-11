package PageObject_Android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Gestures.AndroidGestures;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidGestures {
	AppiumDriver driver;

	public CartPage(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPriceList;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalCost;

	@AndroidFindBy(xpath = "//android.widget.CheckBox[contains(@text, 'Send me e-mails on discounts')]")
	private WebElement checkbox_FurtherUpdates;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement termsAndConditions;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement acceptElement;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement btnProceed;

	public double sumOfAllItem() {
		int productCount = productPriceList.size();
		double sum = 0;
		for (int i = 0; i < productCount; i++) {
			double productPrice = Double.parseDouble(productPriceList.get(i).getText().substring(1));
			sum = sum + productPrice;
		}
		return sum;
	}

	public double TotalPrice() {
		String finalCost = totalCost.getAttribute("text");
		double totalValue = Double.parseDouble(finalCost.substring(2));
		return totalValue;
	}

	public void proceedToPlaceOrder() {
		checkbox_FurtherUpdates.click();
		longPress(termsAndConditions);
		acceptElement.click();
		btnProceed.click();

	}

}
