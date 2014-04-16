package org.jbehave.demo.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TableSteps {
    private final List<Integer> actualResults;
    private Integer startingApples=0;
    private Integer moreApples=0;

    public TableSteps() {
        this.actualResults = new ArrayList<Integer>();
    }

    @When("I add these numbers:$numbers")
    public void addNumbers(ExamplesTable numbers){
        for (Map<String, String> row : numbers.getRows()) {
            final int x = Integer.parseInt(row.get("x"));
            final int y = Integer.parseInt(row.get("y"));
            actualResults.add(x + y);
        }
    }

    @Then("I get these numbers:$numbers")
    public void verifyResultsOfAddition(ExamplesTable numbers){
        List<Integer> expectedResults = new ArrayList<Integer>();
        for (Map<String, String> row : numbers.getRows()) {
            expectedResults.add(Integer.parseInt(row.get("result")));
        }
        assertThat(actualResults, is(expectedResults));
    }

    @Given("<startingApples> apples")
    public void startingApples(@Named("startingApples") Integer startingApples){
        this.startingApples = startingApples;
    }

    @When("I buy <moreApples> more apples")
    public void buyApples(@Named("moreApples") Integer moreApples){
        this.moreApples = moreApples;
    }

    @Then("I have <totalApples> apples")
    public void verifyAppleTotal(@Named("totalApples") Integer totalApples){
        assertThat(startingApples + moreApples, is(totalApples));
    }
}
