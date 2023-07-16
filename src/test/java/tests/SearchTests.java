package tests;

import manager.TestNgListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListener.class)

public class SearchTests extends TestBase{
String location = "Tel Aviv";

    @Test (groups = {"smoke","positive"})
    public void findCarPasteGeneratedDates(){
        app.getHelperSearch().openCarForm(app.getWait());
       app.getHelperSearch().fillCitySearchForm(location,app.getWait());
       app.getHelperSearch().fillDatesSearchForm();
       app.getHelperSearch().clickSubmitYallaButtom(app.getWait());
        Assert.assertTrue(app.getHelperSearch().isElementFilterPresent());
    }

    @Test (groups = {"smoke","positive"})
    public void findCarInThisMonth(){
        app.getHelperSearch().openCarForm(app.getWait());
       app.getHelperSearch().fillCitySearchForm(location,app.getWait());
       app.getHelperSearch().datesSearchFormThisMonth(app.getWait());
      app.getHelperSearch().clickSubmitYallaButtom(app.getWait());
        Assert.assertTrue(app.getHelperSearch().isElementFilterPresent());
    }

    @Test (groups = {"smoke","positive"})
    public void findCarInNextMonth(){
        app.getHelperSearch().openCarForm(app.getWait());
       app.getHelperSearch().fillCitySearchForm(location,app.getWait());
       app.getHelperSearch().datesSearchFormNextMonth();
      app.getHelperSearch().clickSubmitYallaButtom(app.getWait());
        Assert.assertTrue(app.getHelperSearch().isElementFilterPresent());
    }

    @Test
    public void searchPositiveTestL(){
        app.getHelperSearch().fillSearchFormLesson("Tel Aviv", "07/20/2023","03/30/2024");
        app.getHelperSearch().clickSubmitYallaButtom(app.getWait());

    }


    @AfterMethod(alwaysRun = true)
    public void postcondition() {
        app.navigateToMainPage();
        app.getHelperSearch().clearInput();
    }
//@Test
//    public void dateTest(){
//    System.out.println(app.getHelperSearch().currentDateGenerator());
//    System.out.println(app.getHelperSearch().customDate());
//}
}
