package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.URI;
import java.time.Duration;

public class BASETest {



        public AndroidDriver driver;
        public AppiumDriverLocalService service;
        public static ExtentReports extent; // Make this static so it is shared across all classes
        public ExtentTest classTest;


        @BeforeClass
        public void setUp() throws Exception {
            // Set up Appium server
            service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\umesh\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                    .withIPAddress("127.0.0.1").usingPort(4723).build();
            service.start();

            // Set the desired capabilities for Appium and initialize the driver
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setDeviceName("android"); // Ensure this matches your actual device name or emulator
            options.setAutomationName("UiAutomator2");
            options.setAppPackage("com.flipkart.android");
            options.setAppActivity("com.flipkart.android.SplashActivity");
            options.setNoReset(true); // To prevent resetting app state

            // Initialize AndroidDriver with Appium server URL and the options
            driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);

            // Set implicit wait time
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }


        @AfterClass
        public void tearDown() {
            // Quit the driver after the test
            if (driver != null) {
                driver.quit();
                System.out.println("Driver quit successfully.");
            }


            }


        }




