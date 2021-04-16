package com.Scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Shop
{
	
	String driverPath = "D:\\chromedriver_win32\\chromedriver.exe";
    public WebDriver driver ; 
    public WebDriverWait wait;
    
    @BeforeMethod()
  	public void launch_Application() throws InterruptedException
  	{

    	System.setProperty("webdriver.chrome.driver", driverPath);
    	driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.get("https://qa.symbionit.com.au/shop/");
        Thread.sleep(5000);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
        wait = new WebDriverWait(driver, 10);
  	}
    
    @Test(enabled=false)//(priority=0)
    public void login() throws InterruptedException
    {
    	driver.findElement(By.id("UserName")).sendKeys("shop.qa");
    	Thread.sleep(2000);
    	driver.findElement(By.id("Password")).sendKeys("password");
    	driver.findElement(By.id("ctl00_cplMain_Login2_LoginButton")).click();
    }
    
    @Test(enabled=false)
    public void product_Search() throws InterruptedException
    {                                    
    	WebElement selectMyElement = driver.findElement(By.xpath("//*[@id=\"menuContainer\"]/div/ul/li[3]/a"));
    	selectMyElement.click();
    	Thread.sleep(5000);
    	WebElement value=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menuContainer\"]/div/ul/li[3]/ul/li[1]/a")));
    	Thread.sleep(3000);
    	value.click();
    	Thread.sleep(3000);
    	Select account_search = new Select(driver.findElement(By.id("ctl00_cplMain_ddActiveAccounts")));
    	account_search.selectByVisibleText("2000016 - Terry White Chemmart Happy Valley");
    	driver.findElement(By.id("txtSearchProduct")).sendKeys("gloves");
    	driver.findElement(By.id("btnProductSearch")).click();
    }
    
    @Test(enabled=false)
    public void adding_products_to_cart() throws InterruptedException
    {
    	Thread.sleep(4000);
    	driver.findElement(By.id("ctl00_cplMain_rptProduct_txtOrderQuantity_5")).sendKeys("5");
    	Thread.sleep(2000);
    	driver.findElement(By.id("ctl00_cplMain_rptProduct_txtOrderQuantity_4")).sendKeys("2");
    	Thread.sleep(2000);
    	driver.findElement(By.id("btnAddToCart")).click();
    	Thread.sleep(5000);	
    }
    
    @Test(enabled=false)
    public void view_and_placeorder() throws InterruptedException
    {
    	Thread.sleep(2000);
    	((JavascriptExecutor) driver)
        .executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    	Thread.sleep(3000);
    	driver.findElement(By.id("ctl00_cplMain_btnViewShoppingCart")).click();    
    	Thread.sleep(4000);
    	driver.findElement(By.id("ctl00_cplMain_btnOrderCart2")).click();
    	Thread.sleep(3000);
    }
    
    @Test(priority=0)//(enabled=false)
    public void order_reference() throws InterruptedException
    {
    	login();
    	product_Search();
    	adding_products_to_cart();
    	view_and_placeorder();
    	driver.findElement(By.id("ctl00_cplMain_txtCustomerReference")).sendKeys("PR2349");
    	Thread.sleep(2000);
    	driver.findElement(By.id("ctl00_cplMain_btnSubmitOrder")).click();
    	Thread.sleep(2000);
    }
    
    @AfterMethod()
    public void teardown()
    {
    	driver.quit();
    }
}
