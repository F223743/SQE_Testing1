package selenium_mvn_eclipse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Duration;

public class CartSteps {
    WebDriver driver;
    WebDriverWait wait;

    @Given("I am logged in to the Sauce Demo site")
    public void i_am_logged_in_to_the_sauce_demo_site() {
        WebDriverManager.chromedriver().setup();  // Set up ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the login page
        driver.get("https://www.saucedemo.com");

        // Perform login steps
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");  // You can replace with a valid user
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        // Verify that login was successful
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
    }

    @When("I click on the cart icon")
    public void i_click_on_the_cart_icon() {
        // Click the cart icon
        WebElement cartIcon = driver.findElement(By.id("shopping_cart_container"));
        cartIcon.click();
    }

    @When("I click on the checkout button")
    public void i_click_on_the_checkout_button() {
        // Wait for the cart page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_list")));

        // Click the checkout button
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();
    }

    @Then("I should be redirected to the checkout page")
    public void i_should_be_redirected_to_the_checkout_page() {
        // Verify that the checkout page is loaded
        WebElement checkoutHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        String pageTitle = checkoutHeader.getText();
        assert pageTitle.equals("CHECKOUT: YOUR INFORMATION");
    }
    
    @Then("I should be redirected to the cart page with a cart item displayed")
    public void i_should_be_redirected_to_the_cart_page_with_a_cart_item_displayed() {
        try {
            // Wait until the cart page is loaded
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_list")));
            
            // Check that a cart item is displayed
            WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_item")));
            
            // Verify that the cart item is visible on the cart page
            assertTrue("Cart item is not displayed!", cartItem.isDisplayed());
        } catch (Exception e) {
            fail("Failed to find the cart item on the cart page: " + e.getMessage());
        }
    }
}

