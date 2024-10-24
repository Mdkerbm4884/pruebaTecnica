Feature: carito de compras
  usuario realizara compras

  Scenario: Usuario agrega producto
    Given el usaurio selecciona productos
    When el estado de producto cambia
    Then el producto se agrega al carrito


  Scenario:El usuario continua con la compra
    Given el usaurio realizara la compra
    When el sistema realizara la suma del valor de los productos
    Then el sistema realiza la suma correctamente
