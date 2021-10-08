package pageObjects;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.Assert;

import java.util.List;
import java.util.NoSuchElementException;


public class SignInPage extends PageObject{

	@AndroidFindBy(id="app.choco.dummyqa:id/phone")
	private MobileElement phoneNumber;
	@AndroidFindBy(id="app.choco.dummyqa:id/countryCode")
	private MobileElement countryCodeSelector;
	@AndroidFindBy(id="app.choco.dummyqa:id/filterEditText")
	private MobileElement searchCountryCode;
	@AndroidFindBy(id="app.choco.dummyqa:id/title")
	private MobileElement selectCountry;
	@AndroidFindBy(id="app.choco.dummyqa:id/codeList")
	private MobileElement codeList;
	@AndroidFindBy(id="app.choco.dummyqa:id/success_title")
	private MobileElement successTitle;

	public SignInPage(AndroidDriver<MobileElement> driver) {
		super(driver);
	}
	WebDriverWait wait = new WebDriverWait(driver, 10);

	public void enterPhoneNumber(String phone) {
		this.phoneNumber.sendKeys(phone);
	}
	public void ClickCountryCode() {
		this.countryCodeSelector.click();
	}

	public void seacrCountryCode(String codeOrCountry) throws InterruptedException {
		this.searchCountryCode.click();
		this.searchCountryCode.sendKeys(codeOrCountry);
	}
	public void selectCountry(String codeOrCountry) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("title")));

		List<MobileElement> elements = driver.findElements(By.id("title"));
		for (MobileElement element:elements ) {
			if(element.getText().contains(codeOrCountry))
			{
				element.click();
				break;
			}
		}
	}
	public void verifyTextIsDisplayedOnScreen(String text){
		String xpath= "//*[contains(@text,"+"\'"+text+"')]";
		List<MobileElement> elements = driver.findElementsByXPath(xpath);
		if(elements.size() == 0){
			throw new NoSuchElementException("Text "+text+" is not present on screen");
		}
	}
	public void verifyTextIsNotDisplayedOnScreen(String text) throws InterruptedException {
		String xpath= "//*[contains(@text,"+"\'"+text+"')]";
		List<MobileElement> elements = driver.findElementsByXPath(xpath);
		if(elements.size() != 0){
			throw new NoSuchElementException("Text "+text+" is present on screen");
		}
	}

	public void tapElementWithText(String text){
		String xpath= "//*[@text="+"\'"+text+"']";
		List<MobileElement> elements = driver.findElementsByXPath(xpath);
		if(elements.size() == 0){
			throw new NoSuchElementException("Text "+text+" is not present on screen");
		}
		else {
			elements.get(0).click();
		}
	}
	public void EnterCode(String codeSentToUser) {
		char code[] = codeSentToUser.toCharArray();
		List<MobileElement> elements = codeList.findElements(By.className("android.widget.EditText"));
		for( int i =0; i< elements.size();i++ ){
			elements.get(i).sendKeys(Character.toString(code[i]));
		}
	}

	public void verifySuccessMessage(String message) throws Exception {
		try{
			Assert.isTrue(successTitle.isDisplayed(),"Success message not displayed");
			Assert.isTrue(successTitle.getText().equalsIgnoreCase(message),"\n Expected message is: "+message+"\n Displayed message is: "+ successTitle.getText());
		}
		catch (Exception e){
			throw new Exception(e.getMessage());
		}
	}



}
