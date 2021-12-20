# Segundo_Reto
# Reto_Cucumber
Lo primero es crear un proyecto en Intellij para realizar la automatización de buscar un producto, se selecciona Gradle y se deja todo igual y se le da siguiente.

![image](https://user-images.githubusercontent.com/86448944/145666236-87e86a94-d8e7-47a6-9608-d9be274f2cd3.png)

Una vez cread el proyecto, en er archivo build.gradle se egragan las siguientes dependencias, que son para manejar el serenity y el excel.

    implementation 'net.serenity-bdd:serenity-junit:2.0.80'
    implementation 'net.serenity-bdd:serenity-cucumber:1.9.45'
    implementation 'net.serenity-bdd:serenity-core:2.0.80'
    implementation 'org.slf4j:slf4j-simple:1.7.7'
    implementation group: 'org.apache.poi', name: 'poi', version: '3.17'
    implementation group: 'org.apache.poi', name: 'poi-ooxml', version: '3.17'

![image](https://user-images.githubusercontent.com/86448944/145666389-ed0c783a-99b2-4ea7-a410-3bac987a07cf.png)

En la parte superior derecha sale un elefante, se le da clic para que carguen las dependencias

![image](https://user-images.githubusercontent.com/86448944/145666518-b1fb0ef0-5d5e-473e-8279-1c952006e384.png)

Una vez que haya cargado se puede ver la estructura de las carpetas asi:
![image](https://user-images.githubusercontent.com/86448944/145666536-904a9882-6e96-4bb2-a18d-477b7a4e224d.png)

Se empiezan a crear los paquetes (carpetas) con la estructura POM, dentro del paquete src, esta main y en el se encuentra java, ahí se crean los paquetes drivers, pages y steps.

![image](https://user-images.githubusercontent.com/86448944/145667245-0ca0aac0-2811-4631-93b5-e7cd0163f222.png)

Dentro del paquete test, en java,se crean los paquetes runners y stepsDefinition. Y en el paquete resource se crea el paquete feature

![image](https://user-images.githubusercontent.com/86448944/145667304-663898dc-29d4-4c57-a979-af043de4e8e5.png)

Lo primero que se va a crear es el excel con los productos que se desean buscar y se guarda en la raiz de la carpeta del proyecto

![image](https://user-images.githubusercontent.com/86448944/145667358-a2afcd9d-a0c0-456c-8f94-14c974b469ab.png)

En el paquete drivers se crea un archivo java con el driver de Google Chrome y se prepara para la automatización
       
	package drivers;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.chrome.ChromeOptions;

    public class GoogleChromeDriver {

    public static WebDriver driver;

    public static void chromeWebDriver(String url) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); //hace que se inicie la venta maximizada
        options.addArguments("--ignore-certificate-errors"); //Ignora los certificados 
        options.addArguments("--disable-infobars"); //deshabilita las barras informativas
        driver = new ChromeDriver(options);
        driver.get(url); //obtiene la url de la pagina que se va a automatizar
      }

    }

En el paquete pages se realiza un archivo java por cada pantalla a la que se le realizan acciones, aquí se pone una variable (con sus getter y setter) por cada acción realizada y se le asigna el Xpath con la etiqueda del campo, botón o elemento con el que se va a interactuar.

En este caso se usara un page para la busqueda de un elemento y para el cambio de departamento.

Page de busqueda del elemento 

    package pages;

    import org.openqa.selenium.By;

    public class HomeCenterPage {

    By txtBuscador = By.xpath("//input[@class='DesktopSearchBar-module_searchbox-input__HXYgR']"); //xpath del buscador
    By btnBuscador = By.xpath("//div[@class='DesktopSearchBar-module_search-bar__1PiDn']//*[local-name()='svg']"); //xpath del botón para buscar
    By btnElementoBusqueda; //xpath del elemento buscado
    By txtElementoBusqueda; xpath del tecto a comparar

    public By getTxtBuscador() {
        return txtBuscador;
    }

    public By getBtnBuscador() {
        return btnBuscador;
    }

    public By getBtnElementoBusqueda() {
        return btnElementoBusqueda;
    }

    public By getTxtElementoBusqueda() {
        return txtElementoBusqueda;
    }

    public void setBtnElementoBusqueda(String producto) {
        this.btnElementoBusqueda = By.xpath("//a[@id='title-pdp-link']/h2[contains(text(),'" + producto + "')]");//xpath del link del producto
    }

    public void setTxtElementoBusqueda(String producto) {
        this.txtElementoBusqueda = By.xpath("//h1[contains(text(),'" + producto + "')]"); //xpath del titulo del producto para compararlo
    }
    }
		
Page del cambio de departamento, donde se mapean los xpath y se les asignan variables:

    package pages;

import org.openqa.selenium.By;

    public class HomeCenterCiudadPage {
    By btnUbicacion = By.xpath("//div[@class='location h-100 d-flex align-items-center']"); //xpath del titulo del boton para cambiar la ubicación
    By btnDepartamento = By.xpath("//button[@class='SearchDropdown-module_dropdown-front__1imM7']"); //xpath del botón para seleccionar los departamentos
    By txtBuscadorDepartamento = By.xpath("//input[@id='input-drowdown']"); //xpath del input para buscar los departamentos
    By btnElementoBusqueda; 
    By txtElementoBusqueda ;

    By btnCambiar =By.xpath("//button[@data-testid='save-button']"); //xpath del botón para guardar los cambios
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


En el paquete steps se van a poner los metodos por lo general por cada page hay un step. 

En este caso se hacen dos steps uno para el page de buscar producto y otro para cambiar ubicacion.

Step de buscar producto 


    package steps;

    import drivers.GoogleChromeDriver;
    import org.junit.Assert;
    import pages.HomeCenterPage;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.Map;


    public class HomeCenterSteps {
    HomeCenterPage homeCenterPage = new HomeCenterPage();
    public ArrayList<Map<String, String>> listaProductos;


    public void abrirPagina() {
        GoogleChromeDriver.chromeWebDriver("https://www.homecenter.com.co/");  //Se abre la página en el navegador
      
    }


    public void buscarElementoEnHomeCenter(String producto) {
        GoogleChromeDriver.driver.findElement(homeCenterPage.getTxtBuscador()).sendKeys(producto); //se busca el input y se le envia el producto 
        GoogleChromeDriver.driver.findElement(homeCenterPage.getBtnBuscador()).click(); //busca el botón buscar y le da click
        homeCenterPage.setBtnElementoBusqueda(producto); 
        GoogleChromeDriver.driver.findElement(homeCenterPage.getBtnElementoBusqueda()).click();// busca el producto que entre todos y lo selecciona

    }


    public void validarElementoEnPantalla(String producto) {
        homeCenterPage.setTxtElementoBusqueda(producto);
        Assert.assertEquals(producto, GoogleChromeDriver.driver.findElement(homeCenterPage.getTxtElementoBusqueda()).getText());//compara el nombre del producto enviado con el que se busco
        GoogleChromeDriver.driver.quit();//cierra el navegador
    }
    }

Step de cambiar ubicacion

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
		//metodo para buscar el departamento
    public void BuscadorDepartamentoHomeCenter(String departamento){
        GoogleChromeDriver.driver.findElement(homeCenterCiudadPage.getBtnUbicacion()).click();
        GoogleChromeDriver.driver.findElement(homeCenterCiudadPage.getBtnDepartamento()).click();
        GoogleChromeDriver.driver.findElement(homeCenterCiudadPage.getTxtBuscadorDepartamento()).sendKeys(departamento);
        homeCenterCiudadPage.setBtnElementoBusqueda(departamento);
        GoogleChromeDriver.driver.findElement(homeCenterCiudadPage.getBtnElementoBusqueda()).click();

    }
		
		//metodo para validar el departamento
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


En el paquete test, en runners, estan los archivos para ejecutar el proyecto cada uno debe tener su stepsdefinitions y a su vez cada uno responde a un escenario

Runner para buscar producto

     package runners;

    import cucumber.api.CucumberOptions;
    import cucumber.api.SnippetType;
    import net.serenitybdd.cucumber.CucumberWithSerenity;
     import org.junit.runner.RunWith;

    @RunWith(CucumberWithSerenity.class)
     @CucumberOptions(
        features = "src\\test\\resources\\features\\HomeCenterBuscador.feature", //se especifica la ubicación del stepsdefinitions
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
        )

     public class HomeCenterBuscadorRunner {

     }
		 
Runner para cambiar departamento usando Background 

     package runners;
    import cucumber.api.CucumberOptions;
    import cucumber.api.SnippetType;
    import net.serenitybdd.cucumber.CucumberWithSerenity;
    import org.junit.runner.RunWith;

    @RunWith(CucumberWithSerenity.class)
     @CucumberOptions(
        features = "src\\test\\resources\\features\\HomeCenterCiudad.feature", //se especifica la ubicación del stepsdefinitions
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
     )  
    public class HomeCenterCiudadRunner {
     }
		 Runner para cambiar ubicacion 
		 
Runner para cambiar departamento usando scenarios sin examples

     package runners;
     import cucumber.api.CucumberOptions;
     import cucumber.api.SnippetType;
     import net.serenitybdd.cucumber.CucumberWithSerenity;
    import org.junit.runner.RunWith;

     @RunWith(CucumberWithSerenity.class)
    @CucumberOptions(
        features = "src\\test\\resources\\features\\HomeCenterLocacion.feature",
        glue = "stepsDefinitions",
        snippets = SnippetType.CAMELCASE
     )
     public class HomeCenterLocacionRunner {
     }

		 
en la carpeta StepsDefinitions  se ponen los escenarios y en cada parte se ponen los metodos que se van a realizar

stepsDefinitions para buscar producto
     
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

stepsDefinitions para cambiar departamento usando Background 
    
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
	 
stepsDefinitions para cambiar departamento usando scenarios sin examples

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

En el paquete features se definen los escenarios y lo que se desea realizar de la prueba 

Escenario para buscar producto

  
      Feature: HU-001 Buscador Home Center
     Yo como usuario de HC
     Quiero buscar un producto en la plataforma
     Para ver el nombre del producto en pantalla

     Scenario Outline: Buscar producto
    Given que me encuentro en la pagina de ML
    When busque el producto <NombreProducto>
    Then podre ver <NombreProducto> en pantalla
    Examples:
      |NombreProducto|
      |Silla PC Escritorio Heavy Duty Piscis|
    |Silla MyWay Polipropileno Filtro UV Blanco|
    |Silla Ejecutiva con Brazos Negra y Azul   |
    |Silla Ejecutiva Cromada con Brazos Negra  |
    |Silla PC Escritorio Senna                 |

Escenario para cambiar departamento usando Background

    Feature: HU-002 seleccionar departamento
    Yo como usuario de HC
    Quiero seleccionar el departamento en la plataforma
    Para ver el departamento en el que me ubico

    Background:
    Given que me encuentro en la pagina de HC
    Scenario Outline: selecciono el departamento
    When seleccione el departamento <departamento>
    Then vere <departamento>
    Examples:
      | departamento |
      | ANTIOQUIA    |

    Scenario Outline: selecciono el departamento
    When seleccione el departamento <departamento>
    Then vere <departamento>
    Examples:
      | departamento |
      | CUNDINAMARCA    |
		
Escenario para cambiar departamento con scenario sin example
     
		 Feature: HU-003 seleccionar departamento
     Yo como usuario de HC
     Quiero seleccionar departamento en la plataforma
    Para ver el epartamento en el que me ubico


    Scenario:selecciono el departamento
    Given que me encuentro en la pagina de ML
    When selecciono HUILA
    Then podre ver HUILA


    Scenario: selecciono el departamento
      Given que me encuentro en la pagina de HC
      When selecciono UHILLA
      Then podre ver UHILLA






