package org.example.Tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class FindPetsByStatus implements Task {

    private final String status;

    public FindPetsByStatus(String status) {
        this.status = status;
    }

    public static FindPetsByStatus withStatus(String status) {
        return Tasks.instrumented(FindPetsByStatus.class, status);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/pet/findByStatus")
                        .with(request -> request.queryParam("status", status))
        );
    }
}