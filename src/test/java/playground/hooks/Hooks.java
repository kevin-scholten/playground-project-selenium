package playground.hooks;

import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;
import playground.steps.BaseSteps;

import java.time.LocalDateTime;

@Slf4j
public class Hooks {
    @Before(order = 1)
    public void setStandaardTijd() {
        standaardTijd = LocalDateTime.of(2013, 3, 4, 1, 0);
    }

    @Before(value = "@openTestportaal")
    public void openTestportaal() {
        gaNaarTestportaal(standaardTijd);
    }
}
