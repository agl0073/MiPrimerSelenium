package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    
    private WebDriver driver;
    
    private By userField = By.id("user-name");
    private By passField = By.id("password");
    private By loginBtn = By.id("login-button");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void ingresarUsuario(String user) {
        // escribe el usuario en el campo correspondiente
        driver.findElement(userField).sendKeys(user);
    }
    
    public void ingresarPassword(String pass) {
        // escribe la contraseña en el campo correspondiente
        driver.findElement(passField).sendKeys(pass);
    }
    
    public void clickLogin() {
        // pulsa el botón de login
        driver.findElement(loginBtn).click();
    }

    // Parte 5: Mejora el diseño
    // Crea un método que realice todo el proceso de login
    public void login(String user, String pass) {
        ingresarUsuario(user);
        ingresarPassword(pass);
        clickLogin();
    }
}
