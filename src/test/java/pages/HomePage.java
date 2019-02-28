package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id = "comp-jhalo8eilink")
    public WebElement lnkShop;

    public HomePage(WebDriver driver) {

        super(driver);
    }

    public ShopPage GoToShop(){
        waitForElementToBeClickable(lnkShop);
        lnkShop.click();
        return new ShopPage(this.driver);

    }
}
