package stepdefinitions;
import org.openqa.selenium.WebDriver;
import factory.DriverFactory;
import io.cucumber.java.en.*;
import junit.framework.Assert;
import pages.AccountPage;
import pages.HomePage;
import pages.LoginPage;
import utils.CommonUtils;

public class Login {
	
	WebDriver driver;
	private LoginPage lp;
	private AccountPage ac;
	private CommonUtils commonUtils;
		
	@Given("User navigates to login page")
	public void user_navigates_to_login_page() {
		driver = DriverFactory.getdriver();
		HomePage home=new HomePage(driver);
		home.clickOnMyAccount();
		lp = home.selectLoginOption();
	    
	}

    @When("^User enters valid email (.+)$")
    public void user_enters_valid_email(String emailText)  {
    	//LoginPage lp=new LoginPage(driver);   reduced by adding return type tp selectLoginOPtion method
    	lp.enterEmailAddress(emailText);
    	}

    @And("^Enters valid password (.+)$")
    public void enters_valid_password(String passwordText)  {
    	lp.enterPassword(passwordText);
    	
    }

	@When("Click on login button")
	public void click_on_login_button() {
		ac=lp.cliclLogin();
	}

	@Then("User should login successfully")
	public void user_should_login_successfully() {
		//AccountPage ac=new AccountPage(driver);     got reduced
	    Assert.assertTrue(ac.displayStatusOfEditYourAccountInformationOption());
	}
	
	@When("User enters invalid Email address")
	public void user_enters_invalid_email_address() {
	//	lp=new LoginPage(driver);
		commonUtils=new CommonUtils();
		lp.enterEmailAddress(commonUtils.getEmailWithTimeStamp());
	   	}

	@When("Enters invalid Password {string}")
	public void enters_invalid_password(String invalidPasswordText) {
		lp.enterPassword(invalidPasswordText);
		
	}
	@Then("User should get a proper warning message about credentials mismatch")
	public void user_should_get_a_proper_warning_message_about_credentials_mismatch() {
		Assert.assertTrue(lp.getWarningMessage().contains("Warning: No match for E-Mail Address and/or Password."));
		
	}
	


}
