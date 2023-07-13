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

    @Test (groups = {"smoke","positive"})
    public void loginPositiveUser() {
//        User user = new User("domes7@mail.com","123456Aa$");
//        user.setName("domes7@mail.com");
//        user.setPassword("123456Aa$");

        User user = new User()
                .withEmail("domes7@mail.com")
                .withPassword("123456Aa$");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLoggedSuccess());
    }

    @Test (groups = {"smoke","positive"},
            dataProvider = "userDto",dataProviderClass = ProviderData.class)
    public void loginPositiveUserDTO(User user) {
//        User user = new User()
//                .withEmail("domes7@mail.com")
//                .withPassword("123456Aa$");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLoggedSuccess());
    }
    @Test (groups = {"regress","negative"},
            dataProvider = "userDtoNegEmail",dataProviderClass = ProviderData.class)
    public void loginNegativeUserDTO(User user) {
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLoggedFailure());
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
    @Test (groups = {"regress","negative"})
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
    if(app.getHelperUser().isLoggedSuccess() == true
    || app.getHelperUser().isLoggedFailure() == true);
    {
    app.getHelperUser().clickOkButton();
    }
}


}
