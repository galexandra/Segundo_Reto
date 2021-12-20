Feature: HU-001 Buscador Home Center
  Yo como usuario de HC
  Quiero buscar un producto en la plataforma
  Para ver el nombre del producto en pantalla

  Scenario Outline: Buscar producto
    Given que me encuentro en la pagina de ML
    When busque el producto <NombreProducto>
    Then podre ver <NombreProducto> en pantalla
    Examples:
      |NombreProducto|
      |Silla PC Escritorio Heavy Duty Piscis|
    |Silla MyWay Polipropileno Filtro UV Blanco|
    |Silla Ejecutiva con Brazos Negra y Azul   |
    |Silla Ejecutiva Cromada con Brazos Negra  |
    |Silla PC Escritorio Senna                 |
