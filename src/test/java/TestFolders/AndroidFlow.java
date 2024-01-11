package TestFolders;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.testng.annotations.Test;

import BaseTest.AndroidTest;
import PageObject_Android.CartPage;
import PageObject_Android.ProductPage;

public class AndroidFlow extends AndroidTest {
  @Test
  public void Test() throws InterruptedException{
	  ProductPage productPage = formPage.fillForm("India", "Anubhav Gupta", "Male");
	  CartPage cartPage = productPage.addProducts(List.of("Air Jordan 9 Retro", "PG 3"));
	  assertEquals(cartPage.sumOfAllItem(), cartPage.TotalPrice());
	  cartPage.proceedToPlaceOrder();
  }
}
