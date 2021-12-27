package ru.otus.tests;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.MainPage;
import utils.WebDriverFactory;
import utils.listeners.MyListeners;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Данный класс содержит тесты для поиска курсов по ключевым словам и по дате проведения
 *
 */
public class FindCoursesTest {

    protected EventFiringWebDriver driver;

    protected WebDriverWait wait;

    protected Actions actions;

    @BeforeClass
    public void setupWebDriver() {
//        driver = WebDriverFactory.setupDriver(WebDriverFactory.DriverType.CHROME);
        driver = new EventFiringWebDriver(WebDriverFactory.setupDriver(WebDriverFactory.DriverType.CHROME));
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
    @Test
    public void findCoursesByKeywordTest() {

        MainPage mp = new MainPage(driver);
        mp.openPage().checkPage();

        ArrayList<ArrayList<String>> courseListResult = mp.findCourseByKeywords("Специализация Java-разработчик");
        System.out.println("По вашему запросу найден курс:" + courseListResult);

        if (courseListResult == null) {
            System.out.println("По Вашему запросу не найдено ни одного курса.");
        }
    }

    @Test
    public void checkCategoryCoursePages(){
        MainPage mp = new MainPage(driver);
        mp.openPage().checkPage();
        mp.getInfoCourses();
        }
}
