package seleniumgluecode;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


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
        //throw new PendingException();
    }




}
