Feature: Gestion de mascotas en Petstore API

  Background:
    Given el actor establece el endpoint de Petstore

  @CrearMascota
  Scenario: Registrar una mascota correctamente
    When el actor registra una mascota con los siguientes datos
      | id        | nombre   | estado    |
      | 987654321 | Firulais | available |
    Then el actor valida que el codigo de respuesta sea 200

  @ConsultarMascota
  Scenario: Consultar una mascota existente
    When el actor consulta la mascota con ID 987654321
    Then el actor valida que el codigo de respuesta sea 200

  @BuscarMascotas
  Scenario: Buscar mascotas disponibles
    When el actor busca mascotas con estado "available"
    Then el actor valida que el codigo de respuesta sea 200

  @MascotaInexistente
  Scenario: Consultar una mascota inexistente
    When el actor consulta la mascota con ID 999999999999
    Then el actor valida que el codigo de respuesta sea 404

  @ActualizarMascota
  Scenario: Actualizar los datos de una mascota existente
    When el actor actualiza la mascota con los siguientes datos
      | id        | nombre  | estado |
      | 987654321 | Pepito  | sold   |
    Then el actor valida que el codigo de respuesta sea 200