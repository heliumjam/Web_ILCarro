package tests;

import manager.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    static ApplicationManager app = new ApplicationManager();

    @BeforeSuite
    public void setUp(){
        app.init();

    }


    @AfterSuite
    public void stop(){
        app.tearDown();
    }

    @BeforeMethod
    public void startlogger(Method method){
        logger.info("Method "+ method.getName() +" is started ");

    }
    @AfterMethod
    public void end(Method method){
        logger.info("=====================end==method======================== ");

    }

}
