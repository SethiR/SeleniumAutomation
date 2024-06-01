package Selenium.SeleniumFrameworkDesign;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.SeleniumFramework.AbstractClass.AbstractClass;

public class LandingPage extends AbstractClass{
	
		WebDriver driver;

		public LandingPage(WebDriver driver) {
			// TODO Auto-generated constructor stub
			super(driver);
			this.driver= driver;
			PageFactory.initElements(driver, this);
		}

		@FindBy(id="userEmail")
		WebElement email;
		@FindBy(id="userPassword")
		WebElement password;
		@FindBy(id="login")
		WebElement submit;	
	
		public void loginApplication(String em, String pwd)
		{
			email.sendKeys(em);
			password.sendKeys(pwd);
			submit.click();

		}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
