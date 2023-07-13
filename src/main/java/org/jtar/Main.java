package org.jtar;

import org.jtar.annotations.JtarRunApplication;
import org.jtar.annotations.JtarRunType;

@JtarRunApplication(generateNewTestCase = JtarRunType.CAREATE_NEW_TESTCASE)
public class Main {
    public static void main(String[] args) {
        JtarApplication.run(Main.class,args);
    }
}
