package org.jtar.test;

import org.jtar.JtarApplication;
import org.jtar.annotations.JtarRunApplication;
import org.jtar.annotations.JtarRunType;

@JtarRunApplication(generateNewTestCase = JtarRunType.NOT_CAREATE_NEW_TESTCASE,sqlAssertion = JtarRunType.CLOSE_SQL_ASSERTION, ordinaryAssertion = JtarRunType.CLOSE_ORDINARY_ASSERTION,notice = JtarRunType.ClOSED_NOTICE)
public class TestMain {
    public static void main(String[] args) {
        JtarApplication.run(TestMain.class,args);
    }
}
