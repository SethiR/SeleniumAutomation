package Selenium.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("rohit0007sethi@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@135");
		driver.findElement(By.id("login")).click();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-body")));
		List<WebElement> products= driver.findElements(By.className("card-body"));
		WebElement prod= products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.xpath("//div[@class='card-body']/button[2]")).click();
		
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		
		List<WebElement> cartaddedlists= driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean cartlist = cartaddedlists.stream().anyMatch(cartaddedlist->cartaddedlist.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(cartlist);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
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
		driver.close();
		
	}
}
