package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    By NEXT_MONTH_BTN = By.xpath("//button[@aria-label='Next month']");

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
       //click(INPUT_DATE);
        String dates = currentDateGenerator() + "-" + customDate();
        type(INPUT_DATE,dates);
        wd.findElement(INPUT_DATE).submit();
//        click(INPUT_CITY);
        //wd.findElement(By.xpath("//div[@class='mat-calendar-body-cell-content mat-calendar-body-selected']")).click();
        //element.click();
    }

    public boolean isElementFilterPresent() {
        return isElementPresent(ASSERT_SEARCH_LBL);
    }

    public void datesSearchFormThisMonth(WebDriverWait wait){
      //  wd.findElement(INPUT_DATE).clear();

        click(INPUT_DATE);
      //  wd.findElement(INPUT_DATE).clear();

        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mat-calendar-body-cell-content mat-calendar-body-today']")));
        element.click();
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mat-calendar-body-cell-content mat-calendar-body-today']")));
        element.click();
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='27']")));
        element.click();

//        wd.findElement(By.xpath("//div[@class='mat-calendar-body-cell-content mat-calendar-body-today']")).click();
//        wd.findElement(By.xpath("//div[normalize-space()='27']")).click();
//        click(INPUT_CITY);

    }
    public void datesSearchFormNextMonth(){
        click(INPUT_DATE);
        wd.findElement(By.xpath("//div[@class='mat-calendar-body-cell-content mat-calendar-body-today']")).click();
        wd.findElement(NEXT_MONTH_BTN).click();
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


    ////////////////////////////////////////LESSON15////////////////////////////////
    public void fillSearchFormLesson(String city,String dateFrom, String dateTo){
        fillCityLesson(city);
     //   selectPeriodDateLesson(dateFrom, dateTo);
       // selectPeriodDateDatePickerLesson(dateFrom, dateTo);
       // selectPeriodDateDatePickerNextMonthLesson(dateFrom, dateTo);
        selectPeriodDateDatePickerNextYearLesson(dateFrom, dateTo);
    }

    public void fillCityLesson(String adress){
        type(INPUT_CITY, adress);
       // element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pac-item")));
   //     wd.findElement(By.cssSelector("div.pac-item")).click();
        click(By.cssSelector("div.pac-item"));
    }

    public void selectPeriodDateLesson(String dateFrom, String dateTo){
      //  click(INPUT_DATE);
        type(INPUT_DATE,dateFrom+" - " +dateTo);
        click(INPUT_CITY);
    }

    public void selectPeriodDateDatePickerLesson(String dateFrom, String dateTo){
        String [] startDate = dateFrom.split ("/");
        String [] endDate = dateTo.split ("/");
        //       7/15/2023
        // index 1 2  3
        click(By.id("dates"));
//        click(By.xpath("//div[.=' "+startDate[1]+" ']"));
//        click(By.xpath("//div[.=' "+endDate[1]+" ']"));
            // or:
        String locatorStardDate = String.format ("//div[.=' %s ']",startDate[1]); //printf("") - print formated text. %s - string, that after ,
        String locatorEndDate = String.format ("//div[.=' %s ']",endDate[1]);
        click((By.xpath(locatorStardDate)));
        click((By.xpath(locatorEndDate)));
    }

    public void selectPeriodDateDatePickerNextMonthLesson(String dateFrom, String dateTo){
        int fromNowToStart = 0, fromStartToEnd = 1;
        click(By.id("dates"));

        String [] startDate = dateFrom.split ("/");
        String [] endDate = dateTo.split ("/");

        fromStartToEnd = Integer.parseInt(endDate[0]) -Integer.parseInt(startDate[0]);
        if(LocalDate.now().getMonthValue() != Integer.parseInt(startDate[0])){
            fromNowToStart = Integer.parseInt(startDate[0])-LocalDate.now().getMonthValue();
        }
        for(int i = 0; i < fromNowToStart; i++){
            click(NEXT_MONTH_BTN);
        }
            String locatorStardDate = String.format ("//div[.=' %s ']",startDate[1]); //printf("") - print formated text. %s - string, that after ,
            String locatorEndDate = String.format ("//div[.=' %s ']",endDate[1]);
            click((By.xpath(locatorStardDate)));

        for(int i = 0; i < fromStartToEnd; i++){
            click(NEXT_MONTH_BTN);
        }
            click((By.xpath(locatorEndDate)));
    }

    public void selectPeriodDateDatePickerNextYearLesson(String dateFrom, String dateTo){
        LocalDate startDate = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate endDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate nowDate = LocalDate.now();
            String locatorStardDate = String.format ("//div[.=' %s ']",startDate.getDayOfMonth()); //printf("") - print formated text. %s - string, that after ,
            String locatorEndDate = String.format ("//div[.=' %s ']",endDate.getDayOfMonth());
        click(INPUT_DATE);

        int startToEndMonth = startDate.getYear() - nowDate.getYear() == 0 ? //if !=0 -> years differents
                startDate.getMonthValue() - nowDate.getDayOfMonth() :
                12- nowDate.getMonthValue() + startDate.getMonthValue();

        for(int i = 0; i < startToEndMonth; i++){
            click(NEXT_MONTH_BTN);
        }
        click((By.xpath(locatorStardDate)));

        startToEndMonth = endDate.getYear() - startDate.getYear() == 0?
                startDate.getMonthValue() - nowDate.getDayOfMonth() :
                12 - startDate.getMonthValue() + endDate.getMonthValue();

        for(int i = 0; i < startToEndMonth; i++){
            click(NEXT_MONTH_BTN);
        }
        click((By.xpath(locatorEndDate)));


    }
    public void clearInput(){

        wd.findElement(INPUT_DATE).clear();
    }

}
