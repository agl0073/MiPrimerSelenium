package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        // Selenium 4 maneja automáticamente el driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void loginCorrecto() throws InterruptedException {
        // Muestra el proceso paso a paso con pausas
        loginPage.ingresarUsuario("standard_user");
        Thread.sleep(2000); // Parte 4: pausas
        
        loginPage.ingresarPassword("secret_sauce");
        Thread.sleep(2000);
        
        loginPage.clickLogin();
        Thread.sleep(2000);

        // Comprueba que la URL contiene la palabra "inventory"
        assertTrue(driver.getCurrentUrl().contains("inventory"), "El login no fue exitoso");
    }

    @Test
    void loginIncorrecto() throws InterruptedException {
        // Parte 5: Usamos el método unificado (Mejora de diseño) para simplificar
        // Introducimos una contraseña incorrecta
        loginPage.login("standard_user", "password_falsa");
        Thread.sleep(2000); // Parte 4: pausas
        
        // Comprobar que aparece un mensaje de error en la página
        WebElement errorMsg = driver.findElement(By.cssSelector("h3[data-test='error']"));
        assertTrue(errorMsg.isDisplayed(), "No se muestra el mensaje de error de credenciales");
    }
}
