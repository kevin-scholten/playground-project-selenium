package playground.testportaal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import playground.Scherm;

public class TestPortaalPage extends Scherm {
    @FindBy(css = "input#gbaFile")
    public WebElement kiesBestandBrpButton;

    @FindBy(css = "button#gbaPutFile")
    public WebElement uploadBrpButton;

    @FindBy(css = ".feedbackPanelINFO span p")
    public WebElement feedbackPanelParagraafTekst;

    public TestPortaalPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void init() {

    }
}
