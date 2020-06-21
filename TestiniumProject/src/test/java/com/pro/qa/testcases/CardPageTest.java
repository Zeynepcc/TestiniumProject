package com.pro.qa.testcases;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.pro.qa.base.TestBase;
import com.pro.qa.pages.CardPage;
import com.pro.qa.pages.HomePage;

//Sepet işlemlerinin testi
public class CardPageTest extends TestBase {
	HomePage homepage;
	CardPage cardpage;
	PropertiesConfiguration config ;

	public CardPageTest() 
	{
		super();
		initialization();
		cardpage = new CardPage();

	}
	@Test(priority=1)
	public void EqualCostTest() throws ConfigurationException, InterruptedException //2 ve silme cucumber git
	{
		//Payment.configteki ürün fiyatı çekilir
		config = new PropertiesConfiguration("C:/Users/HP/eclipse-workspace/"
				+ "TestiniumProject/src/main/java/com/pro/qa/config/payment.properties");
		//Ürün sayfasındaki fiyat ile sepetteki fiyat karşılaştırılır
		Assert.assertEquals(config.getProperty("cost").toString().substring(0 ,  config.getProperty("cost").toString().indexOf(".")) ,
				cardpage.EqualCost());
	}
	@Test(priority=2)
	public void IncreaseNumber() throws InterruptedException 
	{
		//Ürününün ikiye eşit olması gerekmektedir
		Assert.assertEquals(cardpage.IncreaseNumber(),"2");
	}
	@Test(priority=3)
	public void DeleteProductTest() 
	{
		//Sepet boş yazısının testi yapılır
		Assert.assertEquals(cardpage.DeleteProduct(), "Sepetiniz Boş");
	}
	

}
