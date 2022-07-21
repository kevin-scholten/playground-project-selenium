package playground;

import javax.annotation.PostConstruct;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "nl.rivm.test.screenit.cucumber.context", "nl.rivm.screenit.zephyr.listener" })
public abstract class Scherm implements BeanFactoryAware
{
    protected static final int DEFAULT_PAGE_LOAD_TIMEOUT = 45;

    protected static final int DEFAULT_IMPLICITLY_WAIT = 5;

    protected static final int DEFAULT_SCRIPT_TIMEOUT = 5;

    private static final Logger LOG = LoggerFactory.getLogger(Scherm.class);

    @Autowired
    protected RemoteWebDriver driver;

    private BeanFactory beanFactory;

    protected Scherm()
    {
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException
    {
        this.beanFactory = beanFactory;
    }

    @Bean(destroyMethod = "quit")
    protected RemoteWebDriver chromeDriver()
    {
        return WebDriverManager.getInstance().getWebDriver();
    }

    @PostConstruct
    protected abstract void init();

    public <S extends Scherm> S getScherm(Class<S> clazz)
    {
        return beanFactory.getBean(getBeanName(clazz), clazz);
    }

    public <S extends Scherm> S getScherm(String url, Class<S> clazz)
    {
        driver.get(url);
        return getScherm(clazz);
    }

    protected <S extends Scherm> S getSchermMetBrowserPopup(String url, Class<S> clazz)
    {
        driver.get(url);
        driver.switchTo().alert().accept();
        return getScherm(clazz);
    }

    protected <S extends Scherm> S refreshScherm(Class<S> clazz)
    {
        String currentUrl = driver.getCurrentUrl();
        driver.get(currentUrl);
        return getScherm(clazz);
    }

    private String getBeanName(Class clazz)
    {
        String clazzSimpleName = clazz.getSimpleName();
        if (Character.isLowerCase(clazzSimpleName.charAt(1)))
        {
            return Character.toLowerCase(clazzSimpleName.charAt(0)) + clazzSimpleName.substring(1);
        }
        else
        {
            return clazzSimpleName;
        }

    }
}
