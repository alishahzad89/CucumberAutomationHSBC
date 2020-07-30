package steps;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.RestUtil;
import common.Utility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import java.text.ParseException;

public class StepDefinitions {

    public String apiBaseURL = "https://api.ratesapi.io/api/";
    public String dateFormatError = "time data '{DATE}' does not match format '%Y-%m-%d'";
    public String apiUrl;
    public Response response;
    public String expectedDate;
    public String baseCurrency ="EUR";
    SoftAssert softAssert;

    @Before
    public void beforeScenario() {
        softAssert = new SoftAssert();
    }

    @Given("Foreign Exchange rates for date {string}")
    public void latestAPI(String date) throws ParseException {
        apiUrl = apiBaseURL + date;
        expectedDate = date;
        if (date.equals("latest")) {
            expectedDate = Utility.getFormattedDate();
        }else if (Utility.isValidDate(date)) {
            if (Utility.isFutureDate(date))
                expectedDate = Utility.getFormattedDate();
        }
    }

    @When("The API is available")
    public void checkAPIAlive() {
        response = RestUtil.get(apiUrl);
    }

    @Then("Validate rate api response status code {string}")
    public void validateReponseCode(String statusCode){
        softAssert.assertEquals(Integer.parseInt(statusCode),response.getStatusCode(),"Validate status code");
    }
    @Then("Validate rate api response")
    public void validate_latest_response() {
        JsonObject jsonObject = new JsonParser().parse(response.getBody().asString()).getAsJsonObject();
        softAssert.assertEquals(jsonObject.get("base").getAsString(),baseCurrency,"Validate base currency");
        softAssert.assertEquals(jsonObject.get("date").getAsString(),expectedDate,"Validate exchange rate date");
        softAssert.assertEquals(32,jsonObject.get("rates").getAsJsonObject().size(),"Validate currency count");
    }

    @Then("Validate error message {string}")
    public void validate_error_message(String errorType) {
       String expectedError = "";
        if (errorType.equals("dateFromatError")){
            expectedError = dateFormatError.replace("{DATE}",expectedDate);
            JsonObject jsonObject = new JsonParser().parse(response.getBody().asString()).getAsJsonObject();
            softAssert.assertEquals(jsonObject.get("error").getAsString(),expectedError,"Validate api error message");

        }else{
            softAssert.assertEquals(response.getStatusCode(),404,"Validate status code");
        }
    }

    @Given("Foreign Exchange rates for date {string} with base {string}")
    public void foreign_exchange_rates_for_date_with_base(String date, String base) throws ParseException {
        latestAPI(date);
        //add base currency
        baseCurrency = base;
        apiUrl = apiBaseURL + date +"?base=" +base;

    }

    @After
    public void afterScenario(){
        softAssert.assertAll();
    }
}
