package uk.co.pbellchambers.skeleton.run;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {  "pretty",
                "html:build/cucumber-reports/html",
                "usage:build/cucumber-reports/usage.json",
                "rerun:build/cucumber-reports/rerun.json",
                "json:build/cucumber-reports/json.json",
                "junit:build/cucumber-reports/junit.junit"},
        features = "src/test/resources/features/",
        glue = "uk.co.pbellchambers.skeleton.stepdefs")
public class RunCukesTest {

}