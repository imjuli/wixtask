package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductPage extends BasePage{

    public static final String itemClassName = "item-name";
    public static final String cartCSSSelector = ".minicart.active";

    @FindBy(className = "_2qrJF")
    public WebElement lblProductName;

    @FindBy (css = "[data-hook=add-to-cart-button]")
    public WebElement btnAddToCart;

    @FindBy(className = "remove-item")
    public WebElement btnRemoveFromCart;

    @FindBy(className = "cart-widget-close")
    public WebElement btnMinimizeCart;


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void WaitForCartToAppear(){
        SwitchToIframe();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cartCSSSelector)));
    }

    public void SwitchToIframe(){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName("iframe")));
    }

    public boolean IsCartPresent(){
        WaitForCartToAppear();
        return driver.findElement(By.cssSelector(cartCSSSelector)).isDisplayed();
    }

    public void AddToCart(){
        waitForElementToBeClickable(btnAddToCart);
        btnAddToCart.click();

    }

    public void RemoveFromCart(String productNameToRemove) {

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(itemClassName)));

        Actions actions = new Actions(driver);

        List<WebElement> cartItemNames = driver.findElements(By.className(itemClassName));

        for (WebElement el : cartItemNames
             ) {
            if (el.getText().equals(productNameToRemove))
            {
                actions.moveToElement(el).perform();
                actions.moveToElement(btnRemoveFromCart).perform();
                btnRemoveFromCart.click();
                wait.until(ExpectedConditions.numberOfElementsToBe(By.className(itemClassName), 0));
            }
        }

    }

    public void MinimizeCart(){

        btnMinimizeCart.click();

    }
}
