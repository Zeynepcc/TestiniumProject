package com.pro.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pro.qa.base.TestBase;
import com.pro.qa.pages.HomePage;
import com.pro.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {
	
	//Login işlemlemlerinin testi
	
	LoginPage loginpage;
	HomePage  homepage;
	
	public LoginPageTest()
	{
		super();
		initialization();
		loginpage = new LoginPage();
	}
	@Test(priority=1)
	public void OpenHomePageTest()
	{
		//Title aşağıdaki yazıya eşitse , anasayfadayız demektir.
		Assert.assertEquals(loginpage.OpenHomePage(), "n11.com - Alışverişin Uğurlu Adresi");
	}
	@Test(priority=2)
	public  void LoginAccountTest() throws InterruptedException
	{
		//default değerler config.prop dosyasından çekilir.Ve setlenir ve test edilir.
		homepage = loginpage.LoginPageAccount(prop.getProperty("mail"), 
				prop.getProperty("password"));		
	}
	@Test(priority=3)
	public void GetAccountNameTest()
	{
		//Login test kısmı
		Assert.assertEquals("Zeynep Turhan", loginpage.GetAccountName());
	}
}
