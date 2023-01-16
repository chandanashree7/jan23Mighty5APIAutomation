package stepDefinition;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SavePrograms_SD {

	private String path;
	RequestSpecification request;
	Map<String, Object> requestParams;
	Response response;

	@Given("A Save Service with URL and path")
	public void a_service_with_url_at_path() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
		path = "/saveprogram";
	}

	@When("{string},{string},{string},creationTime,lastModTime are modified")
	public void sdet_creation_time_last_mod_time_are_modified(String programName, String programDesc,
			String programStatus) {
		ZonedDateTime dateTime = ZonedDateTime.now();
		String time = dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		requestParams = new HashMap<>();
		requestParams.put("programName", programName);
		requestParams.put("programStatus", programStatus);
		requestParams.put("programDescription", programDesc);
		requestParams.put("creationTime", time);
		requestParams.put("lastModTime", time);
	}

	@When("POST request is made")
	public void post_request_is_made() {
		RequestSpecification requestSpec = new RequestSpecBuilder().addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json").build();
		request = RestAssured.given().spec(requestSpec).body(requestParams);
	}

	@Then("Save programId")
	public void save_program_id() {
		response = request.post(path);
	}

	@Then("Validate status code is 201")
	public void validate_status_code() {
		Assert.assertEquals(201, response.getStatusCode());
	}

	@Then("Validate required fields {string},{string},{string}")
	public void validate_sdet_api_testing_active(String programName, String programDescription, String programStatus) {
		JsonPath jsonPathEvaluator = response.jsonPath();
		String responseName = jsonPathEvaluator.get("programName");
		String responseDesc = jsonPathEvaluator.get("programDescription");
		String responseStatus = jsonPathEvaluator.get("programStatus");
		
		Assert.assertEquals(responseName, programName);
		Assert.assertEquals(responseDesc, programDescription);
		Assert.assertEquals(responseStatus, programStatus);
		
		response.getBody().prettyPrint();
	}
}