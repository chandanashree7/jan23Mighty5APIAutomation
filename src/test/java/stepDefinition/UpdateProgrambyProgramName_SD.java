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

public class UpdateProgrambyProgramName_SD {
	
	private String contextPath;
	private RequestSpecification request;
	private Map<String, Object> requestParams;
	private Response response;

	@Given("A service with URL and update by programName path")
	public void a_service_with_url_and_update_by_program_name_path() {
		RestAssured.baseURI = "https://lms-backend-service.herokuapp.com/lms";
		contextPath = "/program";
	}

	@When("{string},{string},{string},creationTime,lastModTime are modifed for programName")
	public void creation_time_last_mod_time_are_modifed_for_program_name(String programName,String programDescription, String programStatus) {
		ZonedDateTime dateTime = ZonedDateTime.now();
		String time = dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		requestParams = new HashMap<>();
		requestParams.put("programName", programName);
		requestParams.put("programStatus", programStatus);
		requestParams.put("programDescription", programDescription);
		requestParams.put("creationTime", time);
		requestParams.put("lastModTime", time);

	}

	@When("PUT request is made for programName")
	public void put_request_is_made_for_program_name() {
		RequestSpecification requestSpec = new RequestSpecBuilder().addHeader("Content-Type", "application/json")
				.addHeader("Accept", "application/json").build();
		request = RestAssured.given().spec(requestSpec).body(requestParams);
	}

	@Then("Save program by programName {string}")
	public void save_program_by_program_name(String programName) {
		response = request.put(contextPath + "/" + programName);
	}

	@Then("Validate PUT response status code is {int} for programName")
	public void validate_put_response_status_code_is_for_program_name(Integer int1) {
		Assert.assertEquals(200, response.getStatusCode());
	}

	@Then("Validate required fields {int},{string},{string},{string} for programName")
	public void validate_required_fields_for_program_name(Integer programId,String programName, String programDescription, String programStatus) {
		JsonPath jsonPathEvaluator = response.jsonPath();
		Integer responseId = jsonPathEvaluator.get("programId");
		String responseName = jsonPathEvaluator.get("programName");
		String responseDesc = jsonPathEvaluator.get("programDescription");
		String responseStatus = jsonPathEvaluator.get("programStatus");
		Assert.assertEquals(responseId, programId);
		Assert.assertEquals(responseName, programName);
		Assert.assertEquals(responseDesc, programDescription);
		Assert.assertEquals(responseStatus, programStatus);

		response.getBody().prettyPrint();
	}

}
