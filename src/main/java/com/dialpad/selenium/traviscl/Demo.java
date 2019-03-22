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
	ChromeOptions options = new ChromeOptions();
	
	public void start() throws InterruptedException, MalformedURLException
	{
		
		//System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver");
		 
		 
		 
		 capabilities = DesiredCapabilities.chrome();
		 capabilities.setCapability("version", "");
		 capabilities.setPlatform(Platform.LINUX);
		 capabilities.setJavascriptEnabled(true);
		 options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		 Thread.sleep(5000);
	     capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		//wb = new RemoteWebDriver(new URL("http://localhost:4446/wd/hub"), (Capabilities) options);
	   wb = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
	  // wb = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
	   // wb = new ChromeDriver(capabilities);
		wb.get("http://www.dialpadbeta.com/app");
	}
	
	public void google_login() throws InterruptedException

	{
		WebDriverWait wait = new WebDriverWait(wb, 10);
		//DateTimeFormatter dtf;  
		wb.findElement(By.id("google-login-button")).click();
		Thread.sleep(300);
		WebElement e = wb.findElement(By.id("identifierId"));
		e.click();
		e.sendKeys(SupervisorID);
		wb.findElement(By.cssSelector("#identifierNext > content > span")).click(); 
		WebElement e1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		e1.click(); 
		e1.sendKeys(SupervisorPassword);
		wb.findElement(By.id("passwordNext")).click();
		wb.findElement(By.id("submit_approve_access")).click();
		WebElement e2 = wait.until(ExpectedConditions.elementToBeClickable(By.className("icon-sm")));
		e2.click();
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
