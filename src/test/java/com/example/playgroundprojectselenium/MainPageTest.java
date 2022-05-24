package com.example.playgroundprojectselenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainPageTest extends Helper {
    private WebDriver driver;
    private MainPage mainPage;

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
        driver.get("https://www.jetbrains.com/");

        mainPage = new MainPage(driver);
    }

    @After
    public void tearDown() {
        tearDownDriver();
    }

    @Test
    public void search() {
        mainPage.searchButton.click();

        WebElement searchField = driver.findElement(By.cssSelector("[data-test='search-input']"));
        searchField.sendKeys("Selenium");

        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
        submitButton.click();

        WebElement searchPageField = driver.findElement(By.cssSelector("input[data-test='search-input']"));
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() {
        new Actions(driver)
                .moveToElement(mainPage.toolsMenu)
                .perform();

        WebElement menuPopup = driver.findElement(By.cssSelector("div[data-test='menu-main-popup-content']"));
        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeAllToolsButton.click();

        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }
}
