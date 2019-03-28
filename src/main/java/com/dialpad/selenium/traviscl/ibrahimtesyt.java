package com.dialpad.selenium.traviscl;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ibrahimtesyt
{

	
	public void test() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		   driver.get("https://dataentry.efdevhub.info/login.html");
		   
		   // Username
		   
		   driver.findElement(By.name("j_username")).sendKeys("ibrahim.ali@evolutionfinance.com");
		   
		   //PW
		  
		   driver.findElement(By.name("j_password")).sendKeys("Demopass#123");
		   
		   // Login button
		   
		   driver.findElement(By.xpath("/html/body/form/fieldset[1]/div[5]/div[2]/input")).click();
		   
		   //Clicking on Banking
		   Thread.sleep(1000);	
		   driver.findElement(By.xpath("//*[@id=\"header-mainnav\"]/div/ul/li[4]/a")).click();
		   
		   //Clickong on Add product
		   
		   driver.findElement(By.id("add-product")).click();
		   
		   Thread.sleep(2000);	


		   
		  //Product Name
		  
		   driver.findElement(By.id("name")).sendKeys("TestingDevChecking");
		   
		   Thread.sleep(1000);	

		  // Test product checkbox	
		   driver.findElement(By.xpath("//*[@id=\"add-pdt-ta-div\"]/div[2]/input")).click();
		   

		   // Submit
		    
		    driver.findElement(By.xpath("//*[@id=\"add-pdt-form\"]/fieldset/input[1]")).click();
		   
		   Thread.sleep(3000);
		   
		   
		   //	Selecting Type
		    
		    Select dropdown = new Select(driver.findElement(By.xpath("//*[@id=\"add_pdt_type\"]")));
		        dropdown.selectByValue("Checking");
		
	}
	public static void main(String[] args) throws InterruptedException
	{
		// TODO Auto-generated method stub

		
		ibrahimtesyt ob1 = new ibrahimtesyt();
		ob1.test();
	}

}
