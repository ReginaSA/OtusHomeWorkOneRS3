package pageobjects;

import org.openqa.selenium.WebDriver;;
import org.openqa.selenium.support.PageFactory;;
import org.openqa.selenium.support.ui.WebDriverWait;

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
