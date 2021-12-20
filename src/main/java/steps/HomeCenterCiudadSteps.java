package steps;

import drivers.GoogleChromeDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.HomeCenterCiudadPage;

public class HomeCenterCiudadSteps {

    HomeCenterCiudadPage homeCenterCiudadPage = new HomeCenterCiudadPage();

    public void abrirPagina(){
        GoogleChromeDriver.chromeWebDriver("https://www.homecenter.com.co/");
    }

    public void BuscadorDepartamentoHomeCenter(String departamento){
        GoogleChromeDriver.driver.findElement(homeCenterCiudadPage.getBtnUbicacion()).click();
        GoogleChromeDriver.driver.findElement(homeCenterCiudadPage.getBtnDepartamento()).click();
        GoogleChromeDriver.driver.findElement(homeCenterCiudadPage.getTxtBuscadorDepartamento()).sendKeys(departamento);
        homeCenterCiudadPage.setBtnElementoBusqueda(departamento);
        GoogleChromeDriver.driver.findElement(homeCenterCiudadPage.getBtnElementoBusqueda()).click();

    }

    public void validarLocacionEnPantalla(String locacion){
        homeCenterCiudadPage.setTxtElementoBusqueda(locacion);
        Assert.assertEquals(locacion.replace("  "," "),GoogleChromeDriver.driver.findElement(homeCenterCiudadPage.getTxtElementoBusqueda()).getText());
        GoogleChromeDriver.driver.findElement(homeCenterCiudadPage.getBtnCambiar()).click();
        //GoogleChromeDriver.driver.quit();


    }

    public void escribirEnTexto(By elemento, String texto) {
        GoogleChromeDriver.driver.findElement(elemento).sendKeys(texto);
    }

    public void clicEnElemento(By elemento) {
        GoogleChromeDriver.driver.findElement(elemento).click();
    }
}
