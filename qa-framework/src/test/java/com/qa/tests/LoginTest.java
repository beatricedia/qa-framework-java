package com.qa.tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeOptions;


public class LoginTest {
    WebDriver driver; 

    @BeforeTest
    public void setup() {
        // WebDriverManager.chromedriver().browserVersion("135.0.7049.52").setup();
        WebDriverManager.chromedriver().setup();

    
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");  // ✅ Rulează Chrome fără interfață grafică
        options.addArguments("--no-sandbox"); // ✅ Necesită pentru rularea în containere
        options.addArguments("--disable-dev-shm-usage"); // ✅ Evită probleme cu memoria partajată
    
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogleTitle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        Assert.assertEquals(title, "Googl");
    }

    @Test
    public void testSearchBoxPresence() {
        driver.get("https://www.google.com");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q"))); // Așteaptă ca bara de căutare să fie
                                                                                 // vizibilă

        boolean isSearchBoxDisplayed = driver.findElement(By.name("q")).isDisplayed();
        Assert.assertTrue(isSearchBoxDisplayed, "Search box is not displayed!");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
