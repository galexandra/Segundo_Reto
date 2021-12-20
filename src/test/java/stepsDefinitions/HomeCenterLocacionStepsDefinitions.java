package stepsDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import steps.HomeCenterCiudadSteps;

public class HomeCenterLocacionStepsDefinitions {
    HomeCenterCiudadSteps homeCenterCiudadSteps = new HomeCenterCiudadSteps();

    @Given("^que me encuentro en la pagina de HC$")
    public void que_me_encuentro_en_la_pagina_de_HC() {
    }
    @When("^selecciono (.*)$")
    public void selecciono(String locacion) {
        homeCenterCiudadSteps.BuscadorDepartamentoHomeCenter(locacion);
    }

    @Then("^podre ver (.*)$")
    public void podreVer(String locacion) {
        homeCenterCiudadSteps.validarLocacionEnPantalla(locacion);
    }
}
