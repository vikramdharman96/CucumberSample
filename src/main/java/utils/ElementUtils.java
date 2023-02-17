package utils;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {
	WebDriver driver;	
	
	public ElementUtils(WebDriver driver) {
		this.driver=driver;
	}
	
	
	public void clickOnElement(WebElement element,long durationInSeconds) {
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
			WebElement webelement = wait.until(ExpectedConditions.elementToBeClickable(element));
			
			webelement.click();
	}
	
	
	public void typeTextIntoElement(WebElement element,String textTobeTyped, long durationInSeconds) {
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(durationInSeconds));
			WebElement webelement = wait.until(ExpectedConditions.elementToBeClickable(element));
			
			webelement.click();
			webelement.clear();
			webelement.sendKeys(textTobeTyped);
	}
	
	public void selectOptionInDropDown(WebElement element,String dropDownOption, long durationInSeconds) {
			WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(durationInSeconds));
			WebElement webelement = wait.until(ExpectedConditions.elementToBeClickable(element));
			
			Select select=new Select(webelement);
			select.selectByVisibleText(dropDownOption);
	}
	
	//Wait For Alert Present
	public Alert waitForAlert(long durationInSeconds) {
			Alert alert=null;
			try {
			WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
			wait.until(ExpectedConditions.alertIsPresent());
			}catch(Throwable e) {
				e.printStackTrace();
			}
			
			return alert;
	}
	
	//Accept Alert
	public void acceptAlert(long durationInseconds) {
		Alert alert = waitForAlert(durationInseconds);
		alert.accept();
	}
	
	public void dismissAlert(long durationInseconds) {
		Alert alert = waitForAlert(durationInseconds);
		alert.dismiss();
	}
	
	public void mouseOverAndClick(WebElement element, long durationInseconds) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(durationInseconds));
		WebElement webelement = wait.until(ExpectedConditions.visibilityOf(element));
		
		Actions action =new Actions(driver);
		action.moveToElement(webelement).click().build().perform();

	}
	
	public WebElement waitForVisibilityOfElement(WebElement element,long durationInSeconds) {
		
		WebElement webelement = null;
		try {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(durationInSeconds));
		webelement = wait.until(ExpectedConditions.visibilityOf(element));
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		return webelement;

	}
	
	public void javaScriptClick(WebElement element,long durationInSeconds) {
		
		WebElement webelement = waitForVisibilityOfElement(element,durationInSeconds);
		JavascriptExecutor jse=((JavascriptExecutor)driver);
		
		jse.executeScript("arguments[0].click();", webelement);

	}
	
	public void javaScriptType(WebElement element, long durationInSeconds, String textToBeTyped) {
		WebElement webelement = waitForVisibilityOfElement(element, durationInSeconds);
		JavascriptExecutor jse=((JavascriptExecutor)driver);
		jse.executeScript("arguments[0].value='"+textToBeTyped+"'", webelement);
	}
	
	
	
	
	
	
	
	
	

}
