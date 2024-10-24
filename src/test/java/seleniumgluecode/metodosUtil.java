package seleniumgluecode;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;


import java.time.Duration;

public class metodosUtil {
    private WebDriver driver;

    // Constructor para inicializar el WebDriver
    public metodosUtil(WebDriver driver) {
        this.driver = driver;

    }

    //METODO PARA QUE NO CONTINUE HASTA QUE EL CAMPO SEA VISIBLE
    public WebElement esperarElementoVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("El elemento no fue visible");
            return null;
        }
    }
    //METODO PARA CAPTURAR IMAGEN Y GUARDARLA
    public void capturar(String nombreArchivo, String Ruta) {
        try {
            //setDriver(driver);
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destino = new File(Ruta + nombreArchivo + ".png"); // Cambia la ruta
            FileHandler.copy(screenshot, destino);
            System.out.println("Captura de pantalla guardada en: " + destino.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al guardar la captura de pantalla: " + e.getMessage());
        }
    }

    //ACTUALIZA EL DRIVER SI NECESARIO
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void Login(String userName, String password) {
        WebDriverManager.chromedriver().setup();

        // No crees una nueva instancia de driver aquí
        //driver.get("https://www.saucedemo.com/");
        //driver.manage().window().maximize(); // MAXIMIZA LA PAGINA

        // Esperar a que el campo de usuario sea visible
        esperarElementoVisible(By.id("user-name"));

        // Se ingresan credenciales
        driver.findElement(By.id("user-name")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();

    }
    public void CerrarCeccion (){
        driver.findElement(By.id("react-burger-menu-btn")).click();
        esperarElementoVisible(By.id("logout_sidebar_link"));
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

}
