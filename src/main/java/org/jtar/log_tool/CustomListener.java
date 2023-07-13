//package org.jtar.log_tool;
//
//import org.jtar.time_tool.TimeControl;
//import org.testng.ITestResult;
//import org.testng.TestListenerAdapter;
//
///**
// * @author Allen.Shen
// * @createTime 2023/5/28 5:27
// * @description
// */
//
//public class CustomListener extends TestListenerAdapter {
//    private int m_count = 0;
//
//    private  final  static TimeControl timeControl = new TimeControl();
//
//    @Override
//    public void onTestFailure(ITestResult tr) {
//        log(tr.getTestName()+tr.getName()+ "--Test method failed\n");
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult tr) {
//        log(tr.getName()+ "--Test method skipped\n");
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult tr) {
//        log("["+timeControl.generationTime() +"] "+tr.getName()+"\n"+ "--Test method success\n");
//    }
//
//    private void log(String string) {
//        System.out.print(string);
//        if (++m_count % 40 == 0) {
//
//        }
//    }
//
//}
