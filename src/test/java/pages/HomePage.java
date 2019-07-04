package pages;

import org.openqa.selenium.By;

public class HomePage {

    public static By searchBar = By.cssSelector("input[name='q']");
    public static By searchButton = By.cssSelector("input[name='btnK']");

    public static By suggestions = By.cssSelector("div.suggestions-inner-container");



}
