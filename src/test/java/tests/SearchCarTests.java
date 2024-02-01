package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchCarTests extends TestBase{

@BeforeMethod
public void preCondition(){
    app.getHelperCar().navigateByLogo();
}
    @Test
    public void searchCurrentMonthSuccess(){
        app.getHelperCar().searchCurrentMonth("Tel Aviv, Israel", "2/2/2024", "2/25/2024");
        app.getHelperCar().getScreen("src/test/screenshots/currentMonth.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }


    @Test
    public void searchCurrentYearSuccess(){
        app.getHelperCar().searchCurrentYear("Rehovot, Israel", "4/28/2024", "7/15/2024");
        app.getHelperCar().getScreen("src/test/screenshots/currentYear.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());
    }

    @Test
    public void searchAnyPeriodSuccess(){
        app.getHelperCar().searchAnyPeriod("Tel Aviv, Israel", "6/11/2024", "1/15/2025");
        app.getHelperCar().getScreen("src/test/screenshots/any.png");
        app.getHelperCar().submit();
        Assert.assertTrue(app.getHelperCar().isListOfCarsAppeared());

    }

    @Test
    public void negativeSearch(){
    app.getHelperCar().searchNotValidPeriod("Tel Aviv Israel", "1/10/2024", "10/10/2024");
    app.getHelperCar().submit();
    Assert.assertTrue(app.getHelperCar().isYallaButtonNotActive());
    Assert.assertTrue(app.getHelperCar().isErrorDisplayed("You can't pick date before today"));
    }



}
