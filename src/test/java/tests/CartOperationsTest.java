package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.ProductPage;
import pages.ShopPage;

public class CartOperationsTest {

    WebDriver driver;
    HomePage homePage;
    String testURL = BasePage.BASE_URL;
    String prod1 = "Premium Glasses";

    @BeforeClass
    public void TestSetup (){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(testURL);
        homePage = new HomePage(driver);
    }

    @Test
    public void TestCartOperations() {

        ShopPage shopPage = homePage.GoToShop();
        Assert.assertNotNull(shopPage.GetProducts());

        ProductPage productPage = shopPage.SelectProduct(prod1);
        Assert.assertEquals(productPage.lblProductName.getText(), prod1);

        productPage.AddToCart();
        Assert.assertTrue(productPage.IsCartPresent());

        productPage.RemoveFromCart(prod1);
        //check msg cart is empty

        productPage.MinimizeCart();

    }


    @AfterClass
    public void Teardown() {
        driver.quit();
    }
}
