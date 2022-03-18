package ru.otus.tests;

import helpers.Course;
import org.testng.annotations.*;
import pageobjects.MainPage;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Данный класс содержит тесты для поиска курсов по ключевым словам и по дате проведения
 */
public class FindCoursesTest extends SuiteFixtures{

    public String url = "https://otus.ru/";

    /**
     * Тест находит курсы по ключевым словам на главной странице
     */
    @Test(description = "Тест находит курсы по ключевым словам на главной странице")
    public void findCoursesByKeywordTest() throws IOException {

        MainPage mp = new MainPage(driver, url);
        mp.openPage();
        mp.checkPage();
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
        MainPage mp = new MainPage(driver, url);
        mp.openPage();
        mp.checkPage();
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
        MainPage mp = new MainPage(driver, url);
        mp.openPage();
        mp.checkPage();
        Course afterCourse = mp.getAfterCourse();
        System.out.println("Самый поздний курс '" + afterCourse.getName() + "' стартует " + afterCourse.getRawDate()
                + " чтобы узнать подробнее, перейдите по ссылке: " + afterCourse.getLink());
        mp.clickAndGoToCourse(afterCourse.getName());
    }
}
