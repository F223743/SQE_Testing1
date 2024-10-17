package selenium_mvn_eclipse;

import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.LoginPage;
import pages.ProductsPage;
import java.time.Duration;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    WebDriverWait wait;

    @Before
    public void setup() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "E:\\\\drivers\\\\chromedriver-win64\\\\chromedriver.exe");  // Update the path accordingly
        
        // Initialize the ChromeDriver
        driver = new ChromeDriver();
        
        // Maximize the browser window
        driver.manage().window().maximize();
        
        // Optional: Set an explicit wait time if needed
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Set 10-second wait
    }

    @Given("I am on the Sauce Demo login page")
    public void i_am_on_the_sauce_demo_login_page() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        wait.until(ExpectedConditions.visibilityOf(loginPage.usernameInput));
    }

    @When("I enter username {string}")
    public void i_enter_username(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void i_enter_password(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the Products page with title {string}")
    public void i_should_be_redirected_to_the_products_page_with_title(String expectedTitle) {
        wait.until(ExpectedConditions.titleIs("Swag Labs"));
        String actualTitle = driver.getTitle();
        assertEquals("Swag Labs", actualTitle);
    }


    @Then("I should see an error message on the login page")
    public void i_should_see_an_error_message_on_the_login_page() {
        WebElement errorMessage = loginPage.getErrorMessage();
        assertTrue(errorMessage.isDisplayed());
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
