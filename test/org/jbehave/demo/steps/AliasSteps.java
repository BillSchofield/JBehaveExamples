package org.jbehave.demo.steps;

import org.jbehave.core.annotations.*;

import static java.lang.Integer.parseInt;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AliasSteps {

    private Integer actualTotal;
    @Given("my balance is $initialAmount")
    @Alias("$initialValue in my wallet")
    public void initialAmount(@Named("initialAmount") String initialAmount){
        this.actualTotal = dollarAmountFromString(initialAmount);
    }

    @When("I withdraw $withdrawalAmount")
    @Alias("I spend $withdrawalAmount")
    public void spend(@Named("withdrawalAmount") String withdrawalAmount){
        final Integer dollarAmount = dollarAmountFromString(withdrawalAmount);
        if (actualTotal >= dollarAmount) {
            actualTotal -= dollarAmount;
        }
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
