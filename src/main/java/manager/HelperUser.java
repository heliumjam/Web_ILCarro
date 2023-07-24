package manager;

import models.User;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HelperUser extends HelperBase {


    public HelperUser(WebDriver wd) {
        super(wd);
    }

    WebElement element;

    public void openLoginForm() {
 //       wd.findElement(By.xpath("//a[text()=' Log in ']")).click();

        wd.findElement(By.xpath("//*[.=' Log in ']")).click();
    }

    public void fillLoginForm(String email, String password){
        type(By.xpath("//input[@id='email']"), email);
        type(By.xpath("//input[@id='password']"), password);
    }
    //overloading
    public void fillLoginForm(User user) {
     type(By.xpath("(//input[@id='email'])"), user.getEmail());
        type(By.xpath("//input[@id='password']"), user.getPassword());
    }

    public void openRegistrationForm() {

        wd.findElement(By.xpath("//*[.=' Sign up ']")).click();
//        wd.findElement(By.xpath("//*[.=' Log in ']")).click();
//        wd.findElement(By.xpath("//*[.='Click here']")).click();
    }

    public void fillRegForm(User user) {
        type(By.xpath("(//input[@id='name'])"), user.getName());
        type(By.xpath("(//input[@id='lastName'])"), user.getLastName());
        type(By.xpath("(//input[@id='email'])"), user.getEmail());
        type(By.xpath("//input[@id='password']"), user.getPassword());
        clickTermsOfUse();
    }

    public void clickTermsOfUse() {
        //var1
            // click(By.cssSelector("label[for='terms-of-use']"));
        //var2 JS Interface
            JavascriptExecutor js = (JavascriptExecutor) wd;
            js.executeScript("document.querySelector('#terms-of-use').click();");
        //var3 by coordinates
//        Rectangle rect = wd.findElement(By.cssSelector("div.checkbox-container")).getRect();
//        int x = rect.getX() + 5; //px
//        int y = rect.getY() + rect.getHeight() /4; //px
//        Actions actions = new Actions(wd);
//        actions.moveByOffset(x, y).click().perform();
    }

    // method signature = type + name + parameters types
    public void submitLogin(){
       // click(By.xpath("//button[@type='submit']"));
        wd.findElement(By.xpath("//button[@type='submit']")).submit();
      //  wd.findElement(By.xpath("//button[@type='submit']")).click();

    }
    public void logout(){
        click(By.xpath("//*[.=' Logout ']"));
    }

    public boolean isLogged(){
        return isElementPresent(By.xpath("//*[.=' Logout ']"));
    }

    public void clickOkButton(WebDriverWait wait){
        element =
                wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//button[@type='button']")));
        element.click();

//        pause(500);
//        click(By.xpath("//button[@type='button']"));
    }

    public boolean isLoggedSuccess(){
        return isElementPresent(By.xpath("//h2[contains(text(),'success')]"));
    }
    public boolean isLoggedFailure(){
        return isElementPresent(By.xpath("//h2[contains(text(),'incorrect')]"));

    }
    public boolean isRegistrationFailure(){
        return isElementPresent(By.xpath("//h2[contains(text(),'failed')]"));
    }
    public boolean isPasswordWrong(){
        return isElementPresent(By.xpath("//div[contains(text(),'Password must contain')]"));
    }

    public void login(String email, String password){
        openLoginForm();
        fillLoginForm(email,password);
        submitLogin();
    }

}
