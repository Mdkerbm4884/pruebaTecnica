package seleniumgluecode;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.openqa.selenium.*;
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("El elemento no fue visible");
            return null;
        }
    }
    //METODO PARA CAPTURAR IMAGEN Y GUARDARLA
    public void capturar(String nombreArchivo) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destino = new File("C:/PruebasAutomatizadas/AutomatizacionMVM/" + nombreArchivo + ".png"); // Cambia la ruta
            FileHandler.copy(screenshot, destino);
            System.out.println("Captura de pantalla guardada en: " + destino.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al guardar la captura de pantalla: " + e.getMessage());
        }
    }

}
