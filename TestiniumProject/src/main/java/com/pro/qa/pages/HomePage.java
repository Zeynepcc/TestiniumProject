package com.pro.qa.pages;

import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pro.qa.base.TestBase;


//Ana sayfa işlemleri
public class HomePage extends TestBase {
	
	@FindBy(id="searchData")
	WebElement searchdata;
	
	@FindBy(xpath="//*[@id=\"header\"]/div/div/div[2]/div[1]/a")
	WebElement searchdatabutton;
	
	@FindBy(xpath="//a[@href='https://www.n11.com/arama?q=bilgisayar&pg=2\']")
	WebElement secondpageclick;
	
	@FindBy(xpath="/html/body/div[1]/div[3]/div/div/div[2]/section/div[2]/ul/li[last()]/div/div[1]/a/img")
	WebElement product;
	
	@FindBy(id="productDisplayPrice")
	WebElement costnumber;
	
	Actions action = new Actions(driver);
	
	
	WebDriverWait wait = new WebDriverWait(driver,60);
	
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	public  String SearchWord()
	{
		//Alert kısmı esc tuşu ile kapatılır
		action.sendKeys(Keys.ESCAPE).perform();
		//bilgisayar kelimesi gönderilir.
		searchdata.sendKeys("bilgisayar");
		//arama butonuna tıklanır
		wait.until(ExpectedConditions.elementToBeClickable(searchdatabutton)).click();
		//elementlerin görülmesi için scroll indirilir
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 5700)");
		//ikinci sayfaya geçilir
		wait.until(ExpectedConditions.elementToBeClickable(secondpageclick)).click();
		//İkinci sayfanın urlsi test için değişkende tutulur
		String url = driver.getCurrentUrl();
		//linkteki =2 harfi çekilir
		String letter = url.substring(url.length()-2, url.length());
		return letter;

	}
	public void ChooseProduct() throws InterruptedException, ConfigurationException
	{
		//İkinci sayfa atlanmadan rastgele aşağıdan bir ürün seçilir.
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 5300)");
		//Ürüne tıklanır
		wait.until(ExpectedConditions.elementToBeClickable(product)).click();
		/*Ürünün kaybolmaması için (loginin hata sebebi yüzünden gerçekleşmemesi ve
		bu yüzden sepetin boşalması sebebiyle payment.config dosyasına ürünün fiyatı ve ürünün linki gönderilir*/
		PropertiesConfiguration config = new PropertiesConfiguration("C:/Users/HP/eclipse-workspace/"
				+ "TestiniumProject/src/main/java/com/pro/qa/config/payment.properties");
		config.setProperty("cost",costnumber.getAttribute("value"));
		config.setProperty("producturl", driver.getCurrentUrl());
		config.save();

	}

}
