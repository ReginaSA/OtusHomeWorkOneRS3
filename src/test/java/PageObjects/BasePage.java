package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public BasePage waitUntilPageIsLoaded(WebDriverWait wait, String urlPath) {
        wait.until(ExpectedConditions.urlContains(urlPath));
        return this;
    }

    @FindBy(xpath = "//div[contains(@class, 'subtitle-new') and normalize-space(text())='Популярные курсы']")
    private WebElement blockPopularCourse;

}
