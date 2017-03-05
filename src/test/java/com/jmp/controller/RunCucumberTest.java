package com.jmp.controller;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:com/jmp/controller/person.feature",
        glue = "classpath:com.jmp.controller",
        format = {"html:build/cucumber-html", "json:build/cucumber-json.json"},
        plugin = {"pretty", "html:build/cucumber"})
public class RunCucumberTest {
}
