package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preConditions() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }

    }

    @Test
    public void loginSuccess1() {
        User user = new User().withEmail("marga@gmail.com").withPassword("Mmar123456$");
/*
        user.setEmail("marga@gmail.com");
        user.setPassword("Mmar123456$");
*/
        logger.info("Test start with test data --->" + user.toString());
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOkButton();

    }


    @Test
    public void loginSuccess() {
        logger.info("Test start with test data --->/n" + "email : 'marga@gmail.com' & password : 'Mmar123456$'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marga@gmail.com", "Mmar123456$");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOkButton();


    }

    @Test
    public void loginSuccessModel() {
        logger.info("Test start with test data --->/n" + "email : 'marga@gmail.com' & password : 'Mmar123456$'");
        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm("marga@gmail.com", "Mmar123456$");
        app.getHelperUser().submit();
        //Assert if element with text "Logged in success" is present
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        //app.getHelperUser().clickOkButton();


    }


    @Test
    public void loginWrongEmail(){
        logger.info("Test negative check if it possible to login with wrong format email ");
        User user = new User().withEmail("margagmail.com").withPassword("Mmar123456$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void loginWrongPassword(){
        logger.info("Test negative check if it possible to login with wrong format password ");
        User user = new User().withEmail("marga@gmail.com").withPassword("Mmar1234");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");

    }


    @Test
    public void loginUnregisteredUser(){
        logger.info("Test negative check if it possible to login with valid format data unregistered user ");
        User user = new User().withEmail("duck@gmail.com").withPassword("Ddag123456$");

        app.getHelperUser().openLoginForm();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");

    }




    @AfterMethod
    public void postConditions() {
        app.getHelperUser().clickOkButton();

    }
}
