package com.example.playgroundprojectselenium.testportaal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestPortaalPage {
    @FindBy(css = "input#gbaFile")
    public WebElement kiesBestandBrpButton;

    @FindBy(css = "button#gbaPutFile")
    public WebElement uploadBrpButton;

    @FindBy(css = ".feedbackPanelINFO span p")
    public WebElement feedbackPanelParagraafTekst;

    public TestPortaalPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
