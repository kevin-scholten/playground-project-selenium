package com.example.playgroundprojectselenium;

import com.example.playgroundprojectselenium.testportaal.GbaTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({GbaTests.class})
public class MainTestSuite {

}