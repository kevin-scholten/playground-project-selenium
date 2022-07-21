package playground;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:playground",
        monochrome = true)
public class CucumberSuite extends CucumberBaseTestSuite{
}
