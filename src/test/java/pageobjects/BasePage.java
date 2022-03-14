package pageobjects;

import org.openqa.selenium.support.PageFactory;;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage<T> {

    private T page;

    protected EventFiringWebDriver driver;
    protected WebDriverWait wait;

    public static String baseUrl = "https://otus.ru/";

    public BasePage(EventFiringWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public T openPage() {
        driver.get(baseUrl);
        return page;
    }
}
