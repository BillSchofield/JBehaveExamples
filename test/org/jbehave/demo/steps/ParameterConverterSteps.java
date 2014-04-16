package org.jbehave.demo.steps;

import org.jbehave.core.annotations.When;

import java.util.List;

public class ParameterConverterSteps {
    private AliasSteps aliasSteps;

    public ParameterConverterSteps(AliasSteps aliasSteps) {
        this.aliasSteps = aliasSteps;
    }

    @When("I make purchases in the amounts: $purchaseAmounts")
    public void multipleDeductions(List<String> purchaseAmounts){
        for (String purchaseAmount : purchaseAmounts) {
            aliasSteps.spend(purchaseAmount);
        }
    }
}
