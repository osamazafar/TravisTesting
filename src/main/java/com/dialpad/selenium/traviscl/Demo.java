package com.dialpad.selenium.traviscl;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Demo
{
	private static final Logger _logger = LoggerFactory.getLogger(Demo.class);
	
	private WebDriver _webDriver;
	//private DesiredCapabilities _capabilities;
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

		//_options.setHeadless(true);
		//_options.addArguments("screenshot");
		//_options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors", "--remote-debugging-port=4444");
	//	_capabilities = DesiredCapabilities.chrome();
		//_capabilities.setCapability("version", "");
		//_capabilities.setPlatform(Platform.LINUX);
		//_capabilities.setJavascriptEnabled(true);
		//_capabilities.setCapability(ChromeOptions.CAPABILITY, _options);
		System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver");
		_webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), _options);
		_options.setHeadless(true);
		_options.addArguments("screenshot");
	    _options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors");
		//_webDriver = new ChromeDriver(_options);
		_webDriver.get("http://www.dialpadbeta.com/app");
	}

	public void google_login() throws InterruptedException

	{
		WebDriverWait wait = new WebDriverWait(_webDriver, 10);
		WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.id("google-login-button")));
		e.click();
		
		WebElement e1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierId")));
		e1.sendKeys(_supervisorId);
		e1.sendKeys(Keys.RETURN);
		_logger.info(_webDriver.getTitle());
		captureScreenshot("Screenshot # 1");
		_logger.info("*************** BEFORE CLICK ********************");
		_logger.info("page hash: {}", DigestUtils.sha256Hex(_webDriver.getCurrentUrl()));

		_logger.info("**************** CLICKING **************************");
		
		
		_logger.info("hh" + _webDriver.getTitle());
		captureScreenshot("Screenshot # 2");
		_logger.info("*************** AFTER CLICK ********************");
		_logger.info("page hash: {}", DigestUtils.sha256Hex(_webDriver.getCurrentUrl()));
		
		List<WebElement> elems = _webDriver.findElements(By.name("password"));
		
		if(elems == null || (elems != null && elems.size() == 0))
		{
			
			_logger.info("PASSWORD ELEMNENT NOT FOUND");
			//_logger.info(_webDriver.getPageSource());
		}
		else
		{
			_logger.info("PASSWORD ELEMNT FOUND : {}", elems.size());
			
			for(WebElement each : elems)
			{
				_logger.info(each.toString());
			}
		}
		captureScreenshot("Screenshot # 3");
		WebElement e2 = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		e2.sendKeys(_supervisorPassword);
		e2.sendKeys(Keys.RETURN);
		
		_logger.info("Clicked on closing");

		_logger.info("printing: ");
		_webDriver.quit();
		_logger.info("Ending");

	}
	
/*
	public void embedScreenshot(Scenario sceneio) throws InterruptedException
	{
		if(sceneio.isFailed())
		{
			try
			{
				byte [] screenshots = ((TakesScreenshot) _webDriver).getScreenshotAs(OutputType.BYTES);
				sceneio.embed(screenshots, "image/png");
			}
			catch (WebDriverException wde)
			{
				System.err.println(wde.getMessage());
			}
			catch (ClassCastException cce)
			{
				System.err.println(cce.getMessage());
			}
		}
	}*/
		
		public void captureScreenshot(String fileName)
		{
			File scrFile = ((TakesScreenshot) _webDriver).getScreenshotAs(OutputType.FILE);
			try 
			{
				FileUtils.copyFile(scrFile, new File("/Users/osama/Downloads/screenshots" + fileName));
			}
			catch (IOException e)
			{
				e.getStackTrace();
			}
		}
		
	
}
