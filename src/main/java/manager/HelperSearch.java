package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperSearch extends HelperBase{

    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    By OPEN_CAR_SEARCH_FORM = By.xpath("//*[.=' Search ']");
    By INPUT_CITY = By.xpath("//input[@id='city']");
    By INPUT_DATE = By.xpath("//input[@id='dates']");

    By SUBMIT_YALLA_BTN = By.xpath("//button[@type='submit']");

    By ASSERT_SEARCH_LBL = By.xpath("//label[normalize-space()='Search']");

    WebElement element;
    public void openCarForm(WebDriverWait wait) {
        element =
                wait.until(ExpectedConditions
                        .visibilityOfElementLocated(OPEN_CAR_SEARCH_FORM));
        element.click();
    }

    public void fillCitySearchForm(String location,WebDriverWait wait) {

        type(INPUT_CITY, location);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pac-item")));
       wd.findElement(By.cssSelector("div.pac-item")).click();
        pause(2000);
    }


    public void fillDatesSearchForm(){
       click(INPUT_DATE);
        String dates = currentDateGenerator() + "-" + customDate();
        type(INPUT_DATE,dates);
        wd.findElement(INPUT_DATE).submit();
        //wd.findElement(By.xpath("//div[@class='mat-calendar-body-cell-content mat-calendar-body-selected']")).click();
        //element.click();
    }


    public boolean isElementFilterPresent() {
        return isElementPresent(ASSERT_SEARCH_LBL);
    }
    public void datesSearchFormThisMonth(){
        click(INPUT_DATE);
        wd.findElement(By.xpath("//div[@class='mat-calendar-body-cell-content mat-calendar-body-today']")).click();
        wd.findElement(By.xpath("//div[normalize-space()='27']")).click();

    }
    public void datesSearchFormNextMonth(){
        click(INPUT_DATE);
        wd.findElement(By.xpath("//div[@class='mat-calendar-body-cell-content mat-calendar-body-today']")).click();
        wd.findElement(By.xpath("//button[@aria-label='Next month']")).click();
        wd.findElement(By.xpath("//div[normalize-space()='27']")).click();

    }

    public void clickSubmitYallaButtom(WebDriverWait wait){
        element =
                wait.until(ExpectedConditions
                        .visibilityOfElementLocated(SUBMIT_YALLA_BTN));
        element.click();
    }

    public String currentDateGenerator(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
        //get current date time with Date()
        Date date = new Date();
        // Now format the date
        String currentdate= dateFormat.format(date);
        return currentdate;
    }

    public String customDate(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy ");
        Date date = new Date();
        String currentdate= dateFormat.format(date);
        String customDate = "";
        int day = Integer.parseInt(currentdate.substring(3, 5));
        int m = Integer.parseInt(currentdate.substring(0, 2));
        int y = Integer.parseInt(currentdate.substring(6,10));
        if(day > 22) {
            day = (31 - day) +7;
                if (m == 12){
                    m = 1;
                    y++;
                } else {
                    m = m+1;
                }
            }
        else day = day+7;
        customDate = m +"/" + day +"/" + y;

        return customDate;
    }
}
