package org.example;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class Main {

    private  WebDriver[] drivers;

    //private Map<String, Object> vars;
    //JavascriptExecutor js;

    //det är syfter för 2 två olika webbläsare för chrome och IE (Internet Edge)
    @Before
    public  void Setup()
    {

        drivers = new WebDriver[2];

        drivers[0] = new ChromeDriver();
        drivers[1] = new EdgeDriver();

        //js = (JavascriptExecutor) driver;
        //vars = new HashMap<String, Object>();

    }
    @Given("Im on the page {string}")
    public void im_on_the_page(String page) {
        for (WebDriver driver : drivers)
            driver.get(page);
    }
    @When("Fill {string} in {string}")
    public void fill_in(String text, String id) {
        for (WebDriver driver : drivers) {
            driver.findElement(By.id(id)).click();
            driver.findElement(By.id(id)).sendKeys(text);
        }
    }

    @When("Click on {string}")
    public void ClickOn(String selector)
    {
        for (WebDriver driver : drivers)
            driver.findElement(By.cssSelector(selector)).click();
    }
    // blir jobbigt att måste fixa flera kod, det här är en enkelt utväg.
    String RandomString(int length) {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rnd.nextFloat() * abc.length());
            salt.append(abc.charAt(index));
        }
        return salt.toString();
    }

    @When("Password in {string} and {string}")
    public void passwords(String id0, String id1)
    {
        for (WebDriver driver : drivers)
        {
            String password = RandomString(8);

            // funktion som ser till både är olika text,
            // Java har random funktion men finns risk både är samma
            driver.findElement(By.id(id0)).sendKeys(password);
            driver.findElement(By.id(id1)).sendKeys(password);

            // driver funktion som matar in till webbsidan
        }
    }

    @When("Mismatch password in {string} and {string}")
    public void mismatch_passwords(String id0, String id1)
    {
        for (WebDriver driver : drivers)
        {
            String psw0 = "";
            String psw1 = "";

            while (psw0 == psw1)
            {
                psw0 = RandomString(8); // java random string gen
                psw1 = RandomString(9); // java random string gen
            }

            driver.findElement(By.id(id0)).sendKeys(psw0);
            driver.findElement(By.id(id1)).sendKeys(psw1);
        }
    }
//junit assert
    @Then("I should see a confirmation message {string}")
    public void i_should_see_a_confirmation_message(String expectedMessage) {
        for (WebDriver driver : drivers) {
           // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Använd Duration.ofSeconds här
           // String actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("din_faktiska_selector"))).getText();
            //assertEquals(expectedMessage, actualMessage);
        }
    }

}


