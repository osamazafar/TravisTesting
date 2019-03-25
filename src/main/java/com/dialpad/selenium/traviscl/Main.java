package com.dialpad.selenium.traviscl;

import java.util.Properties;

public class Main
{
	public static void main(String... args) throws Exception
	{
		Properties properties = new Properties();
		properties.load(Main.class.getResourceAsStream("/application.properties"));

		final Demo ob1 = new Demo(properties.getProperty("com.dialpad.seliniun.supervisor.id"),
				properties.getProperty("com.dialpad.seliniun.supervisor.password"));
		ob1.start();
		ob1.google_login();
	}

}
