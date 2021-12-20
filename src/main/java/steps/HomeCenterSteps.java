package steps;

import drivers.GoogleChromeDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.HomeCenterPage;

public class HomeCenterSteps {

    HomeCenterPage homeCenterPage = new HomeCenterPage();

    public void abrirPagina(){
        GoogleChromeDriver.chromeWebDriver("https://www.homecenter.com.co/");
    }

    public void buscarElementoEnHomeCenter(String producto){
        GoogleChromeDriver.driver.findElement(homeCenterPage.getTxtBuscador()).sendKeys(producto);
        GoogleChromeDriver.driver.findElement(homeCenterPage.getBtnBuscador()).click();
        homeCenterPage.setBtnElementoBusqueda(producto);
        GoogleChromeDriver.driver.findElement(homeCenterPage.getBtnElementoBusqueda()).click();
    }



    public void validarElementoEnPantalla(String producto){
        homeCenterPage.setTxtElementoBusqueda(producto);
        Assert.assertEquals(producto.replace("  "," "),GoogleChromeDriver.driver.findElement(homeCenterPage.getTxtElementoBusqueda()).getText());
        GoogleChromeDriver.driver.quit();
    }

    public void escribirEnTexto(By elemento, String texto) {
        GoogleChromeDriver.driver.findElement(elemento).sendKeys(texto);
    }

    public void clicEnElemento(By elemento) {
        GoogleChromeDriver.driver.findElement(elemento).click();
    }
}
