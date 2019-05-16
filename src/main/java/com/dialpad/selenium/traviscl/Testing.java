package com.dialpad.selenium.traviscl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Testing
{
	private static final Logger _logger = LoggerFactory.getLogger(Testing.class);
	private WebDriver _wd;
	private DesiredCapabilities _capabilities;
	private ChromeOptions _options = new ChromeOptions();
	String SupervisorID = "osamadialpad@gmail.com";
	String SupervisorPass = "100California";
	
	public void start() throws InterruptedException, MalformedURLException
	{

		
		//_options.addArguments("--headless");
		_options.addArguments("--disable-gpu");
		_options.addArguments("--window-size=1280,800");
		_options.addArguments("--allow-insecure-localhost");
		//_options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
		_capabilities = DesiredCapabilities.chrome();
		_capabilities.setCapability("version", "");
		_capabilities.setPlatform(Platform.LINUX);
		_capabilities.setJavascriptEnabled(true);
		_capabilities.setCapability(ChromeOptions.CAPABILITY, _options);
		_wd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), _capabilities);

		//_wd = new ChromeDriver(_capabilities);
		_wd.get("https://www.google.com");
		_logger.info(_wd.getTitle());
		 captureScreenshot("Screenshot #  0");
	}
	
	
/*public void google_login() throws InterruptedException
	
	{
		WebDriverWait wait = new WebDriverWait(_wd, 10);
		//DateTimeFormatter dtf;  
		_wd.findElement(By.id("google-login-button")).click();
		Thread.sleep(300);
		WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierId")));
		e.click(); 
		e.sendKeys(SupervisorID);
		e.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		WebElement e1 = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		e1.click(); 
		e1.sendKeys(SupervisorPass);
		e1.sendKeys(Keys.ENTER);
		
		WebElement e2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit_approve_access")));
		e2.click(); 
		WebElement e3 = wait.until(ExpectedConditions.elementToBeClickable(By.className("icon-sm")));
		e3.click();
		System.out.println("Clicked on closing");
	}

public void loging_out() throws InterruptedException
{
	System.out.println("Entering into logout function");
	WebDriverWait wait = new WebDriverWait(_wd, 10);
	WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.id("main-menu")));
	e.click();
	
	WebElement e1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("logout")));
	e1.click();
	_wd.quit();

}*/

	public void action() throws InterruptedException
	{
		
		
		WebDriverWait wait = new WebDriverWait(_wd, 20);
		WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
		e.sendKeys("gmail");
		e.sendKeys(Keys.ENTER);
		captureScreenshot("Screenshot #  11");
		Thread.sleep(4000);
		
		WebElement e1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"rso\"]/div[1]/div/div/div/div/div[1]/a/h3")));
		e1.click();
		
		
		WebElement e2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[5]/ul[1]/li[2]/a")));
		e2.click();
		
		
		
		
		((JavascriptExecutor) _wd).executeScript("");
	    ArrayList<String> tabs = new ArrayList<String>(_wd.getWindowHandles());
	    _wd.switchTo().window(tabs.get(1));
	  //_wd.get("https://accounts.google.com/signin/v2/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		
	   
		
	    Thread.sleep(2000);
		WebElement e3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierId")));
		e3.sendKeys("callback229@gmail.com");
		e3.sendKeys(Keys.ENTER);
		 _logger.info(_wd.getTitle());
		captureScreenshot("Screenshot # 2");
		_logger.info(_wd.getTitle());
		WebElement e4 = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		e4.sendKeys("Dialpad100");
		e4.sendKeys(Keys.ENTER);

		_logger.info(_wd.getTitle());
		_logger.info("its the end");
	}
	
	public void captureScreenshot(String fileName)
	{
		File scrFile = ((TakesScreenshot) _wd).getScreenshotAs(OutputType.FILE);
		try 
		{
			FileUtils.copyFile(scrFile, new File("/Users/osama/Downloads/screenshots/" + fileName));
		}
		catch (IOException e)
		{
			e.getStackTrace();
		}
	}

	
	public static void main(String [] args) throws InterruptedException, MalformedURLException
	{
		Testing ob1 = new Testing();
		ob1.start();
		ob1.action();
		//ob1.google_login();
		//ob1.loging_out();
		
	}

}
