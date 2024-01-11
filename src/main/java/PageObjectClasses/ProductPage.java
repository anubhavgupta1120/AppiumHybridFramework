package PageObjectClasses;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Gestures.AndroidGestures;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductPage extends AndroidGestures{
	
	AppiumDriver driver;
	public ProductPage(AppiumDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;
	
//	com.androidsample.generalstore:id/toolbar_title
	@AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
	private WebElement headerText;
	
	public CartPage addProducts(List<String> products) throws InterruptedException{
		addProductToCart(products);
		cartButton.click();
		Thread.sleep(2000);
//		waitTillAttributeHave(driver, "text", "Cart", headerText);
		return new CartPage(driver);
	}
	
	
	

}
