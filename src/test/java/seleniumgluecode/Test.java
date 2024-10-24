package seleniumgluecode;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;

public class Test {

    private ChromeDriver Driver;
    static WebDriver driver;
    private Connection connection;
    private long startTime;
    private long duration;
    metodosUtil metodosUtil = new metodosUtil(driver);

    @Given("el usaurio ingresa al sistema")
    public void IngresoSistema() {
       WebDriverManager.chromedriver().setup();
        //------ INICIALIZACION
        driver = new ChromeDriver();

        // se Carga de pagina
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize(); // MAXIMIZA LA PAGINA

        // Esperar a que el campo de usuario sea visible
        metodosUtil.esperarElementoVisible(By.id("user-name"));

        // Se ingresan credenciales
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("el usuario selecciona productos")
    public void agregarProductos() throws Throwable {
       //Se agregaran compras
        metodosUtil.esperarElementoVisible(By.id("add-to-cart-sauce-labs-backpack"));
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
    }

    @Then("el producto se agrega al carrito")
    public void CarritoCompras() throws Throwable {
        //se valida que la cantidad de prosuctos agregados sea la correcta
        driver.findElement(By.className("shopping_cart_link")).click();
        WebElement cartBadgeElement = driver.findElement(By.className("shopping_cart_badge"));
        String cartBadgeValue = cartBadgeElement.getText();

        try {
            int cartCount = Integer.parseInt(cartBadgeValue);
                if (cartCount == 2) {
                    System.out.println("El número de productos en el carrito es 2.");
                } else {
                    System.out.println("El número de productos en el carrito no es 2, es: " + cartCount);
                    //metodosUtil.capturar("Escenario1Then","C:/PruebasAutomatizadas/AutomatizacionMVM/");
                }
        } catch (NumberFormatException e) {
            System.out.println("Error: No se pudo convertir el valor del carrito a un número. Valor recibido: " + cartBadgeValue);
            Assert.fail("El valor del carrio no es un número válido: " + cartBadgeValue);
        }
    }

//------------ECENARIO #2------------------//
    @Given("el usaurio realizara la compra")
    public void ContinuarCompra() throws Throwable{
        //Se continua con el proceso de compra
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Brayan");
        driver.findElement(By.id("last-name")).sendKeys("Burgos");
        driver.findElement((By.id("postal-code"))).sendKeys("111111");
        driver.findElement((By.id("continue"))).click();
    }

    @When("el sistema realizara la suma del valor de los productos")
    public void ValidaPrecios()throws Throwable {

        WebElement precioElemento = driver.findElement(By.cssSelector(".summary_subtotal_label[data-test='subtotal-label']"));
        String Total = precioElemento.getText();
        String precioSinDolar = Total.replace("$","")
                .replace("Item total:","").trim();
        double precio = Double.parseDouble(precioSinDolar);

        WebElement valorTax = driver.findElement(By.cssSelector(".summary_tax_label[data-test='tax-label']"));
        String tax = valorTax.getText();
        String taxsinDolar = tax.replace("$","")
                .replace("Tax:","").trim();
        double TaxTotal = Double.parseDouble(taxsinDolar);

        WebElement ElemtValorTotal = driver.findElement(By.cssSelector(".summary_total_label[data-test='total-label']"));
        String ValorTotal = ElemtValorTotal.getText();
        String ValorTotalsinDolar = ValorTotal.replace("$","")
                .replace("Total:","").trim();
        double precioTotal = Double.parseDouble(ValorTotalsinDolar);

        //SUMA LOS DOS VALORES
        double totalSum = TaxTotal + precio;
        System.out.println(precioTotal);

        //Valida que estos valores coincidan

            if (totalSum==precioTotal){
                System.out.println("Suma correcta");
            }else {
                System.out.println("Valor no cincide");
                //metodosUtil.capturar("Escenario2When","C:/PruebasAutomatizadas/AutomatizacionMVM/");
                System.exit(0);
            }
    }

    @Then("el sistema realiza la suma correctamente")
    public void Valirdar()throws Throwable {
        //Si la suma es correcta el sitema finaliza la compra
        driver.findElement(By.id("finish")).click();

        //driver.navigate().refresh(); //Refrescar cache


    }

    //---ESCENARIO 3----------
    @Given("el usaurio ingresa nuevamente al sistema")
    public void NuevoIngresoSistema() {
        //Usurio retoma a la pantalla inicial
        driver.findElement(By.id("back-to-products")).click();//Finaliza Compra

    }
    @When("el usuario selecciona y elimina prodcutos")
    public void eliminarProducto()throws Throwable{

        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        //metodosUtil.capturar("Evidenciacarrito","C:/PruebasAutomatizadas/AutomatizacionMVM/Ep3EvidenciaWHen/");
    }

    @Then("productos disponibles para compra nuevamente")
    public void  cambioStado(){

        //Se eliminara los productos y se tomara evidencia
        driver.findElement(By.id("remove-sauce-labs-bike-light")).click();
        //metodosUtil.capturar("Carritovacio","C:/PruebasAutomatizadas/AutomatizacionMVM/Ep3EvidenciaThen/");
        driver.findElement(By.id("continue-shopping")).click();
        metodosUtil.esperarElementoVisible(By.id("item_4_title_link"));
        driver.findElement(By.id("item_4_title_link")).click();
        //metodosUtil.capturar("ProductoDisponible","C:/PruebasAutomatizadas/AutomatizacionMVM/Ep3EvidenciaThen/");
    }

//------------Escenario4----------------
    @Given("el usario seleciona los productos")
    public  void ingresaProductos(){
        driver.findElement(By.id("react-burger-menu-btn")).click();
        metodosUtil.esperarElementoVisible(By.id("logout_sidebar_link"));
        driver.findElement(By.id("logout_sidebar_link")).click();
        driver.findElement(By.id("user-name")).sendKeys("problem_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("usuario intenta remover productos")
    public void usuario_intenta_remover_productos() {
        //usuario agrega prodcuto
        //metodosUtil.capturar("EstadoACTUAL","C:/PruebasAutomatizadas/AutomatizacionMVM/Ep4EvidencisWhen/");
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();


    }
    @Then("sistema no habilita boton")
    public void sistema_no_habilita_boton() {
        //Validar que el boton este hablitado
        WebElement botonEliminar = driver.findElement(By.id("remove-sauce-labs-backpack"));

        boolean habilitado = botonEliminar.isEnabled();
        if (habilitado){
            System.out.println("Boton habilidato");
            //metodosUtil.capturar("Boton","C:/PruebasAutomatizadas/AutomatizacionMVM/EpEvidenciaThen/");
        }else {
            System.out.println("boton No habiltado");
            //metodosUtil.capturar("Boton","C:/PruebasAutomatizadas/AutomatizacionMVM/EpEvidenciaThen/");
        }


    }

    @Given("se inicia seccion")
    public void se_inicia_seccion() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        metodosUtil.esperarElementoVisible(By.id("logout_sidebar_link"));
        driver.findElement(By.id("logout_sidebar_link")).click();
        driver.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }
    @When("mido el tiempo de espera")
    public void mido_el_tiempo_de_espera() {
        startTime = System.currentTimeMillis(); // Registra el tiempo de inicio

        WebElement logo = null;
        long timeout = 3000; //
        long pollingInterval = 100; // Intervalo de sondeo en milisegundos
        while ((System.currentTimeMillis() - startTime) < timeout) {
            try {
                logo = driver.findElement(By.className("app_logo")); // Busca el logo
                if (logo.isDisplayed()) {
                    break; // Si se encuentra y es visible, sale del bucle
                }
            } catch (Exception e) {
                // Ignora la excepción si el elemento no está aún disponible
            }
            try {
                Thread.sleep(pollingInterval); // Espera un poco antes de volver a intentar
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        duration = System.currentTimeMillis() - startTime; // Calcula la duración
    }
    @Then("el tiempo de espera es demaciado")
    public void el_tiempo_de_espera_es_demaciado() {
        if (duration > 2) {
            System.out.println("El logo fue visible después de " + (duration ) + " segundos, que supera los 3 segundos.");
        } else {
            System.out.println("El logo fue visible después de " + (duration) + " segundos.");
        }
        driver.quit(); // Cierra el navegador
    }
}
