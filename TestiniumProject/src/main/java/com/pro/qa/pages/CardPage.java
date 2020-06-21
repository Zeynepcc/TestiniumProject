package com.pro.qa.pages;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pro.qa.base.TestBase;


//Sepet işlemleri
public class CardPage extends TestBase {
	
	@FindBy(xpath="//a[@title='Sepete Ekle']")
	WebElement addcard;
	
	@FindBy(xpath= "//*[@id=\"header\"]/div/div/div[2]/div[2]/div[4]/a")
	WebElement gotocard;
	
	@FindBy(xpath= "//*[@id=\"newCheckout\"]/div/div[1]/div[2]/div[1]/section/table[2]/tbody/tr/td[3]/div[2]/div/div/input")
	WebElement costcomparison;
	
	@FindBy(xpath="//span[contains(text() , '+')]")
	WebElement increase;
	
	@FindBy(id="quantity_126856648914")
	WebElement numbertxt;
	
	@FindBy(xpath="//*[@id=\"newCheckout\"]/div/div[1]/div[2]/div[1]/section/table[2]/tbody/tr/td[1]/div[3]/div[2]/span[1]")
	WebElement deleteproduct;
	
	@FindBy(xpath="//*[@id=\"wrapper\"]//h2")
	WebElement iscardempty;
	
	
	PropertiesConfiguration config ;
	
	Actions action = new Actions(driver);
	
	WebDriverWait wait = new WebDriverWait(driver,60); 
	
	public CardPage()
	{
		PageFactory.initElements(driver, this);
	}
	public String EqualCost() throws ConfigurationException, InterruptedException 
	{
		//Kaydedilen ürün url'si çağırılır
		 config = new PropertiesConfiguration("C:/Users/HP/eclipse-workspace/"
					+ "TestiniumProject/src/main/java/com/pro/qa/config/payment.properties");
			   driver.get((String) config.getProperty("producturl"));
			   //Sepete eklenir
			   wait.until(ExpectedConditions.elementToBeClickable(addcard)).click();
			   Thread.sleep(2000);
			   //Sepete gidilir
			   wait.until(ExpectedConditions.elementToBeClickable(gotocard)).click();
			   //Alert kapatılır
			   action.sendKeys(Keys.ESCAPE).perform();
			   //Formatların birbirine benzememesi sebebiyle sepetteki ürünün fiyatının virgülden sonrası atılarak return edilir.
			   return costcomparison.getAttribute("value").substring(0,costcomparison.getAttribute("value").indexOf(","));
	}
	public String IncreaseNumber() throws InterruptedException
	{
		Thread.sleep(3000);
		//Sepetteki ürünün artması için + işaretine basılır
		increase.click();
		//Ve mevcut ürün adedi return edilir
		return numbertxt.getAttribute("value");
	}
	public String DeleteProduct()
	{
		//Ürün silme işlemi gerçekleştirilir
		 wait.until(ExpectedConditions.elementToBeClickable(deleteproduct)).click();
		 //Güncelleme işlemi için sayfa yenilenir
		 driver.navigate().refresh();
		 //Çıkan ekran yazısının sepet boş olması gerekmektedir
		return  iscardempty.getText();
	}
	


}
