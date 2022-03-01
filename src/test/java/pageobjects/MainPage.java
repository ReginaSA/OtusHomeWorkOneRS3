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

import static org.testng.Assert.assertFalse;

public class MainPage extends BasePage {

    public MainPage(EventFiringWebDriver driver) {
        super(driver);
    }

    String pageUrl = "https://otus.ru/";

    @FindBy(xpath = "//h1[contains(text(), 'Авторские онлайн‑курсы')]")
    WebElement pageTitle;

    @FindBy(xpath = "//div[contains(@class, 'lessons__new-item-title')]")
    private List<WebElement> coursesList;

    @FindBy(xpath = "//div[@class='lessons__new-item-start']")
    private List<WebElement> startDateOfCourses;

    @FindBy(xpath = "//div[contains(@class, 'lessons__new-item-title')]")
    private List<WebElement> lessonTitle;

    @FindBy(xpath = "//div[@class='lessons']/a[@href]")
    private List<WebElement> lessonUrl;


    public MainPage openPage() {
        driver.get(pageUrl);
        return this;
    }

    /**
     * Проверка, что страница открылась и доступна
     */
    public void checkPage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        assertFalse(driver.getPageSource().contains("error404"));
        wait.until(ExpectedConditions.textToBePresentInElement(pageTitle, "Авторские онлайн‑курсы"));
    }

    /** Метод выбирает список популярных курсов */
    public ArrayList<Course> getInfoCourses() throws ParseException {
        List<WebElement> courseName = lessonTitle;
        List<WebElement> courseUrl = lessonUrl;
        List<WebElement> courseTimeStart = startDateOfCourses;

        ArrayList<Course> coursesList = new ArrayList<>();
        for (int j = 0; j < courseTimeStart.size(); j++) {
            Course course = new Course(
                    courseName.get(j).getText(),
                    courseUrl.get(j).getAttribute("href"),
                    courseTimeStart.get(j).getText()
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
     * Находит все курсы на странице и возвращает массив веб-элементов
     *
     * @return
     */
    public List<WebElement> findCoursesOnPage() {
        List<WebElement> coursesList = this.coursesList;
        return coursesList;
    }

    /**
     * Перебирает массив списка курсов и ищет нужный курс по заголовку
     *
     * @return
     */

    public ArrayList<String> findCourseByKeywords(String keywords) {
        List<WebElement> coursesList = findCoursesOnPage();

        ArrayList<String> listCoursesByKeywords = new ArrayList<>();

        for (int i = 0; i < coursesList.size(); i++) {

            String courses = coursesList.get(i).getText();

            if (courses.contains(keywords)) {
                listCoursesByKeywords.add(courses);
            }
        }
        return listCoursesByKeywords;
    }
}
