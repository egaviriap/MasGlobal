package tests;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.ResultsPage;
import utility.Utility;

import java.util.List;

public class SearchForBook {

    public static WebDriver driver;


    @BeforeClass
    public static void start() {

        String chromeDriverVersion = Utility.getProperty("driver.version");
        ChromeDriverManager.getInstance().version(chromeDriverVersion).arch64().setup();
        ChromeOptions options = new ChromeOptions();
        // Comment next two lines to see the execution on a browser.
        //options.addArguments("--headless");
        //options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        driver.navigate().to("https://www.google.com");

    }

    @AfterClass
    public static void finish() {
        driver.quit();
    }

    @Test()
    public void userUsesSearch() {


        WebElement searchBar,searchButton;

        searchBar = waitForSomethingClickable(HomePage.searchBar);
        searchBar.sendKeys("The name of the wind");

        searchButton = waitForSomethingClickable(HomePage.searchButton);
        searchButton.click();

        waitForSomethingClickable(ResultsPage.nextButton);
        List<WebElement> resultList = driver.findElements(ResultsPage.resultList);

        resultList.get(0).click();

    }

    @Test()
    public void userUsesSuggestions() {

        WebElement searchBar, suggestions;

        searchBar = waitForSomethingClickable(HomePage.searchBar);
        searchBar.sendKeys("The name of the w");

        suggestions = waitForSomethingClickable(HomePage.suggestions);
        List<WebElement> suggestionsList = driver.findElements(HomePage.suggestions);

        suggestionsList.get(0).click();

        waitForSomethingClickable(ResultsPage.nextButton);
        List<WebElement> resultList = driver.findElements(ResultsPage.resultList);

        resultList.get(0).click();

    }


    //Method to be moved to a different class
    public static WebElement waitForSomethingClickable(By in)
    {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        return wait.until(ExpectedConditions.elementToBeClickable(in));
    }
}
