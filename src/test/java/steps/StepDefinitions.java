package steps;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import common.RestUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

public class StepDefinitions {

    public  String apiLatestURL;
    public String apiDateURL ="https://api.ratesapi.io/api/{DATE}";
    public Response response;
    SoftAssert softAssert;

    @Given ("Rates API for Latest Foreign Exchange rates")
    public void latestAPI(){
        softAssert = new SoftAssert();
        apiLatestURL ="https://api.ratesapi.io/api/latest";
    }

    @When("The API is available")
    public void checkAPIAlive() {
        response = RestUtil.get(apiLatestURL);
    }

    @Then("Validate response status code {string}")
    public void validateReponseCode(String statusCode){
        softAssert.assertEquals(Integer.parseInt(statusCode),response.getStatusCode(),"Validate status code");
        softAssert.assertAll();
    }
    @Then("Validate latest response")
    public void validate_latest_response() {

        JsonObject jsonObject = new JsonParser().parse(response.getBody().asString()).getAsJsonObject();
        softAssert.assertEquals("EUR",jsonObject.get("base"),"Validate base currency");
        softAssert.assertEquals("2020-07-28",jsonObject.get("date"),"Validate exchange rate date");
        softAssert.assertEquals(jsonObject.get("rates").getAsJsonObject().size(),13,"Validate currency count");
    }

}
