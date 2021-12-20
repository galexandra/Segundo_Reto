Feature: HU-003 seleccionar departamento
  Yo como usuario de HC
  Quiero seleccionar departamento en la plataforma
  Para ver el epartamento en el que me ubico


  Scenario:selecciono el departamento
    Given que me encuentro en la pagina de ML
    When selecciono HUILA
    Then podre ver HUILA


    Scenario: selecciono el departamento
      Given que me encuentro en la pagina de HC
      When selecciono UHILLA
      Then podre ver UHILLA


