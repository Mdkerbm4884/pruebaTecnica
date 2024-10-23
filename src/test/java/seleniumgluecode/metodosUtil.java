package seleniumgluecode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class metodosUtil {
    private WebDriver driver;

    // Constructor para inicializar el WebDriver
    public metodosUtil(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement esperarElementoVisible(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("El elemento no fue visible");
            return null;
        }
    }
}
