package pageobjects;

import org.openqa.selenium.support.PageFactory;;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage<T> {

    protected T page;

    protected EventFiringWebDriver driver;
    protected WebDriverWait wait;
    protected String url;

    public BasePage(EventFiringWebDriver driver, String url) {
        this.driver = driver;
        this.url = url;

        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public T openPage() {
        driver.get(url);
        return this.page;
    }
}
