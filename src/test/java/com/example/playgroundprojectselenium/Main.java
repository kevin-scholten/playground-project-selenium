package com.example.playgroundprojectselenium;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class Main {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MainPageTest.class);
        System.out.println("Totaal: " + result.getRunCount());
        System.out.println("Gefaald: " + result.getFailureCount());
        System.out.println("Overgeslagen: " + result.getIgnoreCount());
    }
}
