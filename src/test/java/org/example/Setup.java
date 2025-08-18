package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Setup extends BASETest {
    public WebDriverWait wait;
    public FlipkartPOM flipkartpom;

    @BeforeClass
    public void start(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        flipkartpom = new FlipkartPOM(driver);

    }

    @Test
    public void testapp() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigating through the app
        flipkartpom.Langselect();
        flipkartpom.accountclick();
        flipkartpom.loginclick();

        // Switching to email login if needed
        try {
            flipkartpom.useemailinstaedofphno();
        } catch (Exception e) {
            // If "Use Email ID" is not present, just continue with email login
        }

        // Input Email ID
        flipkartpom.Mail();
        flipkartpom.conutinuebutton();

        // Fetch OTP and print it
        String otp = OTPFetcher.OTPF();
        System.out.println("OTP fetched: " + otp);

        // Verify OTP format
        if (otp != null && otp.length() == 6) {
            System.out.println("OTP is valid: " + otp);
        } else {
            System.out.println("Invalid OTP fetched. Exiting test.");
            return;  // Exit if OTP is invalid
        }

        // Wait for OTP input field to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement otpField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.EditText[@resource-id=\"com.flipkart.android:id/otp_view\"]")));

        // Debug: Print OTP field status
        System.out.println("OTP field is found: " + (otpField != null));

        // If OTP field is found, enter the OTP
        if (otpField != null) {
            otpField.sendKeys(otp);  // Send the entire OTP as a string
            System.out.println("OTP entered successfully!");
        } else {
            System.out.println("OTP input field not found.");
        }
        flipkartpom.verifyBTN();
        flipkartpom.scrollToText("My Activity");
        flipkartpom.homeButton();
        flipkartpom.homeButton();
    }
}
