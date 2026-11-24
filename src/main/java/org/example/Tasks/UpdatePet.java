package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class UpdatePet implements Task {

    private final Map<String, String> mascota;

    public UpdatePet(Map<String, String> mascota) {
        this.mascota = mascota;
    }

    public static Performable withData(Map<String, String> mascota) {
        return instrumented(UpdatePet.class, mascota);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", Long.parseLong(mascota.get("id")));
        body.put("name", mascota.get("nombre"));
        body.put("status", mascota.get("estado"));

        actor.attemptsTo(
                Put.to("/pet").with(requestSpecification ->
                        requestSpecification
                                .contentType(ContentType.JSON)
                                .body(body)
                                .log().all()
                )
        );
    }
}