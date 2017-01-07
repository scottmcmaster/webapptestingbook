package com.smcmaster.weatherapptests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GetWeatherTestWithPageObject extends AbstractWebDriverTest {

  @Test
  public void testGetWeather_Found() {
    WeatherSearchForm weatherSearchForm = new WeatherSearchForm(this.driver);
    weatherSearchForm.setCityName("Beijing");
    weatherSearchForm.setCountry("cn");
    weatherSearchForm.loadWeather();
    assertEquals("Beijing", weatherSearchForm.getWeatherCityName());
    assertFalse(weatherSearchForm.isErrorMessageDisplayed());
  }

  @Test
  public void testGetWeather_NotFound() {
    WeatherSearchForm weatherSearchForm = new WeatherSearchForm(this.driver);
    weatherSearchForm.setCityName("asdfasdfasdfasdf");
    weatherSearchForm.setCountry("cn");
    weatherSearchForm.loadWeather();
    assertEquals("N/A", weatherSearchForm.getWeatherCityName());
    assertTrue(weatherSearchForm.isErrorMessageDisplayed());
  }
}
