package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperCar extends HelperBase {
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        pause(500);
        click(By.xpath("//a[text()=' Let the car work ']"));
    }

    public void fillCarForm(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        //type(By.id("price"),String.valueOf(car.getPrice())) ;
        type(By.id("price"), car.getPrice() + "");
        type(By.id("about"), car.getAbout());
    }
    public void fillCarFormEmptyFuel(Car car) {
        typeLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        selectEmptyFuel(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        type(By.id("price"), car.getPrice() + "");
        type(By.id("about"), car.getAbout());
    }

    private void select(By locator, String option) {
        Select select = new Select(wd.findElement(locator));
        select.selectByValue(option);
        //Gas
//        select.selectByIndex(5);
//        select.selectByValue("Gas");
//        select.selectByVisibleText(" Gas ");

    }
    private void selectEmptyFuel(By locator, String option) {
        click(By.id("fuel"));


    }


    private void typeLocation(String location) {
        type(By.id("pickUpPlace"), location);
        click(By.cssSelector("div.pac-item"));

    }

    private void typeEmptyLocation(String location) {
        type(By.id("pickUpPlace"), location);

    }

    public void returnToHome() {
        click(By.xpath("//button[text()='Search cars']"));
    }

    public void attachPhoto(String link) {
        WebElement element = wd.findElement(By.id("photos"));
        element.sendKeys(link);
    }

    public void searchCurrentMonth(String city, String dateFrom, String dateTo) {
        clearTextBox(By.id("city"));
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        //"1/27/2024", "1/30/2024"   27  30

        String[] from = dateFrom.split("/"); ///["1"]["27"]["2024"]
        String locatorFrom = "//div[text()=' " + from[1] + " ']";
        click(By.xpath(locatorFrom));
        String[] to = dateTo.split("/"); ///["1"]["30"]["2024"]
        String locatorTo = "//div[text()=' " + to[1] + " ']";
        click(By.xpath(locatorTo));

    }

    private void typeCity(String city) {
        type(By.id("city"), city);
        //pause(500);
        click(By.cssSelector("div.pac-item"));
    }

    public boolean isListOfCarsAppeared() {
        return isElementPresent(By.cssSelector("a.car-container"));
    }

    public void searchCurrentYear(String city, String dateFrom, String dateTo) {
        clearTextBox(By.id("city"));
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        //"4/28/2024", "7/15/2024"

        LocalDate now = LocalDate.now();
        System.out.println(now);
        int month = now.getMonthValue();
        //int year = now.getYear();
        //int day = now.getDayOfMonth();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
        // LocalDate from1 = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("yyyy:d/MM"));
        System.out.println(from);

        int diffMonth = from.getMonthValue() - month;
        if (diffMonth > 0) {
            clickNextMonthButton(diffMonth);
        }

        click(By.xpath("//div[text()=' " + from.getDayOfMonth() + " ']"));

        diffMonth = to.getMonthValue() - from.getMonthValue();
        if (diffMonth > 0) {
            clickNextMonthButton(diffMonth);
        }

        // click(By.xpath("//div[text()=' "+to.getDayOfMonth()+" ']"));

        String locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));

    }

    private void clickNextMonthButton(int diffMonth) {
        for (int i = 0; i < diffMonth; i++) {
            click(By.cssSelector("button[aria-label='Next month']"));

        }
    }

    public void searchAnyPeriod(String city, String dateFrom, String dateTo) {
        clearTextBox(By.id("city"));
        typeCity(city);
        clearTextBox(By.id("dates"));
        click(By.id("dates"));
        LocalDate now = LocalDate.now();
        LocalDate from = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern("M/d/yyyy"));
        LocalDate to = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern("M/d/yyyy"));
        int diffYear;
        int diffMonth;
        //***from
        diffYear = from.getYear() - now.getYear();
        if (diffYear == 0) { //2024=2024
            diffMonth = from.getMonthValue() - now.getMonthValue();//10-1=9
        } else//2024!=2025
        {
            diffMonth = 12 - now.getMonthValue() + from.getMonthValue(); //12-1 = 11 +1
        }
        clickNextMonthButton(diffMonth);
        String locator = String.format("//div[text()=' %s ']", from.getDayOfMonth());
        click(By.xpath(locator));

        //***to
        diffYear = to.getYear() - from.getYear();
        if (diffYear == 0) {
            diffMonth = to.getMonthValue() - from.getMonthValue();
        } else {
            diffMonth = 12 - from.getMonthValue() + to.getMonthValue();
        }
        clickNextMonthButton(diffMonth);
        locator = String.format("//div[text()=' %s ']", to.getDayOfMonth());
        click(By.xpath(locator));
    }

    public void navigateByLogo() {
        click(By.cssSelector("a.logo"));
    }

    public void searchNotValidPeriod(String city, String dateFrom, String dateTo) {
        clearTextBox(By.id("city"));
        typeCity(city);
        clearTextBox(By.id("dates"));
        type(By.id("dates"), dateFrom + " - " + dateTo);
        click(By.cssSelector("div.cdk-overlay-backdrop"));
    }

    public boolean isErrorDisplayed(String message) {
        String text = wd.findElement(By.className("ng-star-inserted")).getText();
        return text.equals(message);

    }

    public void fillCarEmptyLocations(Car car) {
        typeEmptyLocation(car.getLocation());
        type(By.id("make"), car.getManufacture());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), String.valueOf(car.getSeats()));
        type(By.id("class"), car.getCarClass());
        type(By.id("serialNumber"), car.getCarRegNumber());
        //type(By.id("price"),String.valueOf(car.getPrice())) ;
        type(By.id("price"), car.getPrice() + "");
        type(By.id("about"), car.getAbout());
    }

    public boolean isButtonReturnToHomePresent() {
        return isElementPresent(By.xpath("//button[text()='Search cars']"));
    }
}
