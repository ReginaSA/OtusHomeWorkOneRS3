package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.Locator;

import static org.testng.Assert.assertFalse;

public abstract class BasePage {

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    protected WebDriver driver;
    protected WebDriverWait wait;

    public abstract void init(WebDriver driver);

    public static String baseUrl = "https://otus.ru/";


}
