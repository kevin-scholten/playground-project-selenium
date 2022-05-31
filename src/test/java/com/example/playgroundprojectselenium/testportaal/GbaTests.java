package com.example.playgroundprojectselenium.testportaal;

import com.example.playgroundprojectselenium.Helper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.server.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class GbaTests extends Helper {
    private RemoteWebDriver driver;
    private TestPortaalPage testPortaalPage;

    @Before
    public void setUp() {
        try {
            setupThread("chrome");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver = getDriver();
        driver.manage().window().maximize();
        driver.setFileDetector(new LocalFileDetector());
        // Maak time-out na 10 seconden..
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://testit-testportaal.test.landelijkescreening.nl/");

        testPortaalPage = new TestPortaalPage(driver);
    }
    @After
    public void tearDown() {
        tearDownDriver();
    }

    @Test
    public void uploadBRPClienten() {
        testPortaalPage.kiesBestandBrpButton.sendKeys(getPathForResource("ClientenTestset.csv"));
        testPortaalPage.uploadBrpButton.click();

        WebElement popup = testPortaalPage.feedbackPanelParagraafTekst;
        assertTrue(popup.isDisplayed());
        assertEquals("Uploaden van file was succesvol", popup.getText());
    }

    public String getPathForResource(String resourceName) {
        if(System.getenv("IS_IN_DOCKER") != null) {
            return "/resources/" + resourceName;
        } else {
            return new File("src/test/resources/").getAbsolutePath()
                    +"/" + resourceName;
        }
    }
}