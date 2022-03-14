package ru.otus.tests;

import helpers.Course;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;
import pageobjects.MainPage;
import utils.listeners.MyListeners;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static utils.WebDriverFactory.setupDriver;

/**
 * Данный класс содержит тесты для поиска курсов по ключевым словам и по дате проведения
 */
public class FindCoursesTest {

    protected EventFiringWebDriver driver;

    @BeforeClass
    public void setupWebDriver() throws IOException {
        driver = new EventFiringWebDriver(setupDriver(System.getProperty("browser")));
        driver.register(new MyListeners());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterClass
    public void clearTestDate() {
        driver.quit();
    }

    /**
     * Тест находит курсы по ключевым словам на главной странице
     */
    @Test(description = "Тест находит курсы по ключевым словам на главной странице")
    public void findCoursesByKeywordTest() throws IOException {

        MainPage mp = new MainPage(driver);
        mp.openPage().checkPage();
        String keywords = System.getProperty("keywords");
        ArrayList<String> courseListResult = mp.findCourseByKeywords(keywords);
        if (0 == courseListResult.size()) {
            System.out.println("По Вашему запросу не найдено ни одного курса.");
        } else {
            System.out.println("По вашему запросу найдены курсы:" + courseListResult);
        }
    }

    /**
     * Тест находит самый ранний курс и переходит на его страницу
     */
    @Test
    public void findEarlyCourse() throws ParseException {
        MainPage mp = new MainPage(driver);
        mp.openPage().checkPage();
        Course beforeCourse = mp.getBeforeCourse();
        System.out.println("Самый ранний курс '" + beforeCourse.getName() + "' стартует " + beforeCourse.getRawDate()
                + " чтобы узнать подробнее, перейдите по ссылке: " + beforeCourse.getLink());
        mp.clickAndGoToCourse(beforeCourse.getName());
    }
    /**
     * Тест находит самый поздний курс и переходит на его страницу
     */
    @Test
    public void findLatestCourse() throws ParseException {
        MainPage mp = new MainPage(driver);
        mp.openPage().checkPage();
        Course afterCourse = mp.getAfterCourse();
        System.out.println("Самый поздний курс '" + afterCourse.getName() + "' стартует " + afterCourse.getRawDate()
                + " чтобы узнать подробнее, перейдите по ссылке: " + afterCourse.getLink());
        mp.clickAndGoToCourse(afterCourse.getName());
    }
}
