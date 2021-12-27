package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;

public class CoursePage extends BasePage{

    public CoursePage(EventFiringWebDriver driver) {
        super(driver);
    }

    By checkLocator = By.cssSelector(".lessons__page");
    String categoryCourse = "//div[@class='nav__items course-categories__nav']";
    By dateCourse = By.xpath("//div[@class='lessons__new-item-start']");
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
     * Находит все даты курсов на страницы и возвращает массив веб-элементов
     * @return
     */
    public List<WebElement> findDateCourses() {
        List<WebElement> coursesList = driver.findElements(dateCourse);
        return coursesList;
    }

    /**
     * Метод выбор курса по датам
     * @return
     */

    public ArrayList<String> findDateCoursesOnPage() {
        List<WebElement> coursesDateList = findDateCourses();

        ArrayList<String> dateAndNameCourse = new ArrayList<>();

        for (int i = 0; i < coursesDateList.size(); i++) {
            List<String> dateCoursesList = new ArrayList<>();
            dateCoursesList.add(coursesDateList.get(i).getText());
            dateAndNameCourse.add(String.valueOf(dateCoursesList));
        }
        return dateAndNameCourse;
    }
}
