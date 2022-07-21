package playground;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

@Slf4j
public class WebDriverManager
{

    @Getter
    private static final WebDriverManager instance = new WebDriverManager();

    @Getter
    private RemoteWebDriver webDriver;

    private WebDriverManager()
    {
    }

    public RemoteWebDriver getWebDriver()
    {
        if (webDriver == null)
        {
            var options = getChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("plugins.plugins_disabled", new String[] { "Chrome PDF Viewer" });
            prefs.put("intl.accept_languages", "nl");
            options.setExperimentalOption("prefs", prefs);

            try
            {
                webDriver = createWebDriver(new URL(System.getProperty("webDriverUrl", "http://localhost:4444/wd/hub")), options);
                webDriver.setFileDetector(new LocalFileDetector());
            }
            catch (Exception e)
            {
e.printStackTrace();            }
        }
        return webDriver;
    }

    private ChromeOptions getChromeOptions()
    {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.getBoolean("seleniumHeadless"))
        {
            options.addArguments("--headless");
        }
        options.addArguments("--allow-insecure-localhost");
        options.addArguments("--disable-print-preview");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-notifications");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--lang=nl");
        options.addArguments("--disable-dev-shm-usage");
        return options;
    }

    private RemoteWebDriver createWebDriver(URL webDriverUrl, ChromeOptions options)
    {
        return new RemoteWebDriver(webDriverUrl, options)
        {
            @Override
            public void quit()
            {
                try
                {
                    this.switchTo().alert().accept();
                }
                catch (Exception ignored)
                {

                }
                super.quit();
                WebDriverManager.this.webDriver = null;
            }
        };
    }

}
