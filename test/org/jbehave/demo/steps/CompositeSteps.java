package org.jbehave.demo.steps;

import org.jbehave.core.annotations.Composite;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;

public class CompositeSteps {

    @Given("I have withdrawn $withdrawalAmount from my initial balance of $initialAmount")
    @Composite(
            steps = {
                    "Given my balance is <initialAmount>",
                    "When I withdraw <withdrawalAmount>"
            })
    public void createInitialBalanceAndWithdrawMoney(@Named("withdrawalAmount") String withdrawalAmount, @Named("initialAmount") String initialBalance){
    }


}
