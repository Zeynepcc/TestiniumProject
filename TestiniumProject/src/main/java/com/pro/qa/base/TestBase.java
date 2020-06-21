package com.pro.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//Configurasyonlar
public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public TestBase()
	{
		try 
		{
			/*Default kısımların tutulması için config paketinin 
			içindeki config.properties dosyası oluşturulur*/
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:/Users/HP/eclipse-workspace/"
					+ "TestiniumProject/src/main/java/com/pro/qa/config/config.properties");
			prop.load(ip);	
		} catch(IOException e) {
			e.getMessage();
		} 
	}
	public void initialization()
	{
		//configteki browser türü çekilip browser türüne göre şarta sokulur.Default tarayıcı chrome
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe");
		    driver = new ChromeDriver();    

		}
		else if(browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\selenium\\chromedriver_win32\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();//Max ekran ile çalışılır
		//Zaman aşımını engellemek ve sayfanın yüklenmesi için bir takım time işlemleri yapılır.
		driver.manage().timeouts().pageLoadTimeout(com.pro.qa.util.TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(com.pro.qa.util.TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS); 
		//default url config dosyasından eklenir.
		driver.get(prop.getProperty("url"));
		
	}

}
