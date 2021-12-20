package stepsDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import steps.HomeCenterCiudadSteps;

public class HomeCenterCiudadStepsDefinitions {

    HomeCenterCiudadSteps homeCenterCiudadSteps = new HomeCenterCiudadSteps();

    @When("^seleccione el departamento (.*)$")
    public void seleccioneElDepartamentoCUNDINAMARCA(String locacion) {
        homeCenterCiudadSteps.BuscadorDepartamentoHomeCenter(locacion);
    }

    @Then("^vere (.*) en pantalla$")
    public void vereCUNDINAMARCAEnPantalla(String locacion) {
        homeCenterCiudadSteps.validarLocacionEnPantalla(locacion);
    }


}
