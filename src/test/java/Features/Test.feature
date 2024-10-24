Feature: carito de compras
  usuario realizara compras

  Scenario: Escenario1 Usuario agrega producto
    Given el usaurio ingresa al sistema
    When el usuario selecciona productos
    Then el producto se agrega al carrito


  Scenario: Escenario2 El usuario continua con la compra
    Given el usaurio realizara la compra
    When el sistema realizara la suma del valor de los productos
    Then el sistema realiza la suma correctamente

  Scenario: Escenario3 el usuario quita comprar del carrito
    Given el usaurio ingresa nuevamente al sistema
    When el usuario selecciona y elimina prodcutos
    Then productos disponibles para compra nuevamente

  Scenario: Escenario4 Usuario no puede remover productos
    Given el usario seleciona los productos
    When usuario intenta remover productos
    Then sistema no habilita boton

  Scenario: Escenario5 Medir el tiempo que tarda el login
    Given se inicia seccion
    When mido el tiempo de espera
    Then el tiempo de espera es demaciado