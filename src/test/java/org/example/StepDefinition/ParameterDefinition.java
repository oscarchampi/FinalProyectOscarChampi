package org.example.StepDefinition;

import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import org.junit.runners.Parameterized;

public class ParameterDefinition {
    @ParameterType(".*")
    public Actor actor (String actorName){
        return OnStage.theActorCalled(actorName);
    }
}
