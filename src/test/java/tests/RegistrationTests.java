package tests;

import manager.TestNgListener;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListener.class)
public class RegistrationTests extends TestBase{
    @BeforeMethod (alwaysRun = true)
    public void precondition() {
        if(app.getHelperUser().isLogged()) app.getHelperUser().logout();
    }

    @Test (groups = {"smoke","positive"})
    public void PositiveRegTest(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("TestName")
                .withLastName("TestLastName")
                .withEmail("domes"+i+"@mail.com")
                .withPassword("123456Aa$W");
    app.getHelperUser().openRegistrationForm();
            logger.info("openRegistrationForm invoked");
    app.getHelperUser().fillRegForm(user);
            logger.info("fillRegForm invoked");
        app.getHelperUser().pause(1000);
    app.getHelperUser().submitLogin();
            logger.info("submitLogin Invoked");
        Assert.assertTrue(app.getHelperUser().isLoggedSuccess());

        logger.info("PositiveRegTest successfully with credentials: Email: "
                + user.getEmail() + " & Password: " + user.getPassword());
    }


    @Test (groups = {"regress","negative"})
    public void NegativeRegTestWrongEmail(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("TestName")
                .withLastName("TestLastName")
                .withEmail("domes"+i+"mail.com")
                .withPassword("123456Aa$W");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegForm(user);
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(2000);
        Assert.assertFalse(app.getHelperUser().isLoggedSuccess());
        logger.info("NegativeRegTestWrongEmail successfully with credentials: Email: "
                + user.getEmail() + " & Password: " + user.getPassword());
        app.getHelperUser().clickOkButton();

    }

    @Test (groups = {"regress","negative"})
    public void NegativeRegTestWrongPassword(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        User user = new User()
                .withName("TestName")
                .withLastName("TestLastName")
                .withEmail("domes"+i+"@mail.com")
                .withPassword("123456Aa");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegForm(user);
        app.getHelperUser().submitLogin();
        app.getHelperUser().pause(2000);
       Assert.assertTrue(app.getHelperUser().isPasswordWrong());
        app.getHelperUser().clickOkButton();
        logger.info("NegativeRegTestWrongPassword successfully with credentials: Email: "
                + user.getEmail() + " & Password: " + user.getPassword());
    }

    @AfterMethod (alwaysRun = true)
    public void postcondition() {
    }
}
