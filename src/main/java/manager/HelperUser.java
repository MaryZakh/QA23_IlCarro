package manager;

import models.User;
import org.openqa.selenium.*;


public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }


    public void openLoginForm() {
        click(By.xpath("//a[text()=' Log in ']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"), email);
        type(By.id("password"), password);
    }

    //overloading
    public void fillLoginForm(User user) {
        type(By.id("email"), user.getEmail());
        type(By.id("password"), user.getPassword());
    }


    boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
//        List<WebElement> list = wd.findElements(locator);
//        return list.size() > 0;

    }

    public boolean isLogged() {
        return
                isElementPresent(By.xpath("//*[.=' Logout ']"));
    }

    public void logout() {
        click(By.xpath("//*[.=' Logout ']"));
    }


    public void submitLogin() {
        wd.findElement(By.xpath("//button[@type='submit']"))
                .click();
    }

    public void clickOkButton() {
        if(isElementPresent(By.xpath("//button[text()='Ok']")))
            click(By.xpath("//button[text()='Ok']"));

    }

    public String getErrorText() {
        return wd.findElement(By.cssSelector("div.error")).getText();
    }

    public boolean isYallaButtonNotActive() {
        boolean res = isElementPresent(By.cssSelector("button[disabled]"));
        WebElement element = wd.findElement(By.cssSelector("button[type='submit']"));
        boolean result = element.isEnabled();
        return res && !result;
    }
}