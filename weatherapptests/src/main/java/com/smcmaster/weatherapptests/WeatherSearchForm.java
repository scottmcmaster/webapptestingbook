package com.smcmaster.weatherapptests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page object fore the weather search form.
 * @author Scott McMaster
 */
public class WeatherSearchForm extends PageObject {

  private interface Locators {
    public static By cityName = By.id("city");
    public static By country = By.id("country");
    public static By weatherBtn = By.id("weatherBtn");
    public static By weatherCityName = By.id("weatherCityName");
    public static By weatherHumidity = By.id("weatherHumidity");
    public static By weatherPressure = By.id("weatherPressure");
    public static By weatherTemperature = By.id("weatherTemperature");
    public static By weatherWind = By.id("weatherWind");
    public static By errorMessage = By.id("errorMessage");
  }

  public WeatherSearchForm(WebDriver driver) {
    super(driver);
  }
  
  public void setCityName(String cityName) {
    setText(Locators.cityName, cityName);
  }
  
  public void setCountry(String country) {
    Select countryDropdown = new Select(
        findElementOrFail(Locators.country));
    countryDropdown.selectByValue(country);
  }
  
  public void loadWeather() {
    click(Locators.weatherBtn);
    WebDriverWait wait = new WebDriverWait(driver, 5000);
    wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            WeatherSearchFormLocators.weatherCityName));
  }
  
  public String getWeatherCityName() {
    WebElement resultCityName =
        findElementOrFail(WeatherSearchFormLocators.weatherCityName);
    WebDriverWait wait = new WebDriverWait(driver, 5000);
    wait.until(
        ExpectedConditions.attributeToBeNotEmpty(
            resultCityName, "innerHTML"));

    return resultCityName.getAttribute("innerHTML");
  }
  
  public boolean isErrorMessageDisplayed() {
    WebElement element = findElementOrFail(
        WeatherSearchFormLocators.errorMessage);
    return element.isDisplayed();
  }
}
