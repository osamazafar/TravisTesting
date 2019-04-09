package com.dialpad.selenium.traviscl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTest {
	DesiredCapabilities capabilities;
	ChromeOptions options;
	WebDriver wb;
	WebDriver wb1;
	

	private static final Logger _logger = LoggerFactory.getLogger(MyTest.class);

	@BeforeTest
	public void start() throws InterruptedException, MalformedURLException {
		
		_logger.debug("Starting my test");
		
	    // FailureTestWatcher testWatcher = new FailureTestWatcher();

		options = new ChromeOptions();
		capabilities = DesiredCapabilities.chrome();
		System.setProperty("webdriver.chrome.verboseLogging", "true");

		options.addArguments("--headless");
		options.addArguments("--disable-gpu");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		capabilities.setCapability("version", "");
		capabilities.setPlatform(Platform.LINUX);
		wb = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		wb.get("http://www.dialpadbeta.com/app");
	}

	@BeforeTest
	public void start1() throws InterruptedException, MalformedURLException {
		options = new ChromeOptions();
		options.addArguments("use-fake-ui-for-media-stream");
		options.addArguments("start-maximized");
		options.addArguments("headless");
		options.addArguments("--disable-notifications");
		Thread.sleep(100);
		capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		capabilities.setCapability("version", "");
		capabilities.setPlatform(Platform.LINUX);
		wb1 = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		// wb1 = new ChromeDriver(options);

		wb1.get("http://www.dialpadbeta.com/app");
	}

	@BeforeClass
	public void google_login() throws InterruptedException

	{
		wb.findElement(By.id("google-login-button")).click();
		Thread.sleep(300);
		// wb.findElement(By.id("identifierId")).click();
		wb.findElement(By.id("identifierId")).sendKeys("osamazafar128@gmail.com");
		wb.findElement(By.cssSelector("#identifierNext > content > span")).click();
		Thread.sleep(2000);
		// wb.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).click();
		wb.findElement(By.name("password")).sendKeys("Bigbang@5670006");
		wb.findElement(By.xpath("//*[@id=\"passwordNext\"]/content")).click();
		Thread.sleep(2000);
		wb.findElement(By.id("submit_approve_access")).click();
		Thread.sleep(5000);
		// wb.findElement(By.cssSelector("#announcement-view >
		// div.iblock.dialog-close.ann-close > svg")).click();
		Thread.sleep(100);
	}

	@BeforeClass
	public void googleLogin() throws InterruptedException

	{
		wb1.findElement(By.id("google-login-button")).click();
		Thread.sleep(300);
		wb1.findElement(By.id("identifierId")).click();
		wb1.findElement(By.id("identifierId")).sendKeys("osamazafar128@gmail.com");
		wb1.findElement(By.cssSelector("#identifierNext > content > span")).click();
		Thread.sleep(2000);
		wb1.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).click();
		wb1.findElement(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")).sendKeys("Bigbang@5670006");
		wb1.findElement(By.xpath("//*[@id=\"passwordNext\"]/content")).click();
		Thread.sleep(2000);
		wb1.findElement(By.id("submit_approve_access")).click();
		Thread.sleep(5000);
		// wb.findElement(By.cssSelector("#announcement-view >
		// div.iblock.dialog-close.ann-close > svg")).click();
		Thread.sleep(100);
	}

	@Test(priority = 1)
	public void New_tab() throws InterruptedException {
		System.out.println("This new Tab is experiement");
		((JavascriptExecutor) wb).executeScript("window.open()");
		ArrayList<String> tabs = new ArrayList<String>(wb.getWindowHandles());
		wb.switchTo().window(tabs.get(1));
		wb.get("http://www.dialpadbeta.com");
		Thread.sleep(300);
		// wb.findElement(By.xpath("/html/body/header/div/div/div[2]/a[3]")).click();
		Thread.sleep(5000);
		wb.switchTo().window(tabs.get(0));
	}

	@Test(priority = 2)
	public void loging_out() throws InterruptedException {
		System.out.println("Entering into logout function");
		Thread.sleep(2000);
		wb.findElement(By.xpath("//*[@id=\"main-menu\"]/div[1]")).click();
		Thread.sleep(500);
		wb.findElement(By.xpath("//*[@id=\"logout\"]/div")).click();

		Thread.sleep(10000);

		System.out.println("Out of Application");
		wb.quit();

	}

	@Test(priority = 3)
	public void logingOut() throws InterruptedException {
		System.out.println("Entering into logout function");
		Thread.sleep(2000);
		wb1.findElement(By.xpath("//*[@id=\"main-menu\"]/div[1]")).click();
		Thread.sleep(500);
		wb1.findElement(By.xpath("//*[@id=\"logout\"]/div")).click();

		Thread.sleep(10000);

		System.out.println("Out of Application");
		wb1.quit();

	}

	@AfterTest
	public void end() {
		System.out.println("************Ending********");
	}

	   private void logBrowserConsoleLogs() {
	        System.out.println("================== BROWSER LOGS =======================");
	        LogEntries logEntries = wb.manage().logs().get(LogType.BROWSER);
	        for (LogEntry entry : logEntries) {
	            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
	        }
	        System.out.println("=======================================================");
	    }

	    public class FailureTestWatcher extends TestWatcher {

	        protected void failed(Throwable e, Description description) {
	            // Make the filename safe to write to disk
	            String testName = description.getMethodName();
	            String safeFileName = testName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
	          // takeScreenshot(safeFileName);

	            logBrowserConsoleLogs();
	        }
	    }
}
