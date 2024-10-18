package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Represents the Cart Page of Sauce Demo.
 */
public class CartPage {
    WebDriver driver;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locator for cart item (modify if necessary)
    public By getCartItemLocator() {
        return By.className("cart_item");  // Adjust this if the class name is incorrect
    }
}
