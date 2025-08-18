package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class Androidactions {
    AndroidDriver driver;

    public Androidactions(AndroidDriver driver){
        this.driver=driver;
    }
    public void LongPressAction(WebElement ele)
    {
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture", ImmutableMap.of("elementId",
                ((RemoteWebElement)ele).getId(),"duration",3000));
    }

    public void scrollTotext(String text)
    {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())" +
                ".scrollIntoView(text(\""+text+"\"));"));
    }


    //Scrollaction
    public void ScrollGesture(String contactUs)
    {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector())" +
                ".scrollIntoView(text(\"WebView\"));"));
    }

    //Scrollaction2 when user dont have idea from where to where
    public void ScrollGesture2(String contactUs)
    {
        boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left", 100, "top", 100, "width", 200, "height", 200,
                "direction", "down",
                "percent", 1.0
        ));
    }

    //Swipe action
    public void swipeAction(WebElement ele, String direction)
    {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
                ((RemoteWebElement)ele).getId(),
                "direction",  direction,
                "percent", 0.25
        ));
    }

    public void activity(){
        Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");

        ((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
    }

    //asseration for adding 2 product
    public double getFormattedamt(String amt){
        double price=Double.parseDouble(amt.substring(1));
        return price;
    }


    public void scrollToText(String visibleText) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + visibleText + "\"))"));
    }
}
