package pageobjects;

import helpers.Course;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;
import static org.testng.Assert.assertFalse;

public class MainPage extends BasePage {

    public MainPage(EventFiringWebDriver driver) {
        super(driver);
    }

    String pageUrl = "https://otus.ru/";

    @FindBy(xpath = "//h1[contains(text(), 'Авторские онлайн‑курсы')]")
    WebElement pageTitle;

    @FindBy(xpath = "//a[contains(@class,'transitional-main__courses-more')]")
    WebElement openMoreCourses;

    @FindBy(xpath = "//div[contains(@class, 'lessons__new-item-title')]")
    private List<WebElement> coursesList;

    @FindBy(xpath = "//div[@class='lessons__new-item-time']")
    private List<WebElement> startDateOfCourses;

    @FindBy(xpath = "//div[contains(@class, 'lessons__new-item-title')]")
    private List<WebElement> lessonTitle;

    @FindBy(xpath = "//div[@class='lessons']/a[@href]")
    private List<WebElement> lessonUrl;

    public ArrayList<Course> getInfoCourses() {
        List<WebElement> courseName = lessonTitle;
        List<WebElement> courseUrl = lessonUrl;
        List<WebElement> courseTimeStart = startDateOfCourses;

        ArrayList<Course> coursesList = new ArrayList<>();
        for (int j = 0; j < courseName.size(); j++) {
            Course course = new Course(
                    courseName.get(j).getText(),
                    courseUrl.get(j).getAttribute("href"),
                    courseTimeStart.get(j).getText()
            );

            coursesList.add(course);
        }
        return coursesList;
    }

    public Date getFirstCourse() {
        ArrayList<Course> courses = getInfoCourses();
        Date maxDate = courses.stream().map(u -> u.getRawDate()).max(Date::compareTo).get();
        return maxDate;
    }

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
