package tests;

import manager.TestNgListener;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners(TestNgListener.class)

public class SearchTests extends TestBase{
String location = "Tel Aviv";

@BeforeMethod (alwaysRun = true)
public void precondition() throws IOException {

    app.tearDown();
    app.init();
    app.navigateToMainPage();
}

    @Test (groups = {"regress","positive"})
    public void findCarPasteGeneratedDates(){
        app.getHelperSearch().openCarForm(app.getWait());
       app.getHelperSearch().fillCitySearchForm(location,app.getWait());
       app.getHelperSearch().fillDatesSearchForm();
       app.getHelperSearch().clickSubmitYallaButtom(app.getWait());
        Assert.assertTrue(app.getHelperSearch().isElementFilterPresent());
    }

   @Test (groups = {"regress","positive"})
    public void findCarInThisMonth(){
        app.getHelperSearch().openCarForm(app.getWait());
       app.getHelperSearch().fillCitySearchForm(location,app.getWait());
       app.getHelperSearch().datesSearchFormThisMonth(app.getWait());
      app.getHelperSearch().clickSubmitYallaButtom(app.getWait());
        Assert.assertTrue(app.getHelperSearch().isElementFilterPresent());
    }

    @Test (groups = {"regress","positive"})
    public void findCarInNextMonth(){
        app.getHelperSearch().openCarForm(app.getWait());
       app.getHelperSearch().fillCitySearchForm(location,app.getWait());
       app.getHelperSearch().datesSearchFormNextMonth();
      app.getHelperSearch().clickSubmitYallaButtom(app.getWait());
        Assert.assertTrue(app.getHelperSearch().isElementFilterPresent());
    }

    @Test (groups = {"regress","positive"})
    public void searchPositiveTestL(){
        app.getHelperSearch().fillSearchFormLesson("Tel Aviv", "07/20/2023","03/30/2024");
        app.getHelperSearch().clickSubmitYallaButtom(app.getWait());

    }

    @AfterTest (alwaysRun = true)
    public void postTests(){
    app.tearDown();
    }
//@Test
//    public void dateTest(){
//    System.out.println(app.getHelperSearch().currentDateGenerator());
//    System.out.println(app.getHelperSearch().customDate());
//}
}
