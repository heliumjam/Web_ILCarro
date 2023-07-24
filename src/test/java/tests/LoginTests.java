package tests;

import manager.ProviderData;
import manager.TestNgListener;
import models.User;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestNgListener.class)

public class LoginTests extends TestBase {

@BeforeMethod (alwaysRun = true)
public void precondition(){
    if(app.getHelperUser().isLogged())
        app.getHelperUser().logout();
}

    @Test (groups = {"regress","positive"},
            dataProvider = "userDtoLogin",
			dataProviderClass = ProviderData.class)
    public void loginPositiveUserDTO(User user) {
//        User user = new User()
//                .withEmail("domes7@mail.com")
//                .withPassword("123456Aa$");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLoggedSuccess());
    }
    @Test (groups = {"regress","positive"})
    public void loginPositiveProps() {
//        lesson 16
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(app.getEmail(), app.getPassword());
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLoggedSuccess());
    }
	
    @Test (groups = {"regress","negative"},
            dataProvider = "userDtoNeg",
			dataProviderClass = ProviderData.class)
    public void loginNegativeUserDTO(User user) {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLoggedFailure());
	}		

    @Test (groups = {"regress","positive"})
    public void loginPositiveUser() {
        User user = new User()
                .withEmail("domes7@mail.com")
                .withPassword("123456Aa$");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLoggedSuccess());
    }


    @Test (groups = {"regress","negative"})
    public void loginNegativeWrongEmail() {
        User user = new User()
                .withEmail("domesmail.com")
                .withPassword("123456Aa$");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLoggedFailure());
    }
	
    @Test (groups = {"negative","regress"})
    public void loginNegativeWrongEmail2() {
        User user = new User()
                .withEmail("domes@mail.com")
                .withPassword("123456Aa$");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLoggedFailure());
    }


    @AfterMethod (alwaysRun = true)
public void postcondition(){
    if(app.getHelperUser().isLoggedSuccess()
    || app.getHelperUser().isLoggedFailure());
    {
    app.getHelperUser().clickOkButton(app.getWait());
    }
}


}
