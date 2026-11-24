package org.example.StepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import org.example.Tasks.CreatePet;
import org.example.Tasks.GetPet;
import org.example.Tasks.FindPetsByStatus;
import org.example.Questions.ResponseCode;
import org.example.Tasks.UpdatePet;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class PetStepDefinition {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el {actor} establece el endpoint de Petstore")
    public void elActorEstableceElEndpointDePetstore(Actor actor) {
        actor.whoCan(CallAnApi.at(
                "https://petstore.swagger.io/v2"
        ));
    }

    @When("el {actor} registra una mascota con los siguientes datos")
    public void elActorRegistraUnaMascotaConLosSiguientesDatos(
            Actor actor, DataTable dataTable) {

        Map<String, String> mascota =
                dataTable.asMaps(String.class, String.class).get(0);

        actor.attemptsTo(
                CreatePet.withData(mascota)
        );
    }

    @When("el {actor} consulta la mascota con ID {long}")
    public void elActorConsultaLaMascotaConID(Actor actor, Long id) {
        actor.attemptsTo(
                GetPet.byId(id)
        );
    }

    @When("el {actor} busca mascotas con estado {string}")
    public void elActorBuscaMascotasConEstado(Actor actor, String estado) {
        actor.attemptsTo(
                FindPetsByStatus.withStatus(estado)
        );
    }

    @Then("el {actor} valida que el codigo de respuesta sea {int}")
    public void elActorValidaElCodigoDeRespuesta(
            Actor actor, int responseCode) {

        actor.should(
                seeThat(
                        "El codigo de respuesta",
                        ResponseCode.getStatus(),
                        equalTo(responseCode)
                )
        );
    }

    @When("el {actor} actualiza la mascota con los siguientes datos")
    public void elActorActualizaLaMascotaConLosSiguientesDatos(
            Actor actor, DataTable dataTable) {

        Map<String, String> mascota =
                dataTable.asMaps(String.class, String.class).get(0);

        actor.attemptsTo(
                UpdatePet.withData(mascota)
        );
    }
}
