package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.InventoryPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void anadirUnProductoAlCarrito() throws InterruptedException {
        // inicia sesión correctamente
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(1000);

        // añade un producto al carrito
        inventoryPage.anadirProducto("sauce-labs-backpack");
        Thread.sleep(1000);

        // comprueba que el contador del carrito muestra 1
        assertEquals(1, inventoryPage.getNumeroProductosCarrito(), "El carrito debería tener 1 producto");
    }

    @Test
    public void anadirDosProductosAlCarrito() throws InterruptedException {
        // inicia sesión correctamente
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(1000);

        // añade dos productos al carrito
        inventoryPage.anadirProducto("sauce-labs-backpack");
        inventoryPage.anadirProducto("sauce-labs-bike-light");
        Thread.sleep(1000);

        // comprueba que el contador del carrito muestra 2
        assertEquals(2, inventoryPage.getNumeroProductosCarrito(), "El carrito debería tener 2 productos");
    }

    @Test
    public void botonCambiaTrasAnadirProducto() throws InterruptedException {
        // inicia sesión correctamente
        loginPage.login("standard_user", "secret_sauce");
        Thread.sleep(1000);
        
        String producto = "sauce-labs-backpack";
        
        // comprobamos que inicialmente el botón dice Add to cart
        assertEquals("Add to cart", inventoryPage.getTextoBotonProducto(producto));

        // añade un producto al carrito
        inventoryPage.anadirProducto(producto);
        Thread.sleep(1000);

        // comprueba que el botón cambia de Add to cart a Remove
        assertTrue(inventoryPage.botonRemoveAparece(producto), "El botón de Remove no aparece");
        assertEquals("Remove", inventoryPage.getTextoBotonProducto(producto), "El texto del botón no cambió a Remove");
    }
}
