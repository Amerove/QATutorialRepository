package com.tutorial.qa.testcases.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ExtentReporter {

    public static ExtentReports generate_Extent_Report(){

        ExtentReports extentReports = new ExtentReports();

        File extentReportFile = new File(System.getProperty("user.dir") + "//ExtentReport//extentReports.html");
        ExtentHtmlReporter reporter = new ExtentHtmlReporter(extentReportFile);

        reporter.config().setTheme(Theme.DARK);
        reporter.config().setReportName("Tutorial Test Automation Result Report");
        reporter.config().setDocumentTitle("TN Automation Report");
        reporter.config().setTimeStampFormat("dd/mm/yyyy hh:mm:ss");

        extentReports.attachReporter(reporter);

        Properties configProp = new Properties();
        File configReportFile = new File(System.getProperty("user.dir") + "//src//main//java//com//tutorial//qa//testcases//utils//config.properties");

        try {
            FileInputStream fisConfigProp = new FileInputStream(configReportFile);
            configProp.load(fisConfigProp);
        }catch (Throwable e){
            e.printStackTrace();
        }
        extentReports.setSystemInfo("Application URL", configProp.getProperty("url"));
        extentReports.setSystemInfo("Browser Name", configProp.getProperty("browser"));
        extentReports.setSystemInfo("Email", configProp.getProperty("validEmail"));
        extentReports.setSystemInfo("Password", configProp.getProperty("validPassword"));
        extentReports.setSystemInfo("Operation System", System.getProperty("os.name"));
        extentReports.setSystemInfo("User-name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Java-version", System.getProperty("java.version"));

        return extentReports;
    }

}
