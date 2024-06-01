package Selenium.SeleniumFrameworkDesign;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Selenium.SeleniumFramework.AbstractClass.AbstractClass;

public class ProductCatalogue extends AbstractClass{
	
		WebDriver driver;

		public ProductCatalogue(WebDriver driver) {
			// TODO Auto-generated constructor stub
			super(driver);
			this.driver= driver;
			PageFactory.initElements(driver, this);
		}

		@FindBy(className= "card-body")
		List<WebElement> products;
		@FindBy(css=".ng-animating")
		WebElement spinner;
		
		
	
		
		By productvisible= By.cssSelector(".card-body");
		By selectedProduct = By.cssSelector("b");
		By addToList = By.xpath("//div[@class='card-body']/button[2]");
		By toastMessage= By.cssSelector("#toast-container");
		
		public List<WebElement> getProductList()
		{	untilElementVisible( productvisible);
		    return products;
		
		}
		
		public WebElement getProductName(String productName)
		{	 WebElement prod= getProductList().stream().filter(product->
		      product.findElement(selectedProduct).getText().equals(productName)).findFirst().orElse(null);
			  return prod;
		}
		
		public  void AddToCart(String productName)
		{	WebElement prod= getProductName(productName);
			prod.findElement(addToList).click();
			untilElementVisible(toastMessage);
			untilElementInvisible(spinner);
			
		}

		
		
	
	}
	
}
