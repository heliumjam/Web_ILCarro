package tests;

import manager.ProviderData;
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
	
	    @Test (dataProvider = "userRegDtoCSV",
                dataProviderClass = ProviderData.class)
         public void PositiveRegDTOCSVTest(User user){
        app.getHelperUser().openRegistrationForm();
        logger.info("openRegistrationForm invoked");
        app.getHelperUser().fillRegForm(user);
        logger.info("fillRegForm invoked");
        app.getHelperUser().pause(1000);
        app.getHelperUser().submitLogin();
        logger.info("submitLogin Invoked");
        Assert.assertTrue(app.getHelperUser().isLoggedSuccess());
            app.getHelperUser().clickOkButton(app.getWait());
        logger.info("   PositiveRegTest successfully with credentials: Email: "
                + user.getEmail() + " & Password: " + user.getPassword());
    }
	

//	@Test (groups = {"negative", "regress"},
//			dataProvider = "userDtoNeg",
//			dataProviderClass = ProviderData.class)
//    public void PositiveRegDtoTest(User user){
//        app.getHelperUser().openRegistrationForm();
//        logger.info("openRegistrationForm invoked");
//        app.getHelperUser().fillRegForm(user);
//        logger.info("fillRegForm invoked");
//        app.getHelperUser().pause(1000);
//        app.getHelperUser().submitLogin();
//        logger.info("submitLogin Invoked");
//        Assert.assertTrue(app.getHelperUser().isLoggedSuccess());
//
//        logger.info("   PositiveRegTest successfully with credentials: Email: "
//                + user.getEmail() + " & Password: " + user.getPassword());
//    }

    @Test (groups = {"regress","positives"})
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
        app.getHelperUser().clickOkButton(app.getWait());
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
        app.getHelperUser().clickOkButton(app.getWait());
        logger.info("NegativeRegTestWrongEmail successfully with credentials: Email: "
                + user.getEmail() + " & Password: " + user.getPassword());
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
        app.getHelperUser().clickOkButton(app.getWait());
        logger.info("NegativeRegTestWrongPassword successfully with credentials: Email: "
                + user.getEmail() + " & Password: " + user.getPassword());
    }

    @AfterMethod (alwaysRun = true)
    public void postcondition() {
        app.navigateToMainPage();
    }
}
