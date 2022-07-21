package playground.context;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
public class TestContext
{
    public ScenarioContext scenarioContext = new ScenarioContext();

    public ScenarioContext getScenarioContext()
    {
        return scenarioContext;
    }
}