package com.dialpad.selenium.traviscl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo
{
	private static final Logger _logger = LoggerFactory.getLogger(Demo.class);
	
	private WebDriver _webDriver;
	private DesiredCapabilities _capabilities;
	final private String _supervisorId;
	final private String _supervisorPassword;
	private ChromeOptions _options = new ChromeOptions();
	
	public Demo(final String supervisorId, final String supervisorPassword)
	{
		_supervisorId = supervisorId;
		_supervisorPassword = supervisorPassword;
	}

	public void start() throws InterruptedException, MalformedURLException
	{

		// System.setProperty("webdriver.chrome.driver",
		// "/usr/local/share/chromedriver");

		// options.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
		_options.setHeadless(true);
		// options.setCapability(CapabilityType.BROWSER_NAME, "CHROME");*/
		_options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
		// options.setCapability(, Platform.LINUX);
		_capabilities = DesiredCapabilities.chrome();
		_capabilities.setCapability("version", "");
		_capabilities.setPlatform(Platform.LINUX);
		_capabilities.setJavascriptEnabled(true);

		Thread.sleep(5000);
		_capabilities.setCapability(ChromeOptions.CAPABILITY, _options);
		// wb = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), options);
		//_webDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4446/wd/hub"), _capabilities);

		// wb = new ChromeDriver(capabilities);
		_webDriver.get("http://www.dialpadbeta.com/app");
	}

	public void google_login() throws InterruptedException

	{
		Thread.sleep(2000);
		_webDriver.findElement(By.id("google-login-button")).click();
		Thread.sleep(300);
		Thread.sleep(2000);
		_webDriver.findElement(By.id("identifierId")).click();
		_webDriver.findElement(By.id("identifierId")).sendKeys(_supervisorId);
		_logger.info(_webDriver.getTitle());
		
		_logger.info("*************** BEFORE CLICK ********************");
		_logger.info("page hash: {}", DigestUtils.sha256Hex(_webDriver.getPageSource()));
		
		Thread.sleep(2000);
		
		_logger.info("**************** CLICKING **************************");
		
		WebElement elementToClick = _webDriver.findElement(By.cssSelector("#identifierNext > content > span"));
		
		_logger.info("Element to click info : {}", elementToClick);
		
		elementToClick.click();
		
		Thread.sleep(2000);
		_logger.info("hh" + _webDriver.getTitle());
		
		_logger.info("*************** AFTER CLICK ********************");
		_logger.info("page hash: {}", DigestUtils.sha256Hex(_webDriver.getPageSource()));
		
		List<WebElement> elems = _webDriver.findElements(By.id("password"));
		
		if(elems == null || (elems != null && elems.size() == 0))
		{
			
			_logger.info("PASSWORD ELEMNENT NOT FOUND");
			_logger.info(_webDriver.getPageSource());
		}
		else
		{
			_logger.info("PASSWORD ELEMNT FOUND : {}", elems.size());
			
			for(WebElement each : elems)
			{
				_logger.info(each.toString());
			}
		}
		
		_webDriver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).click();
		_webDriver.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys(_supervisorPassword);
		Thread.sleep(2000);
		_webDriver.findElement(By.xpath("//*[@id=\"passwordNext\"]/content")).click();
		Thread.sleep(2000);
		_webDriver.findElement(By.xpath("//*[@id=\"submit_approve_access\"]/content")).click();

		Thread.sleep(5000);
		_webDriver.findElement(By.cssSelector("#announcement-view > div.iblock.dialog-close.ann-close > svg")).click();
		Thread.sleep(100);
		_logger.info("Clicked on closing");

		_logger.info("printing: ");
		_webDriver.quit();
		_logger.info("Ending");

	}
}
