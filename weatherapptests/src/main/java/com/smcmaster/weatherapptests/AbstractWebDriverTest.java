package com.smcmaster.weatherapptests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AbstractWebDriverTest {
  private static final String FF_DRIVER_DIR = "C:\\Users\\Scott McMaster\\Development\\WebDriver";

  protected WebDriver driver;

  @Before
  public void setUp() {
    System.setProperty("webdriver.gecko.driver", FF_DRIVER_DIR + "/geckodriver.exe");
    driver = new FirefoxDriver();
    driver.get("http://localhost:4200");
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  protected WebElement findElementOrFail(By by) {
    try {
      return driver.findElement(by);
    } catch (NoSuchElementException ex) {
      org.junit.Assert.fail("Element " + by + " not found");
    }
    return null;
  }

  protected void click(By by) {
    WebElement element = findElementOrFail(by);
    element.click();
  }
  
  protected void setText(By by, String text) {
    WebElement element = findElementOrFail(by);
    element.clear();
    element.sendKeys(text);
  }
  
  protected void assertIsNotDisplayed(By by) {
    WebElement element = findElementOrFail(by);
    org.junit.Assert.assertFalse(element.isDisplayed());
  }
}
