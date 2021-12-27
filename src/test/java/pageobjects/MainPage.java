package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.*;

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

    //Локатор блока с информацией о курсе
    By courseInfo = By.xpath("//div[@class='lessons__new-item-container']");
    //Локатор названия курса
    static By lessonTitle = By.xpath("//div[contains(@class, 'lessons__new-item-title')]");
    //Локатор ссылки на курс
    static By lessonUrl = By.xpath("//div[@class='lessons']/a[@href]");
    //Локтор даты начала курса
    static By timeStartLesson = By.xpath("//div[@class='lessons__new-item-time']");
    static By timeStartPopularLesson = By.xpath("//div[@class='lessons__new-item-start']");

    public ArrayList<ArrayList> getInfoCourses() {
        List<WebElement> courseName = driver.findElements(lessonTitle);
        List<WebElement> courseUrl = driver.findElements(lessonUrl);
        List<WebElement> courseTimeStart = driver.findElements(timeStartLesson);
        List<WebElement> popCourseTimeStart = driver.findElements(timeStartPopularLesson);

        ArrayList<ArrayList> coursesList = new ArrayList<>();
        for(int j =0; j < courseName.size(); j++) {
            ArrayList<String> courses = new ArrayList<String>();
            courses.add(courseName.get(j).getText());
            courses.add(courseUrl.get(j).getAttribute("href"));
            courses.add(courseTimeStart.get(j).getText());

            coursesList.add(courses);
        }
        return coursesList;
    }

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
     * Находит все курсы на странице и возвращает массив веб-элементов
     * @return
     */
    public List<WebElement> findCoursesOnPage() {
        List<WebElement> coursesList = driver.findElements(lessonTitle);
        return coursesList;
    }

    /**
     * Перебирает массив списка курсов и ищет нужный курс по заголовку
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
