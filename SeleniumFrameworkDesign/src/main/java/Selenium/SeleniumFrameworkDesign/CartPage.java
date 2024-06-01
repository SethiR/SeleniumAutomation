package Selenium.SeleniumFrameworkDesign;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Selenium.SeleniumFramework.AbstractClass.AbstractClass;

public class CartPage{
	
		WebDriver driver;
		
		@FindBy(xpath="//div[@class='cartSection']/h3")
		List<WebElement> cartaddedlists;
		@ByFind(css=".totalRow button")
		WebElement clickCheckout; 
		
		public CartPage(WebDriver driver) {
			// TODO Auto-generated constructor stub
			this.driver= driver;
			PageFactory.initElements(driver, this);
		}
		
		public Boolean CheckCart(String productName)
		{
			Boolean cartlist = cartaddedlists.stream().anyMatch(cartaddedlist->cartaddedlist.getText().equalsIgnoreCase(productName));
			return cartlist;
		}
		
		public void checkout()
		{	clickCheckout.click();
			
		}
	
		
}
