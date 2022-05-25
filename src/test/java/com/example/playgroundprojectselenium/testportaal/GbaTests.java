package com.example.playgroundprojectselenium.testportaal;

import com.example.playgroundprojectselenium.Helper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;

import java.io.File;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class GbaTests extends Helper {
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
        driver.get("https://testit-testportaal.test.landelijkescreening.nl/");

        testPortaalPage = new TestPortaalPage(driver);
    }
    @After
    public void tearDown() {
        tearDownDriver();
    }

    @Test
    public void uploadBRPClienten() {
        // Haal pad van clienten test set op
        File f = new File(System.getProperty("user.dir") + File.separator + "resources" + File.separator + "ClientenTestset.csv");
        LocalFileDetector detector = new LocalFileDetector();
        File f2 = detector.getLocalFile(f.getAbsolutePath());
        System.out.println("Resource path: " + f2.getAbsolutePath());

        // Selecteer Clienten Test set
        testPortaalPage.kiesBestandBrpButton.sendKeys(f2.getAbsolutePath());
        testPortaalPage.uploadBrpButton.click();

        // Controleer of popup positief is
        WebElement popup = testPortaalPage.feedbackPanelParagraafTekst;
        assertTrue(popup.isDisplayed());
        String tekstInPopup = testPortaalPage.feedbackPanelParagraafTekst.getText();
        assertEquals("Uploaden van file was succesvol", tekstInPopup);
    }
}