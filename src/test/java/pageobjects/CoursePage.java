package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertFalse;

public class CoursePage extends BasePage{

    public CoursePage(WebDriver driver) {
        super(driver);
    }

    By checkLocator = By.cssSelector(".lessons__page");
    String categoryCourse = "//div[@class='nav__items course-categories__nav']";
    public static String pageUrl = "categories/";

    @FindBy(css = ".lessons__new-item")
    WebElement blockLesson;


    /**
     * Переход на страницу
     */
    public CoursePage goToPage() {
        String url = baseUrl + pageUrl;
        if (!driver.getCurrentUrl().equals(url))
            driver.get(url);
        checkPage();

        return this;
    }

    /**
     * Проверка, что страница открылась и доступна
     */
    public CoursePage checkPage() {
        assertFalse(driver.getPageSource().contains("error404"));
        wait.until(ExpectedConditions.presenceOfElementLocated(checkLocator));

        return this;
    }

    /**
     * Метод выбора курса по категории
     */
    public CoursePage findAngGoToCategoryByName(String name) {
        driver.findElement(By.xpath(categoryCourse + "/a[@title='" + name + "']")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[h1='" + name + "']"), name));
        return this;
    }

    /**
     * Метод выбор курса по датам
     */

    public void selectCourseByDate(String data) {
        wait.until(ExpectedConditions.elementToBeSelected(blockLesson));
    }


}
