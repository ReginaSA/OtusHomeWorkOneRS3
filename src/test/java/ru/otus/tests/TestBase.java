package ru.otus.tests;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestBase {

//    public void launchBrowser() {
//        ChromeOptions options = new ChromeOptions();
//        options.setScriptTimeout(Duration.ofSeconds(30));
//        options.addArguments("--kiosk");
//
//        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
//
//        ChromeDriver driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        actions = new Actions(driver);
//    }
}
