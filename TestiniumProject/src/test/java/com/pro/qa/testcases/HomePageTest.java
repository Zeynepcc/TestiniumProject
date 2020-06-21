package com.pro.qa.testcases;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.configuration.Config;
import com.pro.qa.base.TestBase;
import com.pro.qa.pages.HomePage;
import com.pro.qa.pages.LoginPage;


//Anasayfa işlemlerinin testi
public class HomePageTest extends TestBase {
	
	HomePage homepage;

	public HomePageTest() throws InterruptedException
	{
		super();
		initialization();
		homepage = new HomePage();

	}
	@Test(priority=1)
	public void SearchWordTest() 
	{
		//Gelen değer =2 ise ikinci sayfaya geçilmiştir
		Assert.assertEquals("=2", homepage.SearchWord());
	}
	@Test(priority=2)
	public void ChooseProductTest() throws InterruptedException, ConfigurationException
	{
		homepage.ChooseProduct();
		//Ürünün seçilip seçilmediği fiyatının null gelip gelmemesiyle belli olur
		PropertiesConfiguration config = new PropertiesConfiguration("C:/Users/HP/eclipse-workspace/"
				+ "TestiniumProject/src/main/java/com/pro/qa/config/payment.properties");
		    Assert.assertNotNull(config.getProperty("cost"));
	}
	
}
