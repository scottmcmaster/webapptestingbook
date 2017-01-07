package com.smcmaster.weatherapptests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GetWeatherTestWithFormLocators {

  private static final String FF_DRIVER_DIR = "C:\\Users\\Scott McMaster\\Development\\WebDriver";

  private WebDriver driver;

  @Before
  public void setUp() {
    System.setProperty("webdriver.gecko.driver",
        FF_DRIVER_DIR + "/geckodriver.exe");
    driver = new FirefoxDriver();
    driver.get("http://localhost:4200");
  }

  @Test
  public void testGetWeather_Found() {
    WebElement cityTextbox =
        driver.findElement(WeatherSearchFormLocators.cityName);
    cityTextbox.sendKeys("Beijing");

    Select countryDropdown = new Select(
        driver.findElement(WeatherSearchFormLocators.country));
    countryDropdown.selectByValue("cn");

    WebElement getButton = driver.findElement(
        WeatherSearchFormLocators.weatherBtn);
    getButton.click();

    WebDriverWait wait = new WebDriverWait(driver, 5000);
    wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            WeatherSearchFormLocators.weatherCityName));

    WebElement resultCityName =
        driver.findElement(WeatherSearchFormLocators.weatherCityName);
    wait = new WebDriverWait(driver, 5000);
    wait.until(
        ExpectedConditions.attributeToBeNotEmpty(
            resultCityName, "innerHTML"));

    assertEquals("Beijing", resultCityName.getAttribute("innerHTML"));

    WebElement errorMessage =
        driver.findElement(WeatherSearchFormLocators.errorMessage);
    assertFalse(errorMessage.isDisplayed());
  }

  @Test
  public void testGetWeather_NotFound() {
    WebElement cityTextbox = driver.findElement(By.id("city"));
    cityTextbox.sendKeys("asdfasdfasdfasdf");

    Select countryDropdown = new Select(driver.findElement(By.id("country")));
    countryDropdown.selectByValue("cn");

    WebElement getButton = driver.findElement(By.id("weatherBtn"));
    getButton.click();

    WebDriverWait wait = new WebDriverWait(driver, 5000);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errorMessage")));

    WebElement resultCityName = driver.findElement(By.id("weatherCityName"));
    assertEquals("N/A", resultCityName.getAttribute("innerHTML"));
  }

  @After
  public void tearDown() {
    driver.quit();
  }

}
