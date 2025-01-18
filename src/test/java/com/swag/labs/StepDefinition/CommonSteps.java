package com.swag.labs.StepDefinition;

import com.swag.labs.utils.CucumberLogger;
import com.swag.labs.utils.WebDriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;


public class CommonSteps {

    WebDriverManager driverManager;

    public CommonSteps(WebDriverManager driverManager) {
        this.driverManager = driverManager;
    }

    @Before()
    public void initializeDriver(){
        CucumberLogger.logger.info("Before: init driver");
    }

    @After()
    public void closeDriver(Scenario scenario){
        CucumberLogger.logger.info("After: quit driver");
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

            LogEntries logs = driverManager.getDriver().manage().logs().get(LogType.BROWSER);
            for (LogEntry entry : logs) {
                scenario.attach(entry.getMessage(), "text/plain", "CONSOLE LOGS");
                CucumberLogger.logger.info(entry.getMessage());
                }
        }
        driverManager.closeDriver();
    }
}
