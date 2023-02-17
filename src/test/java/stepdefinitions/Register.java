package stepdefinitions;

import java.util.Map;
import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import junit.framework.Assert;
import pages.AccountSuccessPage;
import pages.HomePage;
import pages.RegisterPage;
import utils.CommonUtils;

public class Register {

	WebDriver driver;
	private RegisterPage registerPage;
	private AccountSuccessPage accountSuccessPage;
	private CommonUtils commonUtils;

	@Given("User navigates to Register Account Page")
	public void user_navigates_to_register_account_page() {
		driver = DriverFactory.getdriver();
		HomePage homepage = new HomePage(driver);
		homepage.clickOnMyAccount();
		registerPage=homepage.selectRegisterOption();
	}

	@When("User enters below details into the fields")
	public void User_enters_below_details_into_the_fields(DataTable datatable) {
		Map<String, String> asMap = datatable.asMap(String.class, String.class);
//		registerPage = new RegisterPage(driver);  					got reduced
		registerPage.enterFirstName(asMap.get("firstname"));
		registerPage.enterLastName(asMap.get("lastname"));
		commonUtils=new CommonUtils();
		registerPage.enterEmailAddress(commonUtils.getEmailWithTimeStamp());
		registerPage.enterTelephone(asMap.get("telephone"));
		registerPage.enterPassword(asMap.get("password"));
		registerPage.enterConfirmPassword(asMap.get("password"));
	}

	@When("User enters below details into the fields with duplicate account")
	public void User_enters_below_details_into_the_fields_with_duplicate_account(DataTable datatable) {
		Map<String, String> asMap = datatable.asMap(String.class, String.class);
//		registerPage = new RegisterPage(driver);   got reduced
		registerPage.enterFirstName(asMap.get("firstname"));
		registerPage.enterLastName(asMap.get("lastname"));
		registerPage.enterEmailAddress(asMap.get("emailaddress"));
		registerPage.enterTelephone(asMap.get("telephone"));
		registerPage.enterPassword(asMap.get("password"));
		registerPage.enterConfirmPassword(asMap.get("password"));

	}

	@When("Selects privacy policy field")
	public void selects_privacy_policy_field() {
		registerPage.selectPrivacyPolicy();

	}

	@When("Click on Continue button")
	public void click_on_continue_button() {
		accountSuccessPage=registerPage.clickContinueButton();

	}

	@Then("Account should get successfully created")
	public void account_should_get_successfully_created() {
	//	AccountSuccessPage asp = new AccountSuccessPage(driver);  got reduced
		Assert.assertEquals("Your Account Has Been Created!", accountSuccessPage.getPageHeading());
	}

	@When("Select Yes for Newsletter")
	public void select_yes_for_newsletter() {
		registerPage.selectYesNewsletter();
	}

	@Then("User should get proper warning about duplicate email")
	public void User_should_get_proper_warning_about_duplicate_email() {
		Assert.assertTrue(registerPage.getWarningMessageText()
				.contains("Warning: E-Mail Address is already registered!"));
	}

}
