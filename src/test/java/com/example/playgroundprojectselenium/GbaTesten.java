package com.example.playgroundprojectselenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class GbaTesten extends Helper {
    private WebDriver driver;
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

        // Maak time-out na 10 seconden..
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://testit-testportaal.test.landelijkescreening.nl/?1");

        testPortaalPage = new TestPortaalPage(driver);
    }

    @After
    public void tearDown() {
        tearDownDriver();
    }

    @Test
    public void uploadBRPClienten() {
        testPortaalPage.kiesBestandBrpButton.sendKeys("C:\\git\\playground-project-selenium\\src\\test\\resources\\gba\\ClientenTestset.csv");
        testPortaalPage.uploadBrpButton.click();
        WebElement popup = testPortaalPage.feedbackPanelParagraafTekst;
        assertTrue(popup.isDisplayed());

        String tekstInPopup = testPortaalPage.feedbackPanelParagraafTekst.getText();
        assertEquals("Uploaden van file was succesvol", tekstInPopup);
    }


}
