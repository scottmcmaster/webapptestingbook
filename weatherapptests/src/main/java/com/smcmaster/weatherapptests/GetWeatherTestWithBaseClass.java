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

public class GetWeatherTestWithBaseClass extends AbstractWebDriverTest {

  @Test
  public void testGetWeather_Found() {
    setText(WeatherSearchFormLocators.cityName, "Beijing");

    Select countryDropdown = new Select(
        findElementOrFail(WeatherSearchFormLocators.country));
    countryDropdown.selectByValue("cn");

    click(WeatherSearchFormLocators.weatherBtn);

    WebDriverWait wait = new WebDriverWait(driver, 5000);
    wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            WeatherSearchFormLocators.weatherCityName));

    WebElement resultCityName =
        findElementOrFail(WeatherSearchFormLocators.weatherCityName);
    wait = new WebDriverWait(driver, 5000);
    wait.until(
        ExpectedConditions.attributeToBeNotEmpty(
            resultCityName, "innerHTML"));

    assertEquals("Beijing", resultCityName.getAttribute("innerHTML"));
    assertIsNotDisplayed(WeatherSearchFormLocators.errorMessage);
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
}
