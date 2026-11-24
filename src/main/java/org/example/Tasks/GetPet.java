package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetPet implements Task {

    private final Long id;

    public GetPet(Long id) {
        this.id = id;
    }

    public static Performable byId(Long id) {
        return instrumented(GetPet.class, id);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/pet/{id}").with(requestSpecification ->
                        requestSpecification
                                .pathParam("id", id)
                                .contentType(ContentType.JSON)
                                .log().all()
                )
        );
    }
}