package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
@BeforeMethod
public void precondition(){
    app.getUser().isLogged();


}

    @Test
    public void loginPositiveUser() {
//        User user = new User("domes7@mail.com","123456Aa$");
//        String email = "domes7@mail.com";
//        String password = "123456Aa$";
        User user = new User()
                .withEmail("domes7@mail.com")
                .withPassword("123456Aa$");

//        user.setName("domes7@mail.com");
//        user.setPassword("123456Aa$");

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isLoggedSuccessful());

    }

@AfterMethod
public void postcondition(){
app.getUser().clickButtonOk();
}
}
