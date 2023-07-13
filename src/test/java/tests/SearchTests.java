package tests;

import manager.TestNgListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListener.class)

public class SearchTests extends TestBase{
String location = "Tel Aviv";

    @Test
    public void findCarPasteGeneratedDates(){
        app.getHelperSearch().openCarForm(app.getWait());
       app.getHelperSearch().fillCitySearchForm(location,app.getWait());
       app.getHelperSearch().fillDatesSearchForm();
       app.getHelperSearch().clickSubmitYallaButtom(app.getWait());
        Assert.assertTrue(app.getHelperSearch().isElementFilterPresent());
    }

    @Test
    public void findCarInThisMonth(){
        app.getHelperSearch().openCarForm(app.getWait());
       app.getHelperSearch().fillCitySearchForm(location,app.getWait());
       app.getHelperSearch().datesSearchFormThisMonth();
      app.getHelperSearch().clickSubmitYallaButtom(app.getWait());
        Assert.assertTrue(app.getHelperSearch().isElementFilterPresent());
    }

    @Test
    public void findCarInNextMonth(){
        app.getHelperSearch().openCarForm(app.getWait());
       app.getHelperSearch().fillCitySearchForm(location,app.getWait());
       app.getHelperSearch().datesSearchFormNextMonth();
      app.getHelperSearch().clickSubmitYallaButtom(app.getWait());
        Assert.assertTrue(app.getHelperSearch().isElementFilterPresent());
    }




//@Test
//    public void dateTest(){
//
//    System.out.println(app.getHelperSearch().currentDateGenerator());
//    System.out.println(app.getHelperSearch().customDate());
//}
}
