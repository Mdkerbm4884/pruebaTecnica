Feature: carito de compras
  usuario realizara compras

  Scenario: Usuario agrega producto
    Given el usaurio selecciona productos
    When el estado de producto cambia
    Then el producto se agrega al carrito

