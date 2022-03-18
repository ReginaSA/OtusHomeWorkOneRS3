package pageobjects;

import helpers.Course;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertFalse;

public class MainPage extends BasePage {

    public MainPage(EventFiringWebDriver driver, String url) {
        super(driver, url);
    }

    @FindBy(css = ".title-new__text")
    WebElement pageTitle;

    @FindBy(css = ".lessons__new-item-title")
    protected List<WebElement> coursesList;

    @FindBy(css = ".lessons__new-item-start")
    protected List<WebElement> startDateOfCourses;

    @FindBy(css = ".lessons__new-item-title")
    protected List<WebElement> lessonTitle;

    @FindBy(xpath = "//div[@class='lessons']/a[@href]")
    protected List<WebElement> lessonUrl;

    /**
     * Проверка, что страница открылась и доступна
     */
    public void checkPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        assertFalse(driver.getPageSource().contains("error404"));
        wait.until(ExpectedConditions.textToBePresentInElement(pageTitle, "Авторские онлайн‑курсы"));
    }

    /** Метод выбирает список популярных курсов */
    public ArrayList<Course> getInfoCourses() {

        ArrayList<Course> coursesList = new ArrayList<>();
        for (int j = 0; j < startDateOfCourses.size(); j++) {
            Course course = new Course(
                    lessonTitle.get(j).getText(),
                    lessonUrl.get(j).getAttribute("href"),
                    startDateOfCourses.get(j).getText()
            );

            coursesList.add(course);
        }
        return coursesList;
    }
    /**
     * Метод сортирует популярные курсы по датам и выводит самый ранний
     */
    public Course getBeforeCourse() throws ParseException {
        ArrayList<Course> courses = getInfoCourses();
        Collections.sort(courses, Comparator.comparing(Course::getTimeStartBegin));
        return courses.get(0);
    }

    /**
     * Метод сортирует популярные курсы по датам и выводит самый поздний
     */
    public Course getAfterCourse() throws ParseException {
        ArrayList<Course> courses = getInfoCourses();
        Collections.min(courses, Comparator.comparing(Course::getTimeStartBegin));
        return courses.get(0);
    }

    /**
     * Выделить и перейти на страницу курса
     */
    public void clickAndGoToCourse(String nameCourse) {
        WebElement crs = driver.findElement(By.xpath("//div[contains(@class, 'lessons__new-item-title') and normalize-space(text())='" + nameCourse + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", crs);
        Actions builder = new Actions(driver);
        builder.moveToElement(crs)
                .build()
                .perform();
        crs.click();
    }

    /**
     * Перебирает массив списка курсов и ищет нужный курс по заголовку
     */
    public ArrayList<String> findCourseByKeywords(String keywords) {
        ArrayList<String> listCoursesByKeywords = (ArrayList<String>) coursesList.stream()
                .map(WebElement::getText)
                .filter(coursesList -> coursesList.contains(keywords))
                .collect(Collectors.toList());
        return listCoursesByKeywords;
    }
}
