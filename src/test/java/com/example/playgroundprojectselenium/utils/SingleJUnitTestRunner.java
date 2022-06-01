package com.example.playgroundprojectselenium.utils;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

public class SingleJUnitTestRunner {
    public static void main(String... args) throws ClassNotFoundException {
        String[] classAndMethods = args[0].split("#");
        Request request = Request.method(Class.forName(classAndMethods[0]),
                classAndMethods[1]);

        Result result = new JUnitCore().run(request);
        if(result.getFailureCount()==0) {
            System.out.println("De test '"+classAndMethods[0]+"' is geslaagd :) Totaal: "+result.getRunCount()+" Gefaald: "+result.getFailureCount()+" Overgeslagen: "+result.getIgnoreCount());
        } else {
            System.out.println("De test '"+classAndMethods[0]+"' is gefaald :( Totaal: "+result.getRunCount()+" Gefaald: "+result.getFailureCount()+" Overgeslagen: "+result.getIgnoreCount());
            for(int i = 0; i < result.getFailures().size(); i++) {
                result.getFailures().get(i).getException().printStackTrace();
            }
        }
        System.exit(result.wasSuccessful() ? 0 : 1);
    }
}