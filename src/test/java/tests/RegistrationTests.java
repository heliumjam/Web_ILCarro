package tests;

import manager.HelperBase;
import manager.HelperUser;
import manager.TestNgListener;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListener.class)
public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void precondition() {
        if(app.getUser().isLogged()) app.getUser().logout();
    }

    @Test
    public void PositiveRegTest(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("TestName")
                .withLastName("TestLastName")
                .withEmail("domes"+i+"@mail.com")
                .withPassword("123456Aa$W");
    app.getUser().openRegistrationForm();
            logger.info("openRegistrationForm invoked");
    app.getUser().fillRegForm(user);
            logger.info("fillRegForm invoked");
        app.getUser().pause(1000);
    app.getUser().submitLogin();
            logger.info("submitLogin Invoked");
        Assert.assertTrue(app.getUser().isLoggedSuccess());

        logger.info("PositiveRegTest successfully with credentials: Email: "
                + user.getEmail() + " & Password: " + user.getPassword());
    }


    @Test
    public void NegativeRegTestWrongEmail(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("TestName")
                .withLastName("TestLastName")
                .withEmail("domes"+i+"mail.com")
                .withPassword("123456Aa$W");
        app.getUser().openRegistrationForm();
        app.getUser().fillRegForm(user);
        app.getUser().submitLogin();
        app.getUser().pause(2000);
        Assert.assertFalse(app.getUser().isLoggedSuccess());
        logger.info("NegativeRegTestWrongEmail successfully with credentials: Email: "
                + user.getEmail() + " & Password: " + user.getPassword());
        app.getUser().clickOkButton();

    }

    @Test
    public void NegativeRegTestWrongPassword(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("TestName")
                .withLastName("TestLastName")
                .withEmail("domes"+i+"@mail.com")
                .withPassword("123456Aa");
        app.getUser().openRegistrationForm();
        app.getUser().fillRegForm(user);
        app.getUser().submitLogin();
        app.getUser().pause(2000);
       Assert.assertTrue(app.getUser().isPasswordWrong());
        app.getUser().clickOkButton();
        logger.info("NegativeRegTestWrongPassword successfully with credentials: Email: "
                + user.getEmail() + " & Password: " + user.getPassword());
    }

    @AfterMethod
    public void postcondition() {
    }
}
