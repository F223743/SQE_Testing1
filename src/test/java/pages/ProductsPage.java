package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage {
    WebDriver driver;

    @FindBy(className = "title")
    public WebElement title;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(xpath = "//span[@class='shopping_cart_badge']")
	public WebElement cartBadge;

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to get the page title
    public String getTitle() {
        return title.getText();
    }

    // Method to add a product to the cart by name
    public void addProductToCart(String productName) {
        WebElement product;
        try {
            product = driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//button[contains(text(), 'Add to cart')]"));
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Product with name '" + productName + "' not found.");
        }
        product.click();
    }

    // Method to check if the cart badge is displayed
    public boolean isCartBadgeDisplayed() {
        return cartBadge != null && cartBadge.isDisplayed();
    }

    // Method to get the cart badge text (e.g., number of items)
    public String getCartBadgeText() {
        if (isCartBadgeDisplayed()) {
            return cartBadge.getText();
        } else {
            return "0";
        }
    }

    // Method to remove a product from the cart by name
    public void removeProductFromCart(String productName) {
        WebElement removeButton = driver.findElement(By.xpath("//div[text()='" + productName + "']/following-sibling::button[text()='Remove']"));
        removeButton.click();
    }

    // Method to open the side menu
    public void openMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-menu-btn")));
        menuButton.click();
    }

    // Method to verify if the menu is displayed
    public boolean isMenuDisplayed() {
        return driver.findElement(By.id("react-burger-menu-container")).isDisplayed();
    }

    // Method to log out
    public void logout() {
        openMenu();
        logoutLink.click();
    }
}
