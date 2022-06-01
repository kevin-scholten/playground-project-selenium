package com.example.playgroundprojectselenium.testportaal;

import com.example.playgroundprojectselenium.Helper;
import com.google.common.io.Resources;
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
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class GbaTests extends Helper {
    private TestPortaalPage testPortaalPage;

    @Before
    public void setUp() {
        try {
            setupThread("chrome");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        RemoteWebDriver driver = getDriver();
        driver.manage().window().maximize();
        driver.setFileDetector(new LocalFileDetector());
        // Maak time-out na 10 seconden..
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://host.docker.internal:14841/");
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

    @Test
    public void herschrijfNullBericht() {
        String nullBerichtTemplate = getStringFromFile("/gba/null-bericht-template.txt");
        String replaced = nullBerichtTemplate.replaceAll("900001030", "kevin12345").replaceAll("1000000070", "Akevin12345");
        try {
            File tempFile = File.createTempFile("null-bericht", ".txt");
            Path pathOfNewFile = Files.write(Paths.get(tempFile.getPath()), replaced.getBytes(StandardCharsets.UTF_8));

            testPortaalPage.kiesBestandBrpButton.sendKeys(pathOfNewFile.toString());
            testPortaalPage.uploadBrpButton.click();

            WebElement popup = testPortaalPage.feedbackPanelParagraafTekst;
            assertTrue(popup.isDisplayed());
            assertEquals("Uploaden van file was succesvol", popup.getText());

            tempFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}