package org.example;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FlipkartPOM extends Androidactions {

    AndroidDriver driver;

    public FlipkartPOM(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // English Language selection
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.flipkart.android:id/custom_back_icon\"]")
    private WebElement EnglishLang;

    public void Langselect() {
        EnglishLang.click();
    }

    //Click on Account
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Account\"]/android.view.ViewGroup")
    private WebElement Account;

    public void accountclick(){
        Account.click();
    }

    //Click on Login
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Log In\"]")
    private WebElement Login;

    public void loginclick(){
        Login.click();
    }

    //Use Email-ID
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.flipkart.android:id/tv_right_cta\"]")
    private WebElement useemailinstaedofphno;

    public void useemailinstaedofphno(){
        useemailinstaedofphno.click();
    }

    //Mail-ID field
    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"Email ID\"]")
    private WebElement MailID;

    public void Mail(){
        MailID.sendKeys("rpa2024testing@gmail.com");
    }

    //Continue button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.flipkart.android:id/button\"]")
    private WebElement continueBTN;

    public void conutinuebutton(){
        continueBTN.click();
    }

    //Verify button
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.flipkart.android:id/button\"]")
    private WebElement verify;

    public void verifyBTN(){
        verify.click();
    }

    //Home button on home page
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Home\"]/android.view.ViewGroup")
    private WebElement homeBTN;

    public void homeButton(){

        homeBTN.click();
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_foreground_only_button\"]")
    private  WebElement permissionat_launch;

    public void Permissionatlaunch(){
        permissionat_launch.click();
    }
}