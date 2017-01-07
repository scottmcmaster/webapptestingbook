package com.smcmaster.weatherapptests;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObject {

  protected final WebDriver driver;

  public PageObject(WebDriver driver) {
    this.driver = driver;
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

}
