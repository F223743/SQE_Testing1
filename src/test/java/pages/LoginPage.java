package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents the Login Page of Sauce Demo.
 */
public class LoginPage {
    WebDriver driver;

    // Locators
    @FindBy(id = "user-name")
	public
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "login-button")
    WebElement loginButton;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Actions
    public void enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public WebElement getErrorMessage() {
        return driver.findElement(By.cssSelector(".error-message-container"));
    }

}