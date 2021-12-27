package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;

public class MainPage extends BasePage{

    public MainPage(EventFiringWebDriver driver) {
        super(driver);
    }

    String pageUrl = "https://otus.ru/";

    @FindBy(xpath = "//h1[contains(text(), 'Авторские онлайн‑курсы')]")
    WebElement pageTitle;

    @FindBy(xpath = "//a[contains(@class,'transitional-main__courses-more')]")
    WebElement openMoreCourses;

    By lessonTitle = By.xpath("//div[contains(@class, 'lessons__new-item-title')]");

    public MainPage openPage() {
        driver.get(pageUrl);
        return this;
    }

    /** Проверка, что страница открылась и доступна */
    public void checkPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        assertFalse(driver.getPageSource().contains("error404"));
        wait.until(ExpectedConditions.textToBePresentInElement(pageTitle, "Авторские онлайн‑курсы"));
    }

    /** Находит запрашиваемый блок курсов */
    public void findCourseUnit (String unitName) {
        driver.findElement(By.xpath("//div[contains(@class, 'subtitle-new') and normalize-space(text())='" + unitName + "']"));
    }

    /**
     * Находит все курсы на страницы и возвращает массив веб-элементов
     * @return
     */
    public List<WebElement> findCoursesOnPage() {
        List<WebElement> coursesList = driver.findElements(lessonTitle);
        return coursesList;
    }

    /**
     * Перебирает массив списка курсов и ищет по ключевым словам
     * @return
     */

    public ArrayList<ArrayList<String>> findCourseByKeywords(String keywords) {
        List<WebElement> coursesList = findCoursesOnPage();

        ArrayList<ArrayList<String>> listCoursesByKeywords = new ArrayList<>();

        for (int i = 0; i < coursesList.size(); i++) {

            ArrayList<String> courses = new ArrayList<String>();
            courses.add(coursesList.get(i).getText());

            if (courses.contains(keywords)) {
                listCoursesByKeywords.add(courses);
            }
        }
        return listCoursesByKeywords;
    }

    /** Кликает по кнопке Больше курсов js, так как кнопка перекрыта другим элементом */
    public void findAndClickMoreCourses() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", openMoreCourses);
    }
}
