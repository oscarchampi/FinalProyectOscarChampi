package org.example.Tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Post;

import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreatePet implements Task {

    private final String id;
    private final String nombre;
    private final String estado;

    public CreatePet(String id, String nombre, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public static Performable withData(Map<String, String> mascota) {
        return instrumented(
                CreatePet.class,
                mascota.get("id"),
                mascota.get("nombre"),
                mascota.get("estado")
        );
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        String body = """
                {
                    "id": %s,
                    "category": {
                        "id": 1,
                        "name": "Perros"
                    },
                    "name": "%s",
                    "photoUrls": [
                        "https://example.com/mascota.jpg"
                    ],
                    "tags": [
                        {
                            "id": 1,
                            "name": "Domestico"
                        }
                    ],
                    "status": "%s"
                }
                """.formatted(id, nombre, estado);

        actor.attemptsTo(
                Post.to("/pet")
                        .with(requestSpecification ->
                                requestSpecification
                                        .contentType(ContentType.JSON)
                                        .header("Accept", "application/json")
                                        .body(body)
                                        .log().all()
                        )
        );

        SerenityRest.lastResponse().body().prettyPrint();

        if (SerenityRest.lastResponse().statusCode() == 200) {

            String petId = SerenityRest.lastResponse()
                    .jsonPath()
                    .getString("id");

            OnStage.theActorInTheSpotlight()
                    .remember("petId", petId);

            System.out.println("Pet ID: " + actor.recall("petId"));
        }
    }
}
