package ru.otus.tests;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.listeners.MyListeners;

import java.util.concurrent.TimeUnit;

import static utils.WebDriverFactory.setupDriver;

public class SuiteFixtures {

    public EventFiringWebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setupWebDriver(){
        driver = new EventFiringWebDriver(setupDriver(System.getProperty("browser")));
        driver.register(new MyListeners());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterSuite(alwaysRun = true)
    public void clearTestDate() {
        driver.quit();
    }
}
