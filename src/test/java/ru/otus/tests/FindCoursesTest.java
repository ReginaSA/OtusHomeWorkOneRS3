package ru.otus.tests;

import pageobjects.CoursePage;
import pageobjects.MainPage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import utils.WebDriverFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class FindCoursesTest {

    protected WebDriver driver;

    protected WebDriverWait wait;

    protected Actions actions;

    @BeforeClass
    public void setupWebDriver() {
        driver = WebDriverFactory.setupDriver(WebDriverFactory.DriverType.CHROME);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void clearTestDate() {
        driver.quit();
    }

    @Test
    public void findPopularCoursesTest() {

        MainPage mp = new MainPage(driver);
        mp.openPage().checkPage();

        ArrayList<ArrayList<String>> courseListResult = mp.findCourseByKeywords("Специализация Android");
        System.out.println(courseListResult);

        if (courseListResult == null) {
            System.out.println("По Вашему запросу не найдено ни одного курса.");
        }
    }

//    @Test
//    public void checkCategoryCoursePages() {
//        CoursePage coursePage = new CoursePage(driver);
//        coursePage.goToPage().checkPage();
//        coursePage.findAngGoToCategoryByName("Программирование")
//                .findAngGoToCategoryByName("Инфраструктура")
//                .findAngGoToCategoryByName("Data Science")
//                .findAngGoToCategoryByName("GameDev")
//                .findAngGoToCategoryByName("Управление")
//                .findAngGoToCategoryByName("Тестирование")
//                .findAngGoToCategoryByName("Корпоративные курсы");
//    }


}
