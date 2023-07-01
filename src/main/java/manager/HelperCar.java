package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperCar extends HelperBase {

    public HelperCar(WebDriver wd) {
        super(wd);
    }
    public void openCarForm() {
        pause(2000);
      click(By.xpath("//*[.=' Let the car work ']"));
     //   click(By.id("1"));
    }

    public void fillCarForm(Car car) {
        if (isCarFormPresent()==false) return;

        typeLocation(car.getLocation());
        type(By.id("make"),car.getMake());
        type(By.id("model"),car.getModel());
        type(By.id("year"),car.getYear());
        select(By.id("fuel"),car.getFuel());
        type(By.id("seats"),car.getSeats());
        type(By.id("class"),car.getCarClass());
        //      clickToBoxRegNum(car.getCarRegNumber()); //work ok
        type(By.id("serialNumber"), car.getCarRegNumber()); // work if  "element.click();" is off in the HelperBase

        type(By.id("price"),car.getPrice());

    }
    public void typeLocation(String address) {
        type(By.id("pickUpPlace"),address);
        click(By.cssSelector("div.pac-item"));
    }
    public void select(By locator,String option){
        new Select(wd.findElement(locator)).selectByValue(option);

    }

    public boolean isCarFormPresent() {
        return new WebDriverWait(wd, 10)
                .until(ExpectedConditions
                        .textToBePresentInElement(wd.findElement(By.cssSelector("h2")),"Write some details "));

    }
    public void clickToBoxRegNum(String text) {

        //var1 , var2 in HelperUser
        //var3 by coordinates:
        // Rectangle rect = wd.findElement(By.id("serialNumber")).getRect();
        //       int x = rect.getWidth()-rect.getWidth() * 7 / 8;
        //       int y = rect.getHeight()-rect.getHeight() / 2;
        // Actions actions = new Actions(wd);
        // actions.moveByOffset(x,y).click().perform();
            //ERROR - Move target out of bounds

       // var 4:
        WebElement elem = wd.findElement(By.id("serialNumber"));
        Actions actions = new Actions(wd);
        actions.moveToElement(elem).click().perform();
        elem.clear();
        elem.sendKeys(text);
    }
}
