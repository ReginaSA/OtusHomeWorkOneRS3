package pageobjects;

import org.openqa.selenium.WebDriver;;
import org.openqa.selenium.support.PageFactory;;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    public BasePage(EventFiringWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    protected EventFiringWebDriver driver;
    protected WebDriverWait wait;


    public static String baseUrl = "https://otus.ru/";
}
