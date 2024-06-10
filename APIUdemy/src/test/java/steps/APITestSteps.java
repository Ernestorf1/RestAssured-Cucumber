// Define el paquete donde se encuentra esta clase
package steps;

// Importa las clases de Cucumber para definir los pasos
import io.cucumber.java.en.*;

// Importa las clases de RestAssured para manejar solicitudes y respuestas HTTP
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

// Importa métodos estáticos de RestAssured para simplificar las solicitudes HTTP
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.util.List;

// Define una clase para los pasos de la prueba API
public class APITestSteps {
    // Define una variable estática para la especificación de la solicitud
    private static RequestSpecification request;
    // Define una variable para almacenar la respuesta HTTP
    private Response response;
    // Define una variable para almacenar la respuesta validable
    private ValidatableResponse json;

    // Define un paso dado en Cucumber que indica que el usuario envía una solicitud
    // GET al endpoint
    // (.+) STring
    @Given("^user sends a get request to the (.+) URI$")
    public void user_sends_a_get_request_to_the_https_jsonplaceholder_typicode_com_uri(String URI) {
        // Inicializa la solicitud con la URI base y el tipo de contenido JSON
        request = given()
                .baseUri(URI)
                .contentType(ContentType.JSON);
    }

    // Define un paso entonces en Cucumber que verifica que el usuario recibe un
    // código de estado específico
    // La expresión regular (\\d+) captura un valor numérico y lo pasa como
    // argumento al método
    @Then("^user gets a (\\d+) status code$")
    // Define el método que acepta el código de estado esperado como un parámetro
    // entero
    public void User_gets_a_status_code(int expectedStatusCode) {
        // Envía una solicitud GET al endpoint específico y almacena la respuesta
        response = request
                .when()
                .get("/users/TheFreeRangeTester/repos");

        // Valida que la respuesta tenga el código de estado esperado
        json = response.then().statusCode(expectedStatusCode);
    }

    @Then("^user validate there are (\\d+) items on the (.+) endpoint$")
    public void validateSize(int expectedSize, String endpoint) {
        response = request
                .when()
                .get(endpoint);

        List<String> jsonResponse = response.jsonPath().getList("$");
        int actualSize = jsonResponse.size();
        assertEquals(expectedSize, actualSize);
    }

    @Then("^user validate there is a value: (.+) in the response at (.+) endpoint$")
    public void validateValue(String expectedValue, String endpoint) {
        response = request
                .when()
                .get(endpoint);

        List<String> jsonResponse = response.jsonPath().getList("username");
        //To validate if the expected result is into the list
        assertTrue(jsonResponse.contains(expectedValue));
        //To validate expected result is equal to actual result
        //String actualValue = jsonResponse.get(0);
        //assertEquals(expectedValue, actualValue);
    }
    @Then("^user validate the nested value: (.+) in the response at (.+) endpoint$")
    public void validateNestedValue(String expectedStreet, String endpoint) {
        response = request
                .when()
                .get(endpoint);

        String jsonResponse = response.jsonPath().getString("address.street");
        //To validate if the expected result is into the list
        assertTrue("La calle " + expectedStreet+ " no pertenece a ningun ususario", jsonResponse.contains(expectedStreet));
        System.out.println(expectedStreet);
    }
}