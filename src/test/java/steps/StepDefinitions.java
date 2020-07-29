package steps;

import io.cucumber.java.en.Given;
public class StepDefinitions {
    @Given ("Test cucumber runner")
    public void test(){
        System.out.println("Test cucumber runner");
    }
}
