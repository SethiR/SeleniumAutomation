package Selenium.SeleniumFramework.AbstractClass;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractClass {
	WebDriver driver;
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartOption;
	
	public AbstractClass(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void untilElementVisible(By FindBy)
	{	 
		WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void untilElementInvisible(WebElement spinner)
	{	WebDriverWait w= new WebDriverWait(driver,Duration.ofSeconds(5));
	    w.until(ExpectedConditions.invisibilityOf(spinner));
		
	}
	
	public void GoToCart()
	{
		cartOption.click();
	}
	
}
