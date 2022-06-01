package com.example.playgroundprojectselenium;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Helper {
    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    public void setupThread(String browserName) throws MalformedURLException {
        if(browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            driver.set(new RemoteWebDriver(new URL(getRemoteUrl()), options));
        } else if(browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            driver.set(new RemoteWebDriver(new URL(getRemoteUrl()), options));
        }
    }

    public static RemoteWebDriver getDriver() {
        return driver.get();
    }

    public void tearDownDriver() {
        getDriver().quit();
    }

    public boolean isInDocker() {
        return System.getenv("IS_IN_DOCKER") != null;
    }

    public String getRemoteUrl() {
        if(isInDocker())
            return "http://seleniumhub:4444/wd/hub";
        else return "http://localhost:4444/wd/hub";
    }

    public String getPathForResource(String resourceRelativePath) {
        if(System.getenv("IS_IN_DOCKER") != null) {
            return "/resources/" + resourceRelativePath;
        } else {
            return new File("src/test/resources/").getAbsolutePath()
                    +"/" + resourceRelativePath;
        }
    }

    public String getStringFromFile(String resourceRelativePath) {
        try {
            return Files.readString(Paths.get(getPathForResource(resourceRelativePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
