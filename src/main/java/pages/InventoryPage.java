package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventoryPage {

    private WebDriver driver;

    // Localizador del icono del carrito (el badge que muestra la cantidad)
    private By cartBadge = By.className("shopping_cart_badge");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Añade un producto al carrito utilizando su identificador parcial de nombre.
     * Ejemplo: "sauce-labs-backpack"
     */
    public void anadirProducto(String prductNameId) {
        By addToCartBtn = By.id("add-to-cart-" + prductNameId);
        driver.findElement(addToCartBtn).click();
    }

    /**
     * Retorna el número de productos mostrados en el badge del carrito.
     * Si no hay productos, el badge desaparece del DOM, por lo que manejamos ese caso.
     */
    public int getNumeroProductosCarrito() {
        List<WebElement> badges = driver.findElements(cartBadge);
        if (badges.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(badges.get(0).getText());
    }

    /**
     * Comprueba si el botón de eliminar producto (Remove) está visible
     * y obtiene su texto. Resulta útil para saber si el botón cambió de Add to Cart a Remove.
     */
    public boolean botonRemoveAparece(String productNameId) {
        By removeBtn = By.id("remove-" + productNameId);
        return driver.findElement(removeBtn).isDisplayed();
    }

    /**
     * Recupera el texto actual del botón de acción de un producto determinado
     * Puede ser "Add to cart" o "Remove"
     */
    public String getTextoBotonProducto(String productNameId) {
        // El botón puede tener el id "add-to-cart-..." o "remove-..." dependiendo de su estado
        // Buscamos dentro de la card del producto específico
        // Otra forma sencilla es buscar directamente el elemento por class si sabemos que es el botón
        try {
            return driver.findElement(By.id("remove-" + productNameId)).getText();
        } catch (Exception e) {
            return driver.findElement(By.id("add-to-cart-" + productNameId)).getText();
        }
    }

    /**
     * Elimina un producto del carrito.
     */
    public void removerProducto(String productNameId) {
        By removeBtn = By.id("remove-" + productNameId);
        driver.findElement(removeBtn).click();
    }
}
