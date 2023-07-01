package tests;

import manager.TestNgListener;
import models.Car;
import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNgListener.class)
public class AddNewCar extends TestBase{
    @BeforeMethod
    public void precondition(){

        if(app.getUser().isLogged()==false)
            app.getUser().login(new User()
                    .withEmail("domes7@mail.com")
                    .withPassword("123456Aa$"));
    }

    @Test
    public void addNewCarPositive(){
        int i = (int)(System.currentTimeMillis()/1000)%3600;
        Car car = Car.builder()
                .location("Tel Aviv")
                .make("Ford")
                .model("Mustang")
                .year("2022")
                .fuel("Petrol")
                .seats("5")
                .carClass("B")
                .carRegNumber("780-444"+i)
                .price("150")
                .about("333")
                .build();
        app.getCar().openCarForm();
        app.getCar().fillCarForm(car);
        app.getUser().submitLogin();
    }



}