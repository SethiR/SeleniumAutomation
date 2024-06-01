package Selenium.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Selenium.SeleniumFramework.AbstractClass.AbstractClass;
import io.github.bonigarcia.wdm.WebDriverManager;


public class ChnageCode {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(5));
		
		LandingPage lp= new LandingPage(driver);
		lp.goTo();
		lp.loginApplication("rohit0007sethi@gmail.com","Password@135");
		
	
		ProductCatalogue pc= new ProductCatalogue(driver);
		List<WebElement> products = pc.getProductList();
		pc.AddToCart(productName);
		pc.GoToCart();
		CartPage cp= new CartPage(driver);
		Boolean cartlist= cp.CheckCart(productName);
		Assert.assertTrue(cartlist);
		
		
		
		
		
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		Thread.sleep(500);
		//w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		//driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		List<WebElement> options= driver.findElements(By.xpath("//button/span[@class='ng-star-inserted']"));
		System.out.println(options.size());
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 500)");
//		for (int i=0;i<options.size(); i++)
//		{	System.out.println(options.get(i).getText());
//		
//			Thread.sleep(100);
//		
//			if(options.get(i).getText().equalsIgnoreCase("Indonesia"))
//			{	
//				System.out.println(options.get(i).getText());
//				options.get(i).click();
//			}
//			
//		}
		options.stream().filter(option->option.getText().equalsIgnoreCase("Indonesia")).findFirst().ifPresent(WebElement::click);
	    		
		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
		String confirmMsg= driver.findElement(By.className("hero-primary")).getText();
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
}
