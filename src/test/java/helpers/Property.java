package helpers;

import utils.WebDriverFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {

    WebDriverFactory.DriverType browser;
    String keywords;
    private static final String PROP_FILE = "src/test/resources/application.properties";

    public WebDriverFactory.DriverType getBrowser() throws IOException {
        FileInputStream fis;
        Properties property = new Properties();

        fis = new FileInputStream(PROP_FILE);
        property.load(fis);
        String browserProp = property.getProperty("browser.name");
        return browser = WebDriverFactory.DriverType.valueOf(browserProp);
    }

    public void setBrowser(WebDriverFactory.DriverType browser) {
        this.browser = browser;
    }

    public String getKeywords() throws IOException {
        FileInputStream fis;
        Properties property = new Properties();

        fis = new FileInputStream(PROP_FILE);
        property.load(fis);
        return keywords = property.getProperty("keywords");
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
