package findelements;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class find {
	WebDriver driver = new EdgeDriver();
	String WebSite ="https://www.saucedemo.com/";
	
	@BeforeTest
	public void mysetup() {
		driver.get(WebSite);
		
		driver.manage().window().maximize();
		
	}
	
	@Test(invocationCount = 1)
	public void addandremovecart() throws InterruptedException {
	
	Login();
	
	addtocart();
	
	removeItems();
	
	}
	
	
	public void Login() {
		
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		
		driver.findElement(By.id("login-button")).click();
	}
		
	public void addtocart() throws InterruptedException {	
		
		JavascriptExecutor Js = (JavascriptExecutor)driver ;
		
		List<WebElement> addtocart = driver.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
		
		System.out.println(addtocart.size());
		
		for (int i = 0 ; i<addtocart.size();i++) {
			
			addtocart.get(i).click();
			
			List<WebElement> itemsname = driver.findElements(By.className("inventory_item_name"));
			
			
	    String itemname = itemsname.get(i).getText();
			
			Thread.sleep(2000);
			
			Js.executeScript("alert(arguments[0]+'has been add')", itemname);
			Thread.sleep(1000);
			
			driver.switchTo().alert().accept();
			
		}
	}
		public void removeItems() throws InterruptedException {
			
			JavascriptExecutor Js = (JavascriptExecutor)driver ;
			
			List<WebElement> removeItems = driver.findElements(By.className("btn_secondary"));
			
			for (int i = 0 ; i<removeItems.size();i++) {
				
				removeItems.get(i).click();
				
				List<WebElement> itemsname = driver.findElements(By.className("inventory_item_name"));
							
			    String itemname = itemsname.get(i).getText();
					
					Thread.sleep(2000);
					
					Js.executeScript("alert(arguments[0]+'has been removed')", itemname);
					Thread.sleep(1000);
					
					driver.switchTo().alert().accept();
						
		}
		
	}
	
	@AfterTest 
	public void AfterFinishingTest() {
		
		
	}

}
