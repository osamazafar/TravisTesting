package com.dialpad.selenium.traviscl;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class Demo {
	
	WebDriver wb;
	DesiredCapabilities capabilities;
	String SupervisorID = "callback229@gmail.com";
	String SupervisorPassword = "100California";
	//ChromeOptions options = new ChromeOptions();
	
	public void start() throws InterruptedException, MalformedURLException
	{
		
		System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver");
		 
		 
		 
		 capabilities = DesiredCapabilities.chrome();
		 capabilities.setCapability("version", "");
		 capabilities.setPlatform(Platform.LINUX);
		 capabilities.setJavascriptEnabled(true);
	     //capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		//wb = new RemoteWebDriver(new URL("http://localhost:4446/wd/hub"), (Capabilities) options);
	   // wb = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
	    wb = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
	   // wb = new ChromeDriver(capabilities);
		wb.get("http://www.dialpadbeta.com/app");
	}
	
	public void google_login() throws InterruptedException

	{
		Thread.sleep(2000);
		wb.findElement(By.id("google-login-button")).click();
		Thread.sleep(300);
		wb.findElement(By.id("identifierId")).click();
		wb.findElement(By.id("identifierId")).sendKeys(SupervisorID);
		wb.findElement(By.cssSelector("#identifierNext > content > span")).click();
		//wb.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS); 
		Thread.sleep(2000);
		wb.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).click(); 
		Thread.sleep(2000);
		wb.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys(SupervisorPassword);
		Thread.sleep(2000);
		wb.findElement(By.xpath("//*[@id=\"passwordNext\"]/content")).click();
		Thread.sleep(2000);
		wb.findElement(By.xpath("//*[@id=\"submit_approve_access\"]/content")).click();
		Thread.sleep(10000);
		System.out.println("Clicked on closing");

		System.out.println("printing: ");
		wb.quit();
		System.out.println("Ending");
		
	}
	
	
	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		// TODO Auto-generated method stub
		Demo ob1 = new Demo();
		ob1.start();
		ob1.google_login();

	}

}
