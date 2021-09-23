package com.epam.tc;

import com.epam.tc.steps.ActionSteps;
import com.epam.tc.steps.AssertionSteps;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static com.epam.tc.tests.TrelloFunctionTests.boardDtosListToDelete;

public class TestBase {

    public SoftAssertions softAssertions;
    public ActionSteps actionSteps;
    public AssertionSteps assertionSteps;

    @BeforeSuite
    public void beforeClass() {
        softAssertions = new SoftAssertions();
    }

    @BeforeMethod
    public void beforeTest() {
        actionSteps = new ActionSteps();
        assertionSteps = new AssertionSteps();
    }

    @AfterMethod
    public void afterTest() {

    }

    @AfterClass
    public void afterClass() {
        actionSteps.deletedAllBoards(boardDtosListToDelete);
        actionSteps = null;
        assertionSteps = null;
    }
}
