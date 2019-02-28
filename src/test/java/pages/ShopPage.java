package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ShopPage extends BasePage{

    public static final String BASE_PATH = "/shop";

    public static final String prodItemClass = "._3aG1r._3Gtdw";


    public ShopPage(WebDriver driver){
        super(driver);
    }

    public List<WebElement> GetProducts(){
        return driver.findElements(By.className(prodItemClass));
    }

    public void WaitForProductsToLoad(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("cZv4d")));
    }

    public ProductPage SelectProduct(String productName){

        WaitForProductsToLoad();
        String selector = "[alt="+"\""+ productName + "\"]";
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector(selector))).perform();
        actions.click(driver.findElement(By.cssSelector(selector))).perform();

        return new ProductPage(driver);

    }
}
