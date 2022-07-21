package playground.steps;

import io.cucumber.java.nl.Als;
import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.Stel;
import org.springframework.beans.factory.annotation.Autowired;
import playground.Scherm;
import playground.context.TestContext;
import playground.testportaal.TestPortaalPage;

import java.time.LocalDateTime;

public abstract class BaseSteps {
    @Autowired
    protected TestContext testContext;

    @Autowired
    private Scherm scherm;

    public static LocalDateTime standaardTijd;

    protected Scherm gaNaarTestportaal(LocalDateTime standaardTijd) {
        return scherm.getScherm("http://localhost:14841/", TestPortaalPage.class);
    }

    @Als("dat de gebruiker het testportaalscherm aanvraagt")
    public void gebruikerVraagtTestportaalSchermAan() {

    }

    @Dan("moet het testportaalscherm correct werken")
    public void testenOfTestportaalSchermCorrectWerkt() {

    }
}
