Feature: HU-002 seleccionar departamento
  Yo como usuario de HC
  Quiero seleccionar el departamento en la plataforma
  Para ver el departamento en el que me ubico

  Background:
    Given que me encuentro en la pagina de HC

  Scenario Outline: selecciono el departamento
    When seleccione el departamento <departamento>
    Then vere <departamento>
    Examples:
      | departamento |
      | ANTIOQUIA    |

  Scenario Outline: selecciono el departamento
    When seleccione el departamento <departamento>
    Then vere <departamento>
    Examples:
      | departamento |
      | CUNDINAMARCA    |

