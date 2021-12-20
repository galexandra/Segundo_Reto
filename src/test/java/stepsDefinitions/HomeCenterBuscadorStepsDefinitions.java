package stepsDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import steps.HomeCenterSteps;

public class HomeCenterBuscadorStepsDefinitions {
    HomeCenterSteps homeCenterSteps = new HomeCenterSteps();

    @Given("^que me encuentro en la pagina de ML$")
    public void queMeEncuentroEnLaPaginaDeML() {
        homeCenterSteps.abrirPagina();

    }

    @When("^busque el producto (.*)$")
    public void busqueElProductoSillaPCEscritorioHeavyDutyPiscis(String producto) {
        homeCenterSteps.buscarElementoEnHomeCenter(producto);


    }

    @Then("^podre ver (.*) en pantalla$")
    public void podreVerSillaPCEscritorioHeavyDutyPiscisEnPantalla(String producto) {
        homeCenterSteps.validarElementoEnPantalla(producto);

    }
}
