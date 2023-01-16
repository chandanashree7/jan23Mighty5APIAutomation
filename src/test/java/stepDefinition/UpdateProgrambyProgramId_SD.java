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

public class UpdateProgrambyProgramId_SD {

	private String contextPath;
	private RequestSpecification request;
	private Map<String, Object> requestParams;
	private Response response;

	@Given("The service with URL and update path")
	public void the_service_with_url_and_path() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
		contextPath = "/putprogram";
	}

	@When("{string},{string},{string},creationTime,lastModTime are updated")
	public void creation_time_last_mod_time_are_updated(String programName, String programDescription,
			String programStatus) {
		ZonedDateTime dateTime = ZonedDateTime.now();
		String time = dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		requestParams = new HashMap<>();
		requestParams.put("programName", programName);
		requestParams.put("programStatus", programStatus);
		requestParams.put("programDescription", programDescription);
		requestParams.put("creationTime", time);
		requestParams.put("lastModTime", time);

	}

	@When("PUT request is made")
	public void put_request_is_made() {
		RequestSpecification requestSpec = new RequestSpecBuilder().addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json").build();
		request = RestAssured.given().spec(requestSpec).body(requestParams);
	}

	@Then("Save program by {int}")
	public void save_program_by(Integer programId) {
		response = request.put(contextPath + "/" + programId);
	}

	@Then("Validate Put status code is 200")
	public void validate_status_code() {
		Assert.assertEquals(200, response.getStatusCode());
	}

	@Then("Validate required fields {string},{string},{string},{string}")
	public void validate_required_fields(String programId, String programName, String programDescription,
			String programStatus) {
		JsonPath jsonPathEvaluator = response.jsonPath();
		Integer responseId = jsonPathEvaluator.get("programId");
		String responseName = jsonPathEvaluator.get("programName");
		String responseDesc = jsonPathEvaluator.get("programDescription");
		String responseStatus = jsonPathEvaluator.get("programStatus");

		Assert.assertEquals(responseName, programName);
		Assert.assertEquals(responseDesc, programDescription);
		Assert.assertEquals(responseStatus, programStatus);

		response.getBody().prettyPrint();
	}
}