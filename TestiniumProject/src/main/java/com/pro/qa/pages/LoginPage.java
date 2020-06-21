package com.pro.qa.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.pro.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//Login İşlemleri
	
	/*Not : Login yapma işlemi sırasında hata tespit edilmiştir.Test sırasında butona basılmasına rağmen ve testin geçilmesine rağmen kullanıcı adı 
	ile ana sayfaya yönledirme işlemi yapılmamıştır.Bu sebepten ötürü sepet işlemleri profile girilememesi sebebiyle normal yollarla gerçekleştirilememiştir
	Ek yöntem olarak testin mecburiyeti sebebiyle ürün linki payment.properties dosyasında tutulmuş ve oradan çekilmiştir.*/
		
	@FindBy(xpath="//*[@id=\"header\"]//text()")
	WebElement getaccountname;
	
	@FindBy(id="email")
	WebElement mailadress;
	
	@FindBy(id="password")
	WebElement pass;
	
	@FindBy(id="loginButton")
	WebElement loginbutton;
	
	WebDriverWait wait = new WebDriverWait(driver,60);	
		
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	public String OpenHomePage()
	{
		//Girişteki alert dialog kapatılır
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"userKvkkModal\"]//span"))).click();
		//Title ile anasayfa kontrolü yapılır 
		return driver.getTitle();
	}
	public HomePage LoginPageAccount(String email ,String password) throws InterruptedException
	{
		//Normalde thread sleep kullanılmaması gereklidir ama burada bekleme yapılmazsa element bulunamıyor. 
		Thread.sleep(3000);
		//Giriş yap href'ine tıklanır.
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.n11.com/giris-yap']"))).click();
		//mail ve şifre gönderilir.
		mailadress.sendKeys(email);
		pass.sendKeys(password);
		//Element görülemediği için scrollbar aşağı kaydırılır.
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 500)");
		//Click işlemi gerçekleşir
		wait.until(ExpectedConditions.elementToBeClickable(loginbutton)).click();
		return new HomePage();	
	}
	public String GetAccountName()
	{
		/*Bu kısımda giriş yapıldıysa Profilde isim soyisim bilgileri gözükmelidir fakat
		bahsettiğim gibi giriş yapılsada isim soyisim görünmüyor*/
		return getaccountname.getText();
	}

}
