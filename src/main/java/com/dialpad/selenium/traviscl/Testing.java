package com.dialpad.selenium.traviscl;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
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
	
	public void start() throws InterruptedException, MalformedURLException
	{

		_options.setHeadless(true);
	
		_options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200", "--ignore-certificate-errors", "--remote-debugging-port=4444");
		_capabilities = DesiredCapabilities.chrome();
		_capabilities.setCapability("version", "");
		_capabilities.setPlatform(Platform.LINUX);
		_capabilities.setJavascriptEnabled(true);
		_capabilities.setCapability(ChromeOptions.CAPABILITY, _options);
		_wd = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), _capabilities);

		//_wd = new ChromeDriver(_capabilities);
		_wd.get("https://www.google.com/");
		_logger.info(_wd.getTitle());
	}
	
	public void action() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(_wd, 10);
		WebElement e = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
		e.sendKeys("gmail.com");
		e.sendKeys(Keys.RETURN);
		WebElement e1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"rso\"]/div[1]/div/div[1]/div/div/div[1]/a/h3")));
		e1.click();
		_logger.info(_wd.getTitle());

		_logger.info("its the end");
	}

	
	public static void main(String [] args) throws InterruptedException, MalformedURLException
	{
		Testing ob1 = new Testing();
		ob1.start();
		ob1.action();
	}

}
