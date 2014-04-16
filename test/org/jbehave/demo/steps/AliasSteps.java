package org.jbehave.demo.steps;

import org.jbehave.core.annotations.*;

import static java.lang.Integer.parseInt;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AliasSteps {

    private Integer actualTotal;
    @Given("my balance is $initialValue")
    @Alias("$initialValue in my wallet")
    public void initialAmount(String initialValue){
        this.actualTotal = dollarAmountFromString(initialValue);
    }

    @When("I withdraw $amount")
    @Alias("I spend $amount")
    public void spend(String amount){
        actualTotal -= dollarAmountFromString(amount);
    }

    @Then("my balance is $expectedTotal")
    @Aliases(values={"I have $expectedTotal left", "my wallet contains $expectedTotal"})
    public void verifyTotal(String expectedTotalString){
        Integer expectedTotal = dollarAmountFromString(expectedTotalString);
        assertThat(actualTotal, is(expectedTotal));
    }

    private Integer dollarAmountFromString(String amountString) {
        return parseInt(amountString.replaceAll("\\$", ""));
    }

}
