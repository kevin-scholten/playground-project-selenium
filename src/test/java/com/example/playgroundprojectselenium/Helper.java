package com.example.playgroundprojectselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Helper {
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    public static String remote_url_chrome = "http://localhost:4444/wd/hub";
    public static String remote_url_firefox = "http://localhost:4444/wd/hub";

    public void setupThread(String browserName) throws MalformedURLException {
        if(browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            driver.set(new RemoteWebDriver(new URL(remote_url_chrome), options));
        } else if(browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            driver.set(new RemoteWebDriver(new URL(remote_url_firefox), options));
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public void tearDownDriver() {
        getDriver().quit();
    }
}
