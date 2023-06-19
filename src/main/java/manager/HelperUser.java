package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperUser extends HelperBase {


    public HelperUser(WebDriver wd) {
        super(wd);
    }


    public void openLoginForm() {
        wd.findElement(By.xpath("//a[text()=' Log in ']")).click();

    }

    public void fillLoginForm(String email, String password) {
        type(By.xpath("(//input[@id='email'])"),email);
        type(By.xpath("//input[@id='password']"),password);
    }
    public void submitLogin(){
        click(By.xpath("//button[@type='submit']"));
    }
}
