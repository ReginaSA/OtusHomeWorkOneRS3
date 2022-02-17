package ru.otus.tests;

import helpers.Course;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageobjects.MainPage;
import utils.WebDriverFactory;
import utils.listeners.MyListeners;

import java.util.*;
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
    @Test(description = "Тест находит курсы по ключевым словам на главной странице")
    public void findCoursesByKeywordTest() {

        MainPage mp = new MainPage(driver);
        mp.openPage().checkPage();

        ArrayList<String> courseListResult = mp.findCourseByKeywords("Java");
        if (0 == courseListResult.size()) {
            System.out.println("По Вашему запросу не найдено ни одного курса.");
        } else {
            System.out.println("По вашему запросу найдены курсы:" + courseListResult);
        }
    }

    @Test
    public void findEarlyCourse(){
        MainPage mp = new MainPage(driver);
        mp.openPage().checkPage();
        mp.getInfoCourses();
        Date date = mp.getFirstCourse();
        System.out.println(date);




//        System.out.println(mp.getInfoCourses());
        }
}
