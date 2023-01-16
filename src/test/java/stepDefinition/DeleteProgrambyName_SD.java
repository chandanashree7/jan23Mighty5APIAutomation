package stepDefinition;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteProgrambyName_SD {

	private String contextPath;
	private Response response;

	@Given("The service with URL and delete path")
	public void the_service_with_url_and_path() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
		contextPath = "/deletebyprogname";
	}
	
	@When("Delete request by {string} is made")
	public void delete_request_by_is_made(String programName) {
		response = RestAssured.given().delete(contextPath + "/" + programName);
	}

	@Then("Validate the status code is 200")
	public void validate_the_status_code() {
		Assert.assertEquals(200, response.getStatusCode());
		response.getBody().prettyPrint();
	}
}
