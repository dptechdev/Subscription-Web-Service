package com.webapp;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
/*
    Because this is an API there is not much browser interaction but similar
    tests will be conducted to test JSON, Content Type, etc.,
    Regardless this is how you would set it up and the project contains the proper
    Selenium dependencies.

 */
public class FunctionalTests {
    private FirefoxDriver browser;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testFirefox() throws Exception {
        browser = new FirefoxDriver();
        browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        browser.get("http://localhost:8080/subscription/test");
    }
}
