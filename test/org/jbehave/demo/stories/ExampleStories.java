package org.jbehave.demo.stories;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.IdeOnlyConsoleOutput;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.demo.pages.LoginPage;
import org.jbehave.demo.steps.*;
import org.jbehave.demo.pages.LandingPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class ExampleStories extends JUnitStory {

    @Override
    public Configuration configuration() {
        try {
            URL test = new File("test").toURI().toURL();
            LoadFromRelativeFile storyLoader = new LoadFromRelativeFile(test);
            return new MostUsefulConfiguration()
                    .useStoryLoader(storyLoader)
                            .usePendingStepStrategy(new FailingUponPendingStep())
                            .useStoryReporterBuilder(
                                    new StoryReporterBuilder()
                                            .withReporters(new IdeOnlyConsoleOutput())
                                            .withFormats(Format.IDE_CONSOLE)
                                            .withFailureTrace(true).withFailureTraceCompression(true)
                                            .withDefaultFormats());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        System.setProperty("webdriver.chrome.driver", "/Users/ThoughtWorker/Tools/chromedriver");
        WebDriver driver = new ChromeDriver();

        LandingPage landingPage = new LandingPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        final AliasSteps aliasSteps = new AliasSteps();
        return new InstanceStepsFactory(configuration(),
                new BeforeAndAfterSteps(driver, landingPage),
                new TableSteps(),
                aliasSteps,
                new ParameterConverterSteps(aliasSteps),
                new GithubLoginSteps(landingPage, loginPage)
        );
    }

}
