package com.dialpad.selenium.traviscl;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class Travis
{
	private static final Logger _logger = LoggerFactory.getLogger(Travis.class);
	private WebDriver _wd;
	//private DesiredCapabilities _capabilities;\
	private ChromeOptions _options = new ChromeOptions();
	
	
	public void Openbrowser() throws MalformedURLException, InterruptedException
	{
		 _options.addArguments("--headless");
		_options.addArguments("--disable-gpu");
		_options.addArguments("--window-size=1280,800");
		_options.addArguments("--allow-insecure-localhost");
		//_wd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), _options);
		_wd = new ChromeDriver(_options);
		_wd.get("https://www.expedia.com/");
		Thread.sleep(3000);
	}
	
	public void bookflight()
	{
		
		WebDriverWait wait = new WebDriverWait(_wd, 20);
		WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"package-origin-hp-package\"]")));
		e.sendKeys("Los Angeles");
		e.sendKeys(Keys.RETURN);
		_logger.info("Origin is added");
		
		WebElement e1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"package-destination-hp-package\"]")));
		e1.sendKeys("San Francisco");
		e1.sendKeys(Keys.RETURN);
		
		WebElement e2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"package-departing-hp-package\"]")));
		e2.sendKeys("05/20/2019");
		e2.sendKeys(Keys.RETURN);
		_logger.info("Search completed");
		
		
		
		
		
	}
	
	public static void main(String [] args) throws InterruptedException, MalformedURLException
	{
		Travis travis = new Travis();
		travis.Openbrowser();
		travis.bookflight();
	}
}
