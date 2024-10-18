package selenium_mvn_eclipse;

import static org.junit.Assert.*;

import org.openqa.selenium.By;
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

public class ProductSteps {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    WebDriverWait wait;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("I am on the Products page")
    public void i_am_on_the_products_page() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);

        // Perform login to reach Products page
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();

        productsPage = new ProductsPage(driver);
        wait.until(ExpectedConditions.visibilityOf(productsPage.title));
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        String actualTitle = productsPage.getTitle();
        assertEquals(expectedTitle, actualTitle);
    }

    @When("I add the product with name {string} to the cart")
    public void i_add_the_product_with_name_to_the_cart(String productName) {
        productsPage.addProductToCart(productName);
        wait.until(ExpectedConditions.visibilityOf(productsPage.cartBadge)); // Wait for cart update
    }

    @Then("the product should be added to the cart")
    public void the_product_should_be_added_to_the_cart() {
        assertTrue(productsPage.isCartBadgeDisplayed());
        assertEquals("1", productsPage.getCartBadgeText());
    }

    @Then("the cart should display {int} item")
    public void the_cart_should_display_item(Integer expectedItemCount) {
        WebElement cartBadge = productsPage.cartBadge;
        String actualItemCount = cartBadge.getText();
        assertEquals(expectedItemCount.toString(), actualItemCount);
    }

    @When("I open the side menu")
    public void i_open_the_side_menu() {
        productsPage.openMenu();
    }

    @Then("the side menu should be visible")
    public void the_side_menu_should_be_visible() {
        WebElement sideMenu = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("react-burger-menu-container")));
        assertTrue(sideMenu.isDisplayed());
    }

    @When("I log out")
    public void i_log_out() {
        productsPage.logout();
    }

    @Then("I should be redirected to the login page")
    public void i_should_be_redirected_to_the_login_page() {
        wait.until(ExpectedConditions.urlContains("saucedemo.com/"));
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("saucedemo.com"));
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
