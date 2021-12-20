package pages;

import org.openqa.selenium.By;

public class HomeCenterCiudadPage {
    By btnUbicacion = By.xpath("//div[@class='location h-100 d-flex align-items-center']");
    By btnDepartamento = By.xpath("//button[@class='SearchDropdown-module_dropdown-front__1imM7']");
    By txtBuscadorDepartamento = By.xpath("//input[@id='input-drowdown']");
    By btnElementoBusqueda;
    By txtElementoBusqueda ;

    By btnCambiar =By.xpath("//button[@data-testid='save-button']");

    public By getBtnCambiar() {
        return btnCambiar;
    }

    public By getBtnUbicacion() {
        return btnUbicacion;
    }

    public By getBtnDepartamento() {
        return btnDepartamento;
    }

    public By getTxtBuscadorDepartamento() {
        return txtBuscadorDepartamento;
    }

    public By getBtnElementoBusqueda() {
        return btnElementoBusqueda;
    }

    public By getTxtElementoBusqueda() {
        return txtElementoBusqueda;
    }


    public void setBtnElementoBusqueda(String departamento) {
        this.btnElementoBusqueda = By.xpath("//button[contains(text(),'"+ departamento +"')]");
    }

    public void setTxtElementoBusqueda(String departamento) {
        this.txtElementoBusqueda = By.xpath("//span[contains(text(),'"+ departamento +"')]");
    }
}
