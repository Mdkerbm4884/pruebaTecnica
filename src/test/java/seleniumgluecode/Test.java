package seleniumgluecode;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.build.Plugin;
import org.junit.Assert;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;
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
    metodosUtil metodosUtil = new metodosUtil(driver);

    @Given("el usaurio selecciona productos")
    public void elUsuarioSeEncuentraEnLaPaginaHome() {
       WebDriverManager.chromedriver().setup();
        //------ INICIALIZACION

        driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By by =By.id("continue");
        //------------------------------------


        //ingreso a la pagina y credenciales
        driver.get("https://www.saucedemo.com/"); // iNGRESO A LA PAGINA
        driver.manage().window().maximize(); //MAXIMIZA LA PAGINA

        metodosUtil.esperarElementoVisible(By.id(("user-name")));// se crea metedo para que el codigo avance solo cuando
        //el campo que se envie este visible

        //Se ingresan credenciales
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();


        //driver.manage().window().maximize();
    }

    @When("el estado de producto cambia")
    public void hace_click_en_la_opcion_para_ver_comic() throws Throwable {
       //Se agregaran compras
        metodosUtil.esperarElementoVisible(By.id("add-to-cart-sauce-labs-backpack"));
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
    }

    @Then("el producto se agrega al carrito")
    public void la_pagina_redirige_a_la_pantalla_de_comic() throws Throwable {
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

        //Valida que estos valores coinsidan

            if (totalSum==precioTotal){
                System.out.println("Suma correcta");
            }else {
                System.out.println("Valor no cincide");
                metodosUtil.capturar("ValorIncorrecto");
                System.exit(0);
            }
    }

    @Then("el sistema realiza la suma correctamente")
    public void Valirdar()throws Throwable {
        //Si la suma es correcta el sitema continua con la ejecucion
        driver.findElement(By.id("finish")).click();
        driver.findElement(By.id("back-to-products")).click();
    }



}
