# Automatización de Pruebas API - Petstore (Serenity BDD & Screenplay)

Este proyecto corresponde a la evaluación final del curso de automatización de pruebas. Consiste en un framework desarrollado en Java para validar los servicios REST de la API pública **Swagger Petstore**, aplicando el patrón de diseño **Screenplay** junto con **Cucumber** y **Serenity BDD**.

Autor: Oscar Champi Corrales

## Tecnologías utilizadas
* **Java 17**
* **Maven** (gestión de dependencias y ciclo de ejecución)
* **Serenity BDD & Serenity Screenplay**
* **REST-Assured** (consumo de servicios REST)
* **Cucumber** (definición de escenarios en Gherkin)

## Estructura del proyecto
Organizado siguiendo la separación de responsabilidades de Screenplay para mantener el código limpio y reutilizable:

```text
src
├── main/java/org/example
│   ├── Questions
│   │   └── ResponseCode.java        # Consulta del código de estado HTTP de la respuesta
│   └── Tasks
│       ├── CreatePet.java           # Petición POST para registrar una mascota
│       ├── GetPet.java              # Petición GET para consultar una mascota por ID
│       ├── FindPetsByStatus.java    # Petición GET para filtrar mascotas por estado
│       └── UpdatePet.java           # Petición PUT para actualizar datos de una mascota (si lo incluiste)
└── test
    ├── java/org/example
    │   ├── StepDefinition           # Mapeo de pasos Gherkin a acciones del actor
    │   └── Runner                   # Ejecutor principal de Cucumber con Serenity
    └── resources
        └── features                 # Escenarios de prueba en lenguaje Gherkin
