package com.smcmaster.weatherapptests;

import org.openqa.selenium.By;

public interface WeatherSearchFormLocators {
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
